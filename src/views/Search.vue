<template>
  <div id="app" class="search_container" style="height: 100%; ">

    <el-row>
      <el-col :span="24">
        <div class="grid-content1 bg-purple-dark"
             style="height: 80px; padding-left: 10px; padding-right: 10px; margin-left: 7px; margin-right: 6px;margin-top: 0;padding-top: 0;vertical-align: center">
          <h2 style="margin-top: 0; padding-top: 35px; padding-right: 1500px; font-weight: lighter; font-family: Bahnschrift; color: #f4f4f5">MyProse</h2>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="5" style="height: 610px; margin: 5px">
      <el-col :span="4" style="height: 610px">
        <div class="grid-content bg-grey-light">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#e9e9eb"
            text-color="#909399"
            active-text-color="#909399"
            style="height: 610px">



            <el-submenu index="1">
              <template slot="title" >
                <i class="el-icon-location" slot="reference"></i>
                <span><el-popover
                  placement="top-start"
                  style="padding: 0px"
                  width="200"
                  trigger="hover">
                  <el-image :src='wzqimg'></el-image>
                  <el-button slot="reference" style="background: #e9e9eb; padding: 0;border-color: #e9e9eb">汪曾祺</el-button>
                </el-popover></span>

              </template>
              <el-menu-item-group>
                <template slot="title">散文</template>
                <el-menu-item id="rjcm" v-on:click="r_open()" index="1-1">人间草木</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="诗歌 小说 杂记">
                <el-menu-item v-on:click="z_open()" index="1-3">作品自选集</el-menu-item>
              </el-menu-item-group>
              <!--                            <el-submenu index="1-4">-->
              <!--                                <template slot="title">选项4</template>-->
              <!--                                <el-menu-item index="1-4-1">选项1</el-menu-item>-->
              <!--                            </el-submenu>-->
            </el-submenu>

            <el-submenu index="2">
              <template slot="title" >
              <i class="el-icon-location"></i>
              <span slot="title">
                <el-popover
                  placement="top-start"
                  style="padding: 0px"
                  width="200"
                  trigger="hover">
                  <el-image :src='lximg'></el-image>
                  <el-button slot="reference" style="background: #e9e9eb; padding: 0;border-color: #e9e9eb">鲁迅</el-button>
                </el-popover>
              </span>
              </template>

              <el-menu-item-group>
                <template slot="title">散文</template>
                <el-menu-item  v-on:click="zh_open()" index="2-1">朝花夕拾</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="诗歌 小说 杂记">
                <el-menu-item v-on:click="e_open()" index="1-3">二心集</el-menu-item>
              </el-menu-item-group>

            </el-submenu>
            <el-menu-item index="3" disabled>
              <i class="el-icon-location"></i>
              <span slot="title">王小波</span>
            </el-menu-item>
            <el-menu-item index="4" disabled>
              <i class="el-icon-location"></i>
              <span slot="title">张爱玲</span>
            </el-menu-item>
            <el-menu-item index="5" disabled>
              <i class="el-icon-location"></i>
              <span slot="title">期待更多...</span>
            </el-menu-item>
          </el-menu>

        </div>
      </el-col>
      <el-col :span="10" style="height: 100%">
        <div class="grid-content bg-purple-light">


          <div style="margin: 0px; padding-bottom: 0px; ">
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" size="small"
                     style="padding-right: 50px; padding-bottom: 10px;">
              <el-form-item label="标题" style="padding-top: 10px">
                <el-input v-model="formLabelAlign.name" placeholder="请输入标题"></el-input>
              </el-form-item>
              <el-form-item label="作者">
                <el-input v-model="formLabelAlign.region" placeholder="请输入作者"></el-input>
              </el-form-item>
              <el-form-item label="全文">
                <el-input v-model="formLabelAlign.type" placeholder="请输入检索词"></el-input>
                <el-button type="primary" icon="el-icon-search" style="margin: 10px" v-on:click="search">搜索</el-button>

              </el-form-item>


            </el-form>


            <div class="outbox" style="height: 370px">
              <ul ref="flink" v-for="items in result">
                <el-tag type="success" style="margin-right: 5px">{{items.author}}</el-tag>
                <el-link type="primary" ref="slink" v-on:click="getTitle(items.title)" id="items.title">
                  {{items.title}}
                </el-link>
                <ul>
                  <el-link type="info">{{items.summary1}}</el-link>
                </ul>
              </ul>
            </div>


            <!--            <ul v-for="i in index" :key="page">-->
            <!--              <el-tag type="success" style="margin-right: 5px">{{result[6*page+i].author}}</el-tag>-->
            <!--              <el-link type="primary" ref="slink" v-on:click="getTitle(result[6*page+i].title)" id="result[6*page+i].title">{{items.title}}</el-link>-->
            <!--              <ul><el-link type="info">{{result[6*page+i].summary1}}</el-link></ul>-->
            <!--            </ul>-->


          </div>


        </div>
      </el-col>
      <el-col :span="10" class="grid-content bg-purple-light"
              style="height: 610px; margin-top: 0;margin-bottom: 5px; padding: 5px;
              white-space: pre-wrap;
               margin-bottom: 20px;">


        <div style="margin: 5px; padding: 5px">
          <h2 style="font-family: 'Hiragino Sans GB'; padding-top: 0;margin-top: 10px">{{Titems.title}}</h2>

          <el-progress type="dashboard" :percentage="percentage" :color="colors">emo</el-progress>

          <el-tag closable type="">
            {{Titems.key1}}
          </el-tag>
          <el-tag closable type="success">
            {{Titems.key2}}
          </el-tag>
          <el-tag closable type="info">
            {{Titems.key3}}
          </el-tag>
          <el-tag closable type="warning">
            {{Titems.key4}}
          </el-tag>
          <el-tag closable type="danger">
            {{Titems.key5}}
          </el-tag>

          <el-divider content-position="left" style="font-family: 'Hiragino Sans GB'">{{Titems.author}}</el-divider>

          <div class="outbox"
               style="text-align: left;font-family: 'Hiragino Sans GB';font-size: 12px; line-height: 25px; margin: 20px; padding: 5px;height: 320px">
            <p v-html="brightenKeyword(Titems.contain, formLabelAlign.name+formLabelAlign.region+formLabelAlign.type)"
               style="margin-bottom: 5px"></p>
          </div>
        </div>


      </el-col>


    </el-row>

  </div>
</template>


<script>
  import axios from "axios";

  const SearchOptions = ['题目', '作者', '内容', '默认'];
  export default {
    name: "Search",
    data() {
      return {
        wzqimg:require('../img/wzq.jpg'),
        lximg:require('../img/lx.jpg'),
        visible: false,
        percentage: '',
        colors: [
          {color: '#f56c6c', percentage: 20},
          {color: '#e6a23c', percentage: 40},
          {color: '#5cb87a', percentage: 60},
          {color: '#1989fa', percentage: 80},
          {color: '#6f7ad3', percentage: 100}
        ],
        page: '0',
        pagestep: '',
        index: '',
        count: 6,
        Titems: {
          title: '',
          author: '',
          contain: '',
          key1: '',
          key2: '',
          key3: '',
          key4: '',
          key5: '',
          summary1: '',
          summary2: '',
          emotion: '',
          slidetype: ''
        },
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },
        result: '',
        toarray: [],
        checkedCities: ['默认'],
        opts: SearchOptions,
        querybuilder: {
          cat: "contain",
          query: "都是"
        }
      };
    },

    methods: {

      handleChange: function () {
        if ((this.result.length - 6 * (page + 1)) > 6) {
          this.index = 6;
        } else {
          this.index = this.result.length - 6 * (page + 1);
        }
      },

      brightenKeyword(val, keyword) {
        if (keyword.length > 0) {
          let keywordArr = keyword.split("");
          val = val + "";
          keywordArr.forEach(item => {
            if (val.indexOf(item) !== -1 && item !== "") {
              val = val.replace(
                new RegExp(item, 'g'),
                '<font color="#f75353">' + item + "</font>"
              );
            }
          });
          return val;
        } else {
          return val;
        }
      },


      getTitle: function (title) {
        console.log(title)
        this.result.forEach(item => {

          if (item.title == title) {
            this.Titems.title = title;
            this.Titems.author = item.author;
            this.Titems.contain = item.contain;
            this.Titems.key1 = item.key1;
            this.Titems.key2 = item.key2;
            this.Titems.key3 = item.key3;
            this.Titems.key4 = item.key4;
            this.Titems.key5 = item.key5;
            this.Titems.summary1 = item.summary1;
            this.Titems.summary2 = item.summary2;
            this.percentage = parseFloat(item.emotion) * 100;
          }


        })
      },

      search: function () {
        let formData = new FormData();
        formData.append("title", this.formLabelAlign.name);
        formData.append("author", this.formLabelAlign.region);
        formData.append("contain", this.formLabelAlign.type)
        axios.post('/do/prose/test', formData).then(function (response) {
          // console.log(response);
          console.log("post!")
        }).catch(function (error) {
          console.log(formData);
          console.log(error);
        });
        axios.get('/do/prose/test').then(res => {
          // console.log(res.data);
          this.result = res.data;
          this.pagestep = Math.floor(this.result.length / 6);
          console.log(this.pagestep);
          if ((this.result.length - 6 * (page + 1)) > 6) {
            this.index = 6;
          } else {
            this.index = this.result.length - 6 * (page + 1);
          }
        }).catch(err => {
          console.log(err)
        })
        console.log(this.querybuilder.cat);

        console.log(this.pagestep)

      },

      zh_open:function(){
        window.open("https://book.douban.com/subject/1449352/");
      },

      e_open:function(){
        window.open("https://book.douban.com/subject/25756402/");
      },

      r_open: function () {
        axios.get('/do/prose/test').then(res => {
          console.log(res)
          reso = JSON.parse(JSON.stringify(res));
          console.log("zaaa")
        }).catch(err => {
          console.log(err)
        })
        window.open("https://book.douban.com/subject/25849895/");
      },

      z_open: function () {
        window.open('https://book.douban.com/subject/1950196/');
        console.log(reso)
      }
      // 组件的方法
    },
    watch: {
      // watch擅长处理的场景：一个数据影响多个数据
    },
    computed: {
      // computed擅长处理的场景：一个数据受多个数据影响
    },
    beforeCreate: function () {
      // 在实例初始化之后，数据观测(data observer) 和 event/watcher 事件配置之前被调用。
    },
    created: function () {

      // this.$axios.get('http://localhost:8080/do/prose/proseAll').then(res => {
      //   this.tableData = res.data
      //   console.log(res.data);
      // }).catch(function (error) {
      //   console.log(error);
      // });
      // 实例已经创建完成之后被调用。在这一步，实例已完成以下的配置：数据观测(data observer)，属性和方法的运算， watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见。
    },
    beforeMount: function () {
      // 在挂载开始之前被调用：相关的 render 函数首次被调用。
    },
    mounted: function () {

      // axios
      //   .get('http://10.27.244.180:8080/do/prose/proseAll')
      //   .then(response => (this.info = response))
      //   .then((data) => {
      //     console.log(data);
      //   })
      //   .catch((err) => {
      //   console.log(err);
      // });
      // 编译好的HTML挂载到页面完成后执行的事件钩子
      // el 被新创建的 vm.$el 替换，并挂载到实例上去之后调用该钩子。
      // 此钩子函数中一般会做一些ajax请求获取数据进行数据初始化

      axios.get('/do/prose/proseAll').then(res => {
        console.log(res)
        // reso = JSON.parse(JSON.stringify(res));
      }).catch(err => {
        console.log(err)
      })
      console.log("Home done");
    },
    beforeUpdate: function () {
      // 数据更新时调用，发生在虚拟 DOM 重新渲染和打补丁之前。 你可以在这个钩子中进一步地更改状态，这不会触发附加的重渲染过程。
    },
    updated: function () {
      // 由于数据更改导致的虚拟 DOM 重新渲染和打补丁，在这之后会调用该钩子。
      // 当这个钩子被调用时，组件 DOM 已经更新，所以你现在可以执行依赖于 DOM 的操作。然而在大多数情况下，你应该避免在此期间更改状态，因为这可能会导致更新无限循环。
      // 该钩子在服务器端渲染期间不被调用。
    },
    beforeDestroy: function () {
      // 实例销毁之前调用。在这一步，实例仍然完全可用。
    },
    destroyed: function () {
      // Vue 实例销毁后调用。调用后，Vue 实例指示的所有东西都会解绑定，所有的事件监听器会被移除，所有的子实例也会被销毁。 该钩子在服务器端渲染期间不被调用。
    }
  };
</script>


<!--<script type="text/javascript">-->
<!--  function r_open() {-->
<!--    console.log(1)-->
<!--    window.open("https://book.douban.com/subject/25849895/");-->
<!--  }-->

<!--  function z_open() {-->
<!--    window.open('https://book.douban.com/subject/1950196/');-->
<!--  }-->

<!--  export default {-->
<!--    name: "Search"-->
<!--  }-->
<!--</script>-->

<style scoped>
  .bg-purple-dark {
    background: #909399;
  }


  .bg-grey-light {
    background: #e9e9eb;
  }

  .bg-purple-light {
    background: #f4f4f5;
  }

  .grid-content1 {
    border-radius: 4px;
  }

  .grid-content {
    border-radius: 4px;
    height: 610px;
  }

  .outbox {
    overflow-y: auto;
  }

  .search_container {
    height: 600px;
    border-radius: 4px;
    background-color: #ffffff;
  }

  .input_form input {
    width: 600px;
    height: 30px;
    border-radius: 0;
    transition: all 0.5s ease 0s;
  }

  .input_form input:focus {
    width: 600px;
    height: 30px;
    box-shadow: 0 0 1px;
  }

  /*搜索框*/
  .bar1 {
    background: #e5e9f2;
    border-radius: 4px;
  }

  .bar1 input {
    border: 2px solid #9E9C9C;
    border-radius: 3px;
    background: #ffffff;
    color: #9E9C9C;
    padding: 5px;
    margin: 5px;
  }

  .bar1 button {
    top: 0;
    right: 0;
    height: 42px;
    width: 100px;
    background: #545c64;
    border-radius: 3px 3px 3px 3px;
    transform: translateY(6px);
  }

  .bar1 button:before {
    content: "\f002";
    font-family: FontAwesome;
    font-size: 32px;
    color: #ffffff;
  }

  /*  搜索框*/

</style>


