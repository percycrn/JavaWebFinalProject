<template>
  <div class="login-dialog">
    <form action="http://10.41.56.30:80/JavaWebFinalProject/LoginServlet" method="post" class="from">
      <input type="text" class="item" name="phoneNo" placeholder="手机号">
      <input type="text" class="item" name="password" placeholder="密码">
      <input type="submit" class="item button" :value="buttonText">
    </form>
    <label class="swither">{{labelText}}</label>
  </div>
</template>

<script>
export default {
  name: 'login-dialog',
  data () {
    return {
        buttonText:"登陆",
        labelText:"没有账号，注册",
        formData:null,
    }
  },
  components:{},
  mounted:function(){
    let form = document.querySelector(".form")
    this.formData = new FormData(form)
    this.formData.append("action", "login")
  },
  methods:{

  },
}
</script>

<style>
.login-dialog{
  position: absolute;
  border: 1px solid black;
  z-index: 9999;
}
.from{
  display: flex;
  flex-flow: column;
}
.item{
  width: 200px;
    height: 30px;
    margin: 10px;
    border: 0 none;
}
.button{
  background-color: #f56600;
  cursor: pointer;
  border: 0 none;
}
.swither{
  text-decoration-line: underline
}
</style>
