<template>
  <div>
    <router-link to="/person/msg/in" class="link-type">
      <svg-icon icon-class="message"/>
    </router-link>
  </div>
</template>

<script>
import { queryData} from "@/api/sys/dataset";
export default {
  name: 'RuoYiMsg',
  data() {
    return{
      timer: null,
      count: 0
    }
  },
  mounted() {
    this.getMessage();
    this.timer = setInterval(this.getMessage, 600000);
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  methods: {
    getMessage() {
      let $this = this;
      queryData({code:'unread_msg'}).then(response => {
        this.count = parseInt(response.data);
        if(this.count <= 0)return;

        var n = this.$notify({
          title: '消息提示',
          message: '您有'+this.count+'条未读消息',
          duration: 0,
          onClick: function(){
            $this.$router.push('/person/msg/in');
            n.close();
          }
        });

      });
    }
  }
}
</script>
