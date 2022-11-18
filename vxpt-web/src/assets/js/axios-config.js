import axios from 'axios';
import {router} from './router';

const config = {
    baseURL: '//localhost:9092/api',
    timeout: 60000,
};


export function post(url, params) {
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            url: config.baseURL + url,
            data: JSON.stringify(params),
            timeout: config.timeout,
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
        }).then(response => {
            if (response.status == 200) {
                //根据实际情况进行更改
                resolve(response)
            } else {
                reject(response)
            }
        })
    })
}

export function get(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(config.baseURL + url, {
            params
        }).then(function (response) {
            if (response.status == 200) {
                resolve(response)
            } else {
                reject(response)
            }
        })
    })
}


axios.interceptors.response.use(
    response => {
        //拦截响应，做统一处理
        if (response.data.code) {
            switch (response.status) {
                case 301:
                    router.push("/home")
                default:
                    console.log("response", response);
            }
        }
        return response
    },
    //接口错误状态处理，也就是说无响应时的处理
    error => {
        return Promise.reject(error.response.status) // 返回接口返回的错误信息
    })

