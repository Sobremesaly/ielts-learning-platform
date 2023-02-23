
export const publicFunction = {
  filters: {//配置过滤器
    formatDate: function (value) {//调用时间戳为日期显示
      let date = new Date(value)
      let y = date.getFullYear()  //获取年份
      let m = date.getMonth() + 1  //获取月份
      m = m < 10 ? "0" + m : m  //月份不满10天显示前加0
      let d = date.getDate()  //获取日期
      d = d < 10 ? "0" + d : d  //日期不满10天显示前加0
      return y + "-" + m + "-" + d
      //也可以获取更精准时间
      //let h = date.getHours(),                   //小时
      //let m = date.getMinutes(),                 //分
      //let s = date.getSeconds(),                 //秒
      //let ls = date.getMilliseconds()            //毫秒
    },
    formatDateMore: function (value) {
      let date = new Date(value)
      let y = date.getFullYear()  //获取年份
      let m = date.getMonth() + 1  //获取月份
      m = m < 10 ? "0" + m : m  //月份不满10天显示前加0
      let d = date.getDate()  //获取日期
      d = d < 10 ? "0" + d : d  //日期不满10天显示前加0

      /*也可以获取更精准时间*/
      let h = date.getHours()                   //小时
      let min = date.getMinutes()                //分
      let s = date.getSeconds()                 //秒
      let ls = date.getMilliseconds()            //毫秒
      return y + "-" + m + "-" + d + "/" + h + ":" + min + ":" + s;
    }
  }
}

/*将时间戳转换成通使用的时间*/
export function dateFormat(date) {

  let y = date.getFullYear()
  let m = date.getMonth() + 1
  m = m < 10 ? ('0' + m) : m
  let d = date.getDate()
  d = d < 10 ? ('0' + d) : d
  let h = date.getHours()
  h = h < 10 ? ('0' + h) : h
  let M = date.getMinutes()
  M = M < 10 ? ('0' + M) : M
  let s = date.getSeconds()
  s = s < 10 ? ('0' + s) : s
  let dateTime = y + '-' + m + '-' + d + ' ' + h + ':' + M + ':' + s;

  return dateTime;
}

/*获取现在的时刻的时间戳*/
export function getNowFormatDate() {
  let date = new Date();
  let separate1  = "-";
  let separate2 = ":";
  let month = date.getMonth() + 1;
  let strDate = date.getDate();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  return date.getFullYear() + separate1 + month + separate1 + strDate
    + " " + date.getHours() + separate2 + date.getMinutes()
    + separate2 + date.getSeconds();
}


