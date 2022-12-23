<template>
  <div>
    <el-container>
      <el-header class="homeHeader">
        <div class="title">管理系统</div>
        <el-dropdown class="userInfo" @command="commandHandler">
            <span class="el-dropdown-link">
               {{user.username}}<i> <img :src="user.headImage" ></i>
            </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item command="logout">注销登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router unique-opened>
            <el-submenu :index="index" v-for="(item,index) in this.$router.options.routes"
                        :key="index" v-if="!item.hidden">
              <template slot="title"><i class="el-icon-location"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item :index="children.path"
                            v-for="(children,indexj) in item.children">{{ children.name }}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb  v-if="this.$router.currentRoute.path !='/home'">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
            欢迎进入后端系统
          </div>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem('user'))
    }
  },
  methods: {
    commandHandler(command) {
      if (command == 'logout') {
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //this.$message('' + command);
          this.postRequest('/logout');
          window.sessionStorage.removeItem('tokenStr');
          window.sessionStorage.removeItem('user');
          this.$router.replace('/');
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });


      }
    }
  }
}
</script>

<style>
.homeHeader {
  background: #409eff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px;
}

.homeHeader .title {
  font-family: 华文楷体;
  font-size: 35px;
  color: white;
}

.homeHeader.userInfo{
  cursor: pointer;
}

.el-dropdown-link img {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
}
.homeWelcome{
  text-align: center;
  font-size: 30px;
  font-family: 华文楷体;
  color: #409eff;
  padding-top: 50px;
}
</style>