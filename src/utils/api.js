import axios from "axios";
import {Message} from "element-ui";
import router from "@/router";

// axios 请求拦截器
axios.interceptors.request.use(config=> {
    //若sessionStorage中存在tokenStr
    let tokenString = window.sessionStorage.getItem('tokenStr');
    if (tokenString) {
        config.headers['Authorization'] = tokenString;
    }
    return config;
}, error => {
    console.log(error);
});

axios.interceptors.response.use(success=>{
        if(success.status && success.status == 200){
            if(success.data.code == 500 || success.data.code == 401 || success.data.code == 403){
                Message.error({message: '访问出错'});
                return ;
            }
            return success.data;
        }
},error => {
    if(error.response.code ==504 || error.response.code ==404){
        Message.error({message: '服务器炸了'});
    }else if(error.response.code == 403){
        Message.error({message: '权限不足'});
    }else if(error.response.code==401){
        Message.error({message: '未登录'});
        router.replace('/')
    }else {
        Message.error({message: '未知错误'});
    }
    return ;
});

//前置路径
let base = '';
//封装请求
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    });
}

//put请求
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    });
}

//get请求
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params
    });
}

//delete请求
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params
    });
}