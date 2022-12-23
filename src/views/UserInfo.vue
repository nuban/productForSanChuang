<template>
  <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
    <el-table-column
        label="id"
        prop="id">
    </el-table-column>
    <el-table-column
        label="用户名"
        prop="username">
    </el-table-column>
    <el-table-column
        label="注册时间"
        prop="createTime">
    </el-table-column>
    <el-table-column
        align="right">
      <template slot-scope="scope">
        <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      tableData: []
    }
  },
  mounted() {
    this.initData();
  },
  methods: {
    initData() {
      this.getRequest('/user/findall').then(resp => {
        if (resp) {
          this.tableData = resp.obj;
          console.log(this.tableData)
        }
      })
    },
    handleDelete(index, row) {
      this.deleteRequest("/user/delete/" + row.id).then(resp => {
        if (resp) {
          this.$message(resp.message);
          location.reload();
        }
      })
    }
  }
}
</script>

<style scoped>

</style>