import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)


const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes =[
  {
    path: '/login',
    name:'login',
    component: () =>
      import('../components/login.vue')
  },
  {
    path: '/',
    name:'index',
    component: () =>
      import('../view/index.vue')
  },
  {
    path: '/sregister',
    name:'sregister',
    component: () =>
      import('../components/Sregister.vue')
  },
  {
    path: '/cregister',
    name:'cregister',
    component: () =>
      import('../components/cregister.vue')
  },
  {
    path: '/core',
    name:'core',
    component: () =>
      import('../view/core.vue')
  },
  {
    path: '/manage',
    name:'manage',
    component: () =>
      import('../view/manage.vue')
  },
  {
    path: '/Slogin',
    name:'Slogin',
    component: () =>
      import('../components/Slogin')
  },
  {
    path: '/sindex',
    name:'sindex',
    component: () =>
      import('../components/sindex')
  },
  {
    path: '/superLogin',
    name:'superLogin',
    component: () =>
      import('../components/superLogin')
  }
  ]
export default new Router({
  mode: "history",
  routes

})
