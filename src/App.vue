<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      /*标记一下，判断刷新还是关闭*/
      flag: '',
    };
  },
  onbeforeunload() {
    this.flag = 'refresh';
  },
  onunload() {
    if (this.flag !== 'refresh') {
      localStorage.clear();
    }
  },
  beforeCreate() {
    document.querySelector('body').setAttribute('style', 'margin:0;')
    //在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem('store')) {
      this.$store.replaceState(
        Object.assign(
          {},
          this.$store.state,
          JSON.parse(sessionStorage.getItem('store'))
        )
      )
    }

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener("beforeunload", () => {
      sessionStorage.setItem("store", JSON.stringify(this.$store.state))
    })
  },
}

</script>

<style>
#app {
  height: 100%;
  width: 100%;
}

body {
  margin: 0;
  height: 99vh;
}
</style>
