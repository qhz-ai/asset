<template>
  <div class="app-container">

    <iframe id="content" src="http://192.168.10.106:8888/WCGCloud_static/main.html" width="100%" height="750px" v-bind:frameborder="0"></iframe>

  </div>

</template>
<script>
  export default {
  mounted(){
  let that = this

  window.onresize = () => {
  let ifdoc = that.$refs.main_page_iframe.contentDocument

  let cHeight = Math.max(ifdoc.body.clientHeight, ifdoc.documentElement.clientHeight)

  let sHeight = Math.max(ifdoc.body.scrollHeight, ifdoc.documentElement.scrollHeight)

  that.$refs.main_page_iframe.style.height = Math.max(cHeight, sHeight) + 'px'

  }

  }

  }
</script>

