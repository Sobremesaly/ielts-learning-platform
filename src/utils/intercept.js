// request 拦截器
// 可以自请求发送前对请求做一些处理
import axios from "axios";
import {Message} from "element-ui";

axios.interceptors.request.use(config => {
  // 设置请求头
  config.headers.token = localStorage.getItem("token");
  return config
}, error => {
  return Promise.reject(error)
});

axios.interceptors.response.use(
  response => {
    // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
    // 否则的话抛出错误
    if (response.status === 200) {
      return Promise.resolve(response);
    }
    else if(response.status === 504){
      Message({
        message: '登录状态过期，请重新登录',
        type: 'error'
      });
      return Promise.reject(response);
    }
  })

// 这句一定要写
export default axios
