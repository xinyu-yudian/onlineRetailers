const express = require('express')

const compression = require('compression')

const app = express()

app.use(compression())

app.use(express.static('./dist'))



app.listen(80, () => {
    console.log('server running at http://localhost:80')

})