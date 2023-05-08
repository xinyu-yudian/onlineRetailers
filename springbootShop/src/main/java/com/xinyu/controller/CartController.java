package com.xinyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinyu.entity.Cart;
import com.xinyu.entity.Goods;
import com.xinyu.entity.User;
import com.xinyu.entity.order.Order;
import com.xinyu.entity.order.OrderDetails;
import com.xinyu.service.GoodsService;
import com.xinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class CartController {

    private Goods goods;
    private Cart cart;
    @Autowired
    private GoodsService goodsService;
    private List<Cart> cartList;
    private Map<String,Object> map;
    private ServletContext application;

    @Autowired
    private OrderService orderService;

    @PostMapping("/addCartGoods")
    public String addCartGoods(@RequestBody String params, HttpServletRequest req){
        goods = new Goods();
        cart = new Cart();
        application = req.getServletContext();
        cartList = (List<Cart>)application.getAttribute("cartList");
        System.out.println("添加商品前：" +cartList);
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println("待添加商品信息："+jsonObject);
        Integer number = jsonObject.getInteger("number");
        Integer id = jsonObject.getInteger("id");
        map = new HashMap<>();
        for (Cart c : cartList) {
            if(c.getGoods().getId() == id){
                map.put("status",410); //410商品已存在
                return JSON.toJSONString(map);
            }
        }
        goods.setId(id);
        List<Goods> goodsList = goodsService.selGoodsList(goods);
        goods = goodsList.get(0);
        cart.setNumber(number);
        cart.setGoods(goods);
        cart.setChecked(false);
        cartList.add(cart);
        System.out.println("添加商品后：" +cartList);
        application.setAttribute("cartList",cartList);
        map.put("status",200);
        return JSON.toJSONString(map);
    }

    @PostMapping("/updateCartGoods")
    public String updateCartGoods(@RequestBody String params, HttpServletRequest req){
        cart = new Cart();
        application = req.getServletContext();
        cartList = (List<Cart>)application.getAttribute("cartList");
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        Integer number = jsonObject.getInteger("number");
        Integer id = jsonObject.getInteger("id");
        map = new HashMap<>();
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getGoods().getId() == id){
                if (number == 0){
                    cartList.remove(i);
                    break;
                }
                cart.setNumber(number);
                cart.setGoods(cartList.get(i).getGoods());
                cartList.set(i, cart);
                break;
            }
        }
        application.setAttribute("cartList",cartList);
        map.put("status",200);
        return JSON.toJSONString(map);
    }

    @GetMapping("/selCartGoods")
    public String selCartGoods(HttpServletRequest req){
        application = req.getServletContext();
        cartList = (List<Cart>)application.getAttribute("cartList");
        System.out.println(cartList);
        System.out.println(JSON.toJSONString(cartList, SerializerFeature.DisableCircularReferenceDetect));
        map = new HashMap<>();
        map.put("status",200);
        map.put("cartList",cartList);
        System.out.println(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }

    @PostMapping("/generateOrder")
    public String generateOrder(@RequestBody String params){
        cartList = (List<Cart>)application.getAttribute("cartList");
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println("确认购买的商品");
        System.out.println(jsonObject);
        List<Cart> conCart = JSON.parseArray(jsonObject.getString("cart"),Cart.class);
        List<Cart> arrCary = cartList;
        for (Cart cart1 : conCart) {
            for (int i = 0; i < cartList.size(); i++) {
                if(cart1.getGoods().getId() == arrCary.get(i).getGoods().getId()){
                    arrCary.remove(i);
                    break;
                }
            }
        }
        application.setAttribute("cartList",arrCary);

        //生成订单
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        User user = (User) application.getAttribute("user");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowTime = sdf.format(date);
        Double sumPrice = 0.0;
        for (Cart cart1 : conCart) {
            sumPrice += cart1.getGoods().getPrice() * cart1.getNumber();
        }
        Order order = new Order();
        order.setOrderNumber(uuid);
        order.setUserId(user.getId());
        order.setPlaceTime(nowTime);
        order.setOrderPrice(sumPrice);
        order.setPayStatus(0);
        String msg = orderService.addOrder(order) ? "订单生成成功":"订单生成失败";
        System.out.println(msg);
        //生成订单详情
        order = new Order();
        order.setOrderNumber(uuid);
        order = orderService.selOrders(order).get(0);
        OrderDetails orderDetails;
        for (Cart cart1 : conCart) {
            orderDetails = new OrderDetails();
            orderDetails.setOrderId(order.getId());
            orderDetails.setGoodsId(cart1.getGoods().getId());
            orderDetails.setGoodsPrice(cart1.getGoods().getPrice());
            orderDetails.setGoodsNumber(cart1.getNumber());
            orderDetails.setGoodsTotalPrice(cart1.getNumber() * cart1.getGoods().getPrice());
            msg = orderService.addOrderDetails(orderDetails) ? "商品"+cart1.getGoods().getId()+"详情添加成功" : "添加详情失败";
            System.out.println(msg);

            //对呀商品减少库存
            goods = new Goods();
            goods.setId(cart1.getGoods().getId());
            goods.setNumber(cart1.getNumber());
            msg = goodsService.updateNumber(goods) ? "商品数量已减少" : "商品数量减少成功";
            System.out.println(msg);
        }

        map.put("status",200);
        map.put("order",order);
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }

    @PostMapping("/payStatus")
    public String payStatus(@RequestBody String params) {
        JSONObject jsonObject = JSON.parseObject(params);
        System.out.println(jsonObject);
        Order order = new Order();
        Integer id = jsonObject.getInteger("id");
        order.setId(id);
        order.setPayStatus(1);
        boolean flag = orderService.updateOrder(order);
        String msg = flag ? "订单支付成功" : "订单支付失败";
        map = new HashMap<>();
        if(flag){
            map.put("status",200);
        }else {
            map.put("status",500);
        }
        return JSON.toJSONString(map);
    }


}
