import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id : 0,
    name:null,
    username:null,
    address:null,
    tel:null,
    money:0,
    authentic:9,
    typeList:[]
  },
  mutations: {
    updateAddress(state,address){
      state.address = address
    }
    ,
    login(state,user){
      state.id = user.id;
      state.username = user.username;
      state.tel = user.tel;
      state.address = user.address;
      state.money = user.money;
      state.name = user.name;
      state.authentic = user.authentic;
    },
    reloadType(state,typeList){
      state.typeList = typeList;
    },
    logout(state){
      state.id = 0;
      state.username = null;
      state.tel = null;
      state.address = null;
      state.money = null;
      state.name = null;
      state.authentic = 9;
    }
  },
  actions: {},
  modules: {},
  plugins: [
    createPersistedState({
      storage: window.sessionStorage
    })
  ]
});
