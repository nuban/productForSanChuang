let proxyObj = {}

proxyObj['/'] = {
    //websocket
    ws: false,
    //目标地址 http://www.garyrui.cn:8867/ http://localhost:8082/
    target: 'http://localhost:9999/',
    //target: 'http://www.garyrui.cn:8867/',
    //发送请求头host会被设置为target
    changeOrigin: true,
    //不重写请求地址
    pathRewrite: {
        '^/': '/'
    }
}

//nodejs代理方式解决 跨域问题
module.exports = {
    devServer: {
        host: 'localhost',
        port: 8082,
        proxy: proxyObj
    }
}