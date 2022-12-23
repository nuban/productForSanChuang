<template>
  <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
    <el-table-column
        label="id"
        prop="id">
    </el-table-column>
    <el-table-column
        label="商品名称"
        prop="commodityName">
    </el-table-column>
    <el-table-column
        label="商品描述"
        prop="commodityInformation">
    </el-table-column>
    <el-table-column
      label="商品价格"
      prop="commodityPrice">
    </el-table-column>
    <el-table-column label="商品图片" prop="commodityImages">
      <template slot-scope="scope">
<!--          <img :src="'http://localhost:8088/'+scope.row.commodityImages" width="100px" height="100px"-->
<!--                style="cursor:pointer">-->
        <!-- 触发弹窗 - 图片改为你的图片地址 -->
        <img :id="'myImg'+scope.row.id" :src="'http://localhost:80/image/UploadImage/'+scope.row.commodityImages" width="100px" height="100px"
              class="style" alt="图片信息" @mouseover="bigimg(scope.row)" border="0.5px"  transition="0.3s">
        <!-- 弹窗 -->
        <div :id="'myModal'+scope.row.id" class="modal">

          <!-- 关闭按钮 -->
          <span :id="'close'+scope.row.id" class="close" onclick="document.getElementById('myModal'+scope.row.id).style.display='none'">&times;</span>

          <!-- 弹窗内容 -->
          <img class="modal-content" :id="'img'+scope.row.id">

          <!-- 文本描述 -->
          <div :id="'caption'+scope.row.id" class="caption"></div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
        label="申请审核时间"
        prop="createTime">
    </el-table-column>
    <el-table-column
        align="right">
      <template slot="header" slot-scope="scope">
        <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button
            size="mini"
            type="danger"
            @click="handleCheck(scope.$index, scope.row)">审核通过</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "CheckCommodity",
  data() {
    return {
      tableData: [],
      search: ''
    }

  },
  mounted() {
    this.inittabledata();
  },
  methods: {
    handleCheck(index, row) {
       this.postRequest('/commodity-info/save',row).then(resp=>{
         if(resp.code == 200){

           this.deleteRequest("/tempname/delete/"+row.id).then(resp=>{
             if (resp) {
                alert("审核通过");
                location.reload();
             }
           })
         }

       })
    },
    inittabledata(){
      this.getRequest('/tempname/findall').then(resp=>{
          if(resp){
            this.tableData = resp.obj;
          }
      })
    },
    bigimg(row){
      // alert("test");
      var modal = document.getElementById('myModal'+row.id);
      // 获取图片插入到弹窗 - 使用 "alt" 属性作为文本部分的内容
      var img = document.getElementById('myImg'+row.id);
      var modalImg = document.getElementById("img"+row.id);
      var captionText = document.getElementById("caption"+row.id);
      img.onclick = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
      }
      // 获取 <span> 元素，设置关闭按钮
      var span = document.getElementById("close"+row.id);
      // 当点击 (x), 关闭弹窗
      span.onclick = function () {
        modal.style.display = "none";
      }
    }

  }
}
</script>

<style scoped>
/* 触发弹窗图片的样式 */
/*#myImg {*/
/*  border-radius: 5px;*/
/*  cursor: pointer;*/
/*  transition: 0.3s;*/
/*}*/

/*#myImg:hover {opacity: 0.7;}*/
.style:hover,
.style{
  opacity: 1;
  cursor: pointer;
}

/* 弹窗背景 */
.modal {

  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* 图片 */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* 文本内容 */
.caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* 添加动画 */
.modal-content, caption {
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {-webkit-transform:scale(0)}
  to {-webkit-transform:scale(1)}
}

@keyframes zoom {
  from {transform:scale(0)}
  to {transform:scale(1)}
}

/* 关闭按钮 */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 小屏幕中图片宽度为 100% */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
</style>