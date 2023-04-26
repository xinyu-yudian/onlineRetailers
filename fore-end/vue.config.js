const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    devServer: {
        // host: 'http://192.168.52.128/',
        port: 88,
        open: { app: { name: 'chrome' } }
    }
})