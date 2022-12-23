<template>
  <el-form ref="form" :model="form"  :rules= rules label-width="80px" >
    <el-form-item style="height: 50px"></el-form-item>
    <el-form-item label="商品名称" prop="commodity">
      <el-input v-model="form.commodityName" style="width: 500px" ></el-input>
    </el-form-item>
    <el-form-item label="商品描述" prop="information">
      <el-input v-model="form.commodityInformation" style="width: 500px"></el-input>
    </el-form-item>
    <el-form-item label="商品价格" prop="price">
      <el-input v-model="form.commodityPrice" style="width: 500px"></el-input>
    </el-form-item>
    <el-form-item label="商品图片">
      <el-upload
          class="upload-demo"
          action='D:/PHPlearn/image'
          :http-request="httpRequest"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-upload="beforeUpload"
          :before-remove="beforeRemove"
          :on-change="getFile"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "AddCommodity",
  data() {
    return {
      form: {
        commodityName: '',
        commodityInformation: '',
        commodityPrice: '',
        commodityImages: ''
      },
      fileList: [],
      fd: {},
      rules: {
        // commodity: [{required: true, message: '请输入商品名称', trigger: 'blur' }],
        // information: [ {required: true, message: '请输入商品描述', trigger: 'blur' }],
        // price: [{required: true, message: '请输入商品价格', trigger: 'blur' }]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid)=>{
        if(valid){
          if (this.fileList.length <= 0) {
            this.$message.error("至少上传一个！");
            return;
          }
          this.fd.append('commodityName',this.form.commodityName);
          this.fd.append('commodityInformation',this.form.commodityInformation)
          this.fd.append('commodityPrice' , this.form.commodityPrice)
          this.postRequest('/commodity-info/upload',this.fd).then(resp=>{
              if(resp){
                this.$message(resp.message);
                this.fd= {};
                this.fileList = [];
                location.reload();
              }
          })
        }
      })
    },
    getFile(file, fileList){
      this.fileList = fileList;
      const fd = new FormData();
      this.fd = fd;
    },
    beforeUpload(file){
      let extension = file.name.substring(file.name.lastIndexOf('.')+1);
      console.log(extension);
      if(extension != "jpg"){
        this.$message.warning("文件类型不对！");
        return false;
      }
    },
    httpRequest(param){
      const fileobj = param.file;
      this.fd.append('image',fileobj);
      console.log(this.fd.get('image'));
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      let extension = file.name.substring(file.name.lastIndexOf('.')+1);
      console.log(extension);
      if(extension != "jpg" ){
        return ;
      }
         return this.$confirm(`确定移除 ${ file.name }？`);
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}

</script>

