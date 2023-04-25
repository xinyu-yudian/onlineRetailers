const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    devServer: {
        // host: '192.168.43.50',
        port: 8082,
        open: { app: { name: 'chrome' } }
    }
})