class Bus {
  constructor() {
    // 定义回调函数
    this.callback = {}
  }

  // 事件注册监听
  $on(name, fn) {
    this.callback[name] = this.callback[name] || []
    this.callback[name].push(fn)
  }

  // 触发事件
  $emit(name, args) {
    if (this.callback[name]) {
      // 遍历所有的 callback
      this.callback[name].map(cb => cb(args))
    }
  }

  // 移除事件
  $off(name) {
    if (this.callback[name]) {
      delete this.callback[name]
    }
  }
}

const bus = new Bus()
export default bus
