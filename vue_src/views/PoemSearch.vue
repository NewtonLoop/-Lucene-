<template>



    <el-tabs v-model="activeName" @tab-click="handleClick" style="padding-left: 5%; padding-right: 5%; padding-top: 2%">
      <el-tab-pane label="主页" name="first">

        <el-row :gutter="20">
          <el-col :span="6"><div class="grid-content bg-purple">
<!--            海子头像-->
            <el-image :src='tx' style="padding-top: 5px"></el-image>

            <h3>刘俊含</h3>

            <div style="margin-top: 5px">山东泰安人</div>

          </div></el-col>
          <el-col :span="18"><div class="grid-content white">

<!--            走马灯-->

            <div class="block bg-purple">
              <span class="demonstration bg-purple"></span>
              <el-carousel class="white" height="500px" :interval="30000">
                <el-carousel-item>
                <h2 class="small white" style="text-align: left; padding-left: 25px">面朝大海，春暖花开</h2>

                  <el-row :gutter="20" style="padding-top: 50px; padding-left: 25px">
                    <el-col :span="8" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="sea_text.p1">
                    </div></el-col>
                    <el-col :span="8" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="sea_text.p2">

                    </div></el-col>
                    <el-col :span="8" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="sea_text.p3">

                    </div></el-col>

                  </el-row>
              </el-carousel-item>
                <el-carousel-item>
                  <h2 class="small" style="text-align: left; padding-left: 25px">山楂树</h2>
                  <el-row :gutter="20" style="padding-top: 50px; padding-left: 25px">
                    <el-col :span="12" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="tree_text.p1">
                    </div></el-col>
                    <el-col :span="12" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="tree_text.p2">

                    </div></el-col>

                  </el-row>
                </el-carousel-item>
                <el-carousel-item>
                  <h2 class="small" style="text-align: left; padding-left: 25px">思念前生</h2>
                  <el-row :gutter="20" style="padding-top: 50px; padding-left: 25px">
                    <el-col :span="12" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="previouslife.p1">
                    </div></el-col>
                    <el-col :span="12" style="white-space: pre-wrap; font-size: 15px"><div class="grid-content white" style="text-align: left" v-html="previouslife.p2">

                    </div></el-col>

                  </el-row>
                </el-carousel-item>
              </el-carousel>
            </div>

          </div></el-col>

        </el-row>
      </el-tab-pane>
      <el-tab-pane label="海子的诗" v-on:click="get_short" name="second">

        <el-row :gutter="20">
          <el-col :span="8"><div class="grid-content bg-purple">
            <h2>短篇</h2>
            <div v-for="items in this.short" class="scroll">
              <el-link  target="_blank"  v-on:click="jump_to_poem(items.title,2)">{{items.title}}</el-link>
            </div>
          </div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple">
            <h2>长篇</h2>
            <div v-for="items in this.long" class="scroll">
              <el-link  target="_blank"  v-on:click="jump_to_poem(items.title)">{{items.title}}</el-link>
            </div>
          </div></el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <h2>文论</h2>
              <div v-for="items in this.medium" class="scroll">
                <el-link  target="_blank"  v-on:click="jump_to_poem(items.title)">{{items.title}}</el-link>
              </div>
            </div>
            <div class="scroll">

            </div>

          </el-col>
        </el-row>


      </el-tab-pane>
      <el-tab-pane label="诗歌搜索" name="third">

        <div style="margin-top: 15px; padding-left: 15%; padding-right: 15%">
          <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择">
              <el-option label="诗题" value="1"></el-option>
              <el-option label="诗体" value="2"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" v-on:click="all_searchs"></el-button>
          </el-input>
        </div>

        <div v-for="item in this.result"  >
          <div style="margin: 30px">
            <div>
              <el-link target="_blank" v-on:click="jump_to_poem(item.title)">{{item.title}}</el-link>
            </div>
            <el-link type="info" v-on:click="jump_to_poem(item.title)">{{item.first_line}}</el-link>
          </div>

        </div>



      </el-tab-pane>
      <el-tab-pane label="拼贴诗" name="fourth">

        <el-row :gutter="20">
          <el-col :span="12" style="padding: 25px"><div class="grid-content white">
            <el-input
              placeholder="请输入内容"
              v-model="input"
              clearable>
            </el-input>
            <el-button icon="el-icon-search" circle style="margin: 25px" v-on:click="stitching_search"></el-button>
            <div class="scroll" style="height: 400px">
            <div v-for="item in this.sresult">
              <div style="margin: 20px">
                <div style="font-size: 18px; color: #606266">{{item.line}}</div>

                <div style="font-size: 16px; color: #909399; margin: 9px;text-align: right">      ——{{item.title}}</div>
              </div>
            </div>

            </div>

          </div></el-col>
          <el-col :span="12"><div class="grid-content white">
            <el-row :gutter="20">
              <el-col :span="12"><div class="grid-content white" style=" color: #909399; text-align: right">
                选择诗歌行数
              </div></el-col>
              <el-col :span="12"><div class="grid-content white">
                <el-input-number v-model="num" @change="handleChange" :min="1" :max="20" label="描述文字"></el-input-number>
              </div></el-col>
            </el-row>

            <el-input
              style="padding-left: 20%; padding-right: 20%; padding-top: 5%; width: 150px; font-size: large;"
              type="textarea"
              autosize
              placeholder="标题"
              v-model="input_title">
            </el-input>


            <div class="scroll" style="height: 600px">
        <div v-for="i in this.num">
          <el-input
            style="padding-left: 20%; padding-right: 20%; padding-top: 5%; width: 220px"
            type="textarea"
            autosize
            placeholder="请输入诗句"
            v-model="textarea1[i]">
          </el-input>
        </div>

            </div>




          </div></el-col>
        </el-row>

      </el-tab-pane>
    </el-tabs>




</template>

<script>
  import axios from "axios";


    export default {
        name: "PoemSearch",
      //标签页
      data() {
        return {

          inputValue:'',

          input_title:'',

          textarea1:['','','','','','','','','','','','','','','','','','','',''],




          num:1,

          stitchpoem:{
            title:'',
            line:''
          },

          input: '',

          sresult:'',
          result:'',

          input3: '',
          select: '',

          all_search:{
            title:'',
            label:'',
            contain:''
          },

          poem:{
            title:'',
            label:'',
            contain:'',
            firstline:''
          },

          short:'',
          shortlength:'',

          medium:'',
          mediumlength:'',

          long:'',
          longlength:'',

          sea_text:{
            p1: "从明天起，做一个幸福的人\n喂马，劈柴，周游世界" +
              "\n从明天起，关心粮食和蔬菜" +
              "\n我有一所房子，面朝大海，春暖花开",
            p2: "从明天起，和每一个亲人通信" +
              "\n告诉他们我的幸福，那幸福的闪电告诉我的" +
              "\n我将告诉每一个人" +
              "\n给每一条河每一座山取一个温暖的名字",
            p3: "陌生人，我也为你祝福" +
              "\n愿你有一个灿烂的前程" +
              "\n愿你有情人终成眷属" +
              "\n愿你在尘世获的幸福" +
              "\n我也愿面朝大海，春暖花开"
          },
          tree_text:{
            p1:"今夜我不会遇见你\n" +
              "今夜我遇见了世上的一切\n" +
              "但不会遇见你。\n" +
              "\n" +
              "一棵夏季最后\n" +
              "火红的山楂树\n" +
              "象一辆高大女神的自行车\n" +
              "象一女孩 畏惧群山\n" +
              "呆呆站在门口\n" +
              "她不会向我\n" +
              "跑来！",
            p2:"我走过黄昏\n" +
              "型风吹向远处的平原\n" +
              "我将在暮色中抱住一棵孤独的树干\n" +
              "山楂树！ 一闪而过 啊！ 山楂\n" +
              "\n" +
              "我要在你的乳房下坐到天亮。\n" +
              "又小又美丽的山楂的乳房\n" +
              "在高大女神的自行车上\n" +
              "在农奴的手上\n" +
              "在夜晚就要熄灭"
          },
          previouslife:{
            p1:"庄子在水中洗手洗完了手\n" +
              "\n" +
              "手掌上一片寂静庄子在水中洗身\n" +
              "\n" +
              "身子是一匹布那布上粘满了\n" +
              "\n" +
              "水面上漂来漂去的声音庄子想混入\n" +
              "\n" +
              "凝望月亮的野兽骨头一寸一寸",
            p2:"在肚脐上下象树枝一样长着\n" +
              "\n" +
              "也许庄子就是我摸一摸树皮\n" +
              "\n" +
              "开始对自己的身子亲切\n" +
              "\n" +
              "亲切又苦恼月亮触到我\n" +
              "\n" +
              "仿佛我是光着身子进出\n" +
              "\n" +
              "母亲如门对我轻轻开着"
          },
          activeName: 'first',
          tx: require('../img/touxiang.jpg')
        };
      },
      methods: {



        handleChange(value) {
          console.log(value);
        },

        stitching_search:function(){
          console.log(this.input);
            let formData = new FormData();
            formData.append("key", this.input);
            axios.post('/Haizi/Stitching', formData).then(function (response) {
              // console.log(response);
              console.log("post!")
            }).catch(function (error) {
              console.log(formData);
              console.log(error);
            });
            axios.get('/Haizi/Stitching/get').then(res => {
              // console.log(res.data);
              this.sresult = res.data;
              console.log(res)
            }).catch(err => {
              console.log(err)
            })
          },

          //全搜索
        all_searchs: function(){
          console.log(this.select);

          if(this.select==1){
            this.all_search.title = this.input3;
          }else {
            this.all_search.contain = this.input3;
          }
          let formData = new FormData();
          formData.append("title", this.all_search.title);
          formData.append("label", this.all_search.label);
          formData.append("contain", this.all_search.contain);
          axios.post('/Haizi/Allsearch', formData).then(function (response) {
            // console.log(response);
            console.log("post!")
          }).catch(function (error) {
            console.log(formData);
            console.log(error);
          });
          axios.get('/Haizi/Allsearch/get').then(res => {
            // console.log(res.data);
            this.result = res.data;
            console.log(res)
          }).catch(err => {
            console.log(err)
          })



        },


        //  海子的诗 部分 方法
        //  检索所有诗 区分长篇 中篇 短篇
        //无限滚动
        //跳至诗歌界面
        jump_to_poem:function(title, ac){

          this.short.forEach(item => {
            if(item.title==title){
              this.poem.title = title;
              this.poem.contain = item.contain;
              this.poem.firstline = item.firstline;
              this.poem.label = item.label;
            }
          });

          this.medium.forEach(item => {
            if(item.title==title){
              this.poem.title = title;
              this.poem.contain = item.contain;
              this.poem.firstline = item.firstline;
              this.poem.label = item.label;
            }
          });

          this.long.forEach(item => {
            if(item.title==title){
              this.poem.title = title;
              this.poem.contain = item.contain;
              this.poem.firstline = item.firstline;
              this.poem.label = item.label;
            }
          });

          console.log(this.poem.contain);

          this.$router.push({
            path: "/poem",
            name: "Poem",
            params: {
              title: this.poem.title,
              contain: this.poem.contain,
              index: ac
            },
          });

        },

        get_short:function(){
          console.log("short_coming!")
        },

        handleClick(tab, event) {
          console.log(tab, event);
        }
      },
      mounted() {

        axios.get('/Haizi/short/get').then(res => {
          console.log(res)
          this.short = res.data;
          console.log("short!");
          console.log(this.short[0].contain);
        }).catch(err => {
          console.log(err)
        })
        axios.get('/Haizi/medium/get').then(res => {
          console.log(res)
          this.medium = res.data;
          console.log("medium");
          console.log(this.medium);
        }).catch(err => {
          console.log(err)
        })
        axios.get('/Haizi/long/get').then(res => {
          console.log(res)
          this.long = res.data;
          console.log("long!");
          console.log(this.long);
        }).catch(err => {
          console.log(err)
        })


      }
    };
</script>

<style scoped>


  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .scroll{
    overflow-y: auto;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .white{
    background: #ffffff;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
