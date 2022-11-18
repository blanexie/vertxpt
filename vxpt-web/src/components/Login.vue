<template>
  <div class="by">
    <a-card class="login-card">
      <a-tabs centered>
        <a-tab-pane key="1" tab="登录">
          <a-form :model="loginFormState" name="normal_login" class="login-form" @finish="loginReq"
                  @finishFailed="onFinishFailed">
            <a-form-item label="用户名" name="nickName" :rules="[{ required: true, message: '请输入用户名!' }]">
              <a-input v-model:value="loginFormState.nickName">
                <template #prefix>
                  <UserOutlined/>
                </template>
              </a-input>
            </a-form-item>

            <a-form-item label="密&nbsp;&nbsp;&nbsp;码" name="password" :rules="[{ required: true, message: '请输入密码!' }]">
              <a-input-password v-model:value="loginFormState.password">
                <template #prefix>
                  <LockOutlined/>
                </template>
              </a-input-password>
            </a-form-item>
            <a-form-item>
              <a-form-item name="remember" no-style>
                <a-checkbox v-model:checked="loginFormState.remember">记住我</a-checkbox>
              </a-form-item>
              <a class="login-form-forgot" href="">忘记密码</a>
            </a-form-item>

            <a-form-item>
              <a-button type="primary" html-type="submit" class="login-form-button">登录</a-button>
            </a-form-item>
          </a-form>
        </a-tab-pane>

        <a-tab-pane key="2" tab="注册" force-render>
          <a-form :model="registerFormState" name="normal_register" class="register-form" @finish="registerReq">
            <a-form-item label="用户名" name="nickName" :rules="[{ required: true, message: '请输入用户名!' }]">
              <a-input v-model:value="registerFormState.nickName">
                <template #prefix>
                  <UserOutlined/>
                </template>
              </a-input>
            </a-form-item>
            <a-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;箱" name="email"
                         :rules="[{ required: true, message: '请输入注册邮箱!' }]">
              <a-input v-model:value="registerFormState.email">
                <template #prefix>
                  <MailOutlined/>
                </template>
              </a-input>
            </a-form-item>
            <a-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码" name="password"
                         :rules="[{ required: true, message: '请输入密码!' }]">
              <a-input-password v-model:value="registerFormState.password">
                <template #prefix>
                  <LockOutlined/>
                </template>
              </a-input-password>
            </a-form-item>
            <a-form-item label="邀请码" name="code" :rules="[{ required: true, message: '请输入邀请码!' }]">
              <a-input-password v-model:value="registerFormState.code">
                <template #prefix>
                  <UsergroupAddOutlined/>
                </template>
              </a-input-password>
            </a-form-item>

            <a-form-item label="性&nbsp;&nbsp;&nbsp;&nbsp;别" name="sex" :rules="[{ required: true, message: '请选择性别!' }]">
              <a-radio-group v-model:value="registerFormState.sex">
                <a-radio :value="1">男</a-radio>
                <a-radio :value="2">女</a-radio>
              </a-radio-group>
            </a-form-item>

            <a-form-item>
              <a-button type="primary" html-type="submit" class="login-form-button">
                注册
              </a-button>
            </a-form-item>
          </a-form>
        </a-tab-pane>
      </a-tabs>
    </a-card>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, computed} from "vue";
import {UserOutlined, LockOutlined, MailOutlined, UsergroupAddOutlined,} from "@ant-design/icons-vue";
import {post} from "../assets/js/axios-config";
import {Md5} from 'ts-md5'
import {router} from '../assets/js/router'
import {message} from 'ant-design-vue';


let md5 = new Md5()

interface LoginFormState {
  nickName: string;
  password: string;
  remember: boolean;
}

interface RegisterFormState {
  nickName: string;
  email: string;
  code: string;
  password: string;
  sex: number;
}

/**
 * 校验邮箱
 */
let checkEmail = (email: string) => {
  let emailPattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
  if (!emailPattern.test(email)) {
    message.error("请输入正确的邮箱格式")
    return false
  }
  return true
}

/**
 * 校验用户名
 */
let checkNickName = (nickName: string) => {
  let userNamePattern = /^[a-zA-Z0-9]{4,16}$/;
  if (!userNamePattern.test(nickName)) {
    message.error("用户名必须有数字字母组成，长度在4到16之间")
    return false
  }
  return true
}

/**
 * 校验用户名
 */
let checkPassword = (password: string) => {
  let pwdPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
  if (!pwdPattern.test(password)) {
    message.error("密码最少6位，必须由大小写字母，数字和特殊字符组成")
    return false
  }
  return true
}
/**
 * 注册请求
 */
let registerReq = (registerFormState: RegisterFormState) => {
  //用户名校验
  if (!checkNickName(registerFormState.nickName)) {
    return;
  }
  //密码校验
  if (!checkPassword(registerFormState.password)) {
    return;
  }
  //邮箱校验
  if (!checkEmail(registerFormState.email)) {
    return;
  }
  //发送请求
  post('/user/lr/register', registerFormState).then(res => {
    if (res.data.code == 200) {
      console.log("res", res)
      router.push('/home')
    } else {
      message.error(res.data.msg)
    }
  })


}

/**
 * 登录请求
 * @param loginFormState
 */
let loginReq = (loginFormState: LoginFormState) => {
  //用户名校验
  if (!checkNickName(loginFormState.nickName)) {
    return;
  }
  //密码校验
  if (!checkPassword(loginFormState.password)) {
    return;
  }

  let loginTime: number = new Date().getTime();
  let pwdMd5Hex = md5.appendStr(loginFormState.nickName + loginFormState.password + loginTime).end()
  post("/user/lr/login", {
    nickName: loginFormState.nickName,
    password: pwdMd5Hex,
    loginTime: loginTime
  }).then((res) => {
    if (res.data.code == 200) {
      router.push('/home')
    } else {
      message.error(res.data.msg)
    }
  });
}

export default defineComponent({
  components: {
    UserOutlined,
    LockOutlined,
    MailOutlined,
    UsergroupAddOutlined,
  },
  setup() {
    const loginFormState = reactive<LoginFormState>({
      nickName: "",
      password: "",
      remember: true,
    });
    const registerFormState = reactive<RegisterFormState>({
      nickName: "",
      password: "",
      email: "",
      code: "",
      sex: 1,
    });

    const onFinishFailed = (errorInfo: any) => {
      console.log("Failed:", errorInfo);
    };

    return {
      loginFormState,
      registerFormState,
      loginReq,
      registerReq,
    };
  },
});
</script>

<style scoped>
.by {
  min-height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-card {
  width: 440px;
  padding: 0 20px;
}

.login-form-forgot {
  float: right;
}
</style>
