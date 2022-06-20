<template>
  <div id="app">
    <topnav v-show="navShow"></topnav>
    <router-view></router-view>
  </div>
</template>

<script>
import topnav from './components/topnav'
import index from './view/index'

export default {
  data () {
    return {
      navShow: true
    }
  },
  watch: {
    $route (to, from) {
      if (to.path === '/login' || to.path === '/Slogin' || to.path === '/cregister' || to.path === '/Sregister') {
        this.navShow = false
      } else {
        this.navShow = true
      }
    }
  },
  components: {
    topnav,
    index

  },
  created () {
    this.getTypeInfo()
  },
  methods: {
    getTypeInfo () {
      axios.get('/api/type/getAllType').then(({ data }) => {
        this.$store.commit('reloadType', data.data)
      })
    }
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
</style>
