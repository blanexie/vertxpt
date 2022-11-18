<template>
  <div class="by">
    <a-card class="login-card">
      <a-tabs centered>
        <a-tab-pane key="1" tab="登录">

        </a-tab-pane>

        <a-tab-pane key="2" tab="注册" force-render>

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

    const loginReq = (loginFormState: LoginFormState) => {
      if (loginFormState.remember) {
      }
      let loginTime: number = new Date().getTime();
      let pwdMd5Hex = md5.appendStr(loginFormState.nickName + loginFormState.password + loginTime).end()

      post("/user/login", {
        nickName: loginFormState.nickName,
        password: pwdMd5Hex,
        loginTime: loginTime
      }).then((res) => {
        console.log("res", res)
        if (res.data.code == 200) {
            
        }
      });
      console.log("Success:", loginFormState);
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log("Failed:", errorInfo);
    };

    return {
      loginFormState,
      registerFormState,
      loginReq,
      onFinishFailed,
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
