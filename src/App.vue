<template>
  <div id="app">
    <div id="header">
      <login></login>
    </div>
    <div id="main"></div>
    <div id="footer"></div>
  </div>
</template>

<script>
import Login from "./Login.vue"
export default {
  name: 'app',
  data () {
    return {

    }
  },
  components:{Login},
  mounted:function(){

  },
  methods:{

  },
}
</script>

<style>

</style>
