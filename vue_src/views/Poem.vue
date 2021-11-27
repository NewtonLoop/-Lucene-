<template>
<div style="margin: 25px">
  <el-page-header @back="goBack" >
  </el-page-header>
    <h1>{{this.$route.params.title}}</h1>
  <el-divider content-position="right">海子</el-divider>
  <div class="grid-content white" style="white-space: pre-wrap;text-align: center;" v-html="this.$route.params.contain">

  </div>
</div>
</template>

<script>
    export default {
        name: "Poem",
      data(){
          return{
            title:'',
            contain:''
          };
      },
      methods: {
        goBack() {
          console.log('go back');
          this.$router.push({
            path: "/",
            params: {index : this.$route.params.index}
          });
        }
      },
      mounted() {

      }
    }
</script>

<style scoped>

</style>
