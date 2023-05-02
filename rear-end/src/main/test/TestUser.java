import com.alibaba.fastjson.JSON;
import com.xinyu.entity.User;
import com.xinyu.entity.order.OrderDetails;
import com.xinyu.mapper.UserMapper;
import com.xinyu.mapper.order.OrderDetailsMapper;
import com.xinyu.mapper.role.RoleMapper;
import com.xinyu.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestUser {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerService powerService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private AttrService attrService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Test
    public void testSelect(){
        List<User> userList = userMapper.getUserList(null);
        System.out.println(userList);
    }

    @Test
    public void testRole(){
         String json = roleService.selRoleAndPower();
         System.out.println(json);
    }

    @Test
    public void testPower(){
        String json = powerService.treePowers();
        System.out.println(json);
    }

    @Test
    public void testGoodsCategory(){
        String json = goodsCategoryService.selGoodsCategoryJson();
        System.out.println(json);
    }

    @Test
    public void testAttrJSON(){
        String json = attrService.attrJson(3);
        System.out.println(json);
    }

    @Test
    public void testNoewTime(){
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);
        System.out.println(nowTime);
    }

    @Test
    public void testOrderDetails(){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(1);
        List<OrderDetails> orderDetailsList = orderDetailsMapper.detailsList(orderDetails);

        System.out.println(JSON.toJSONString(orderDetailsList));
    }

}
