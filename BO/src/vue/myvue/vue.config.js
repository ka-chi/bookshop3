module.exports = {
  transpileDependencies: ['vuetify'],
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8088',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
    disableHostCheck: true
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
}
const path = require("path");
function resolve(dir) {
  return path.join(__dirname, dir);
}
