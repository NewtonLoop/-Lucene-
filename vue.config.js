module.exports = {


    devServer: {
      proxy: {
        '/api': {
          target:"http://localhost:8080/",
          changeOrigin:true,
          pathRewrite:{
            '^/api':''
          }
        }
      }
    },

    // 此处修改你想要的端口号


  lintOnSave: false
}
