<template>
<!--
  TODO:
  mask 磨砂效果
 -->
  <div>

    <svg  xmlns="http://www.w3.org/2000/svg"  width="50px" height="50px"
    viewBox="0 0 1024 1024"
    @click="login">
        <path
        d="M992.629535 851.349057c0-150.691618-36.246115-382.00155-236.185583-382.00155-21.20341 0-110.665928 92.675156-244.425054 92.675156-133.872513 0-223.25944-92.675156-244.46285-92.675156-199.939468 0-236.185583 231.309932-236.185583 382.00155 0 107.982431 73.777286 172.650943 182.251061 172.650943h596.681357c108.587163 0 182.326653-64.668512 182.326652-172.650943zM774.170155 255.990551c0-141.318274-117.469162-255.990551-262.151257-255.990551-144.719891 0-262.189053 114.672277-262.189053 255.990551s117.469162 256.028347 262.189053 256.028347c144.682095 0 262.151257-114.710073 262.151257-256.028347z"
        p-id="6061"
        fill="#2c2c2c"
        />
    </svg>
    <div v-if="logging" class="mask"></div>
    <login-dialog v-if="logging"></login-dialog>
  </div>
</template>

<script>
import LoginDialog from "./LoginDialog.vue"
export default {
  name: 'login',
  data() {
    return {
        logging:false,
    }
  },
  components:{LoginDialog},
  methods: {
    login: function () {
      this.logging =true
    }
  }
}
</script>

<style>
.mask{
  position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: rgba(0,0,255,0.5);
}

</style>
