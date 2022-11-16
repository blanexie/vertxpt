<template>
  <div class="by">
    <a-card class="login-card">
      <a-tabs>
        <a-tab-pane key="1" tab="登录">
          <a-form
              :model="loginFormState"
              name="normal_login"
              class="login-form"
              @finish="onFinish"
              @finishFailed="onFinishFailed"
          >
            <a-form-item
                label="Username"
                name="username"
                :rules="[{ required: true, message: 'Please input your username!' }]"
            >
              <a-input v-model:value="loginFormState.username">
                <template #prefix>
                  <UserOutlined class="site-form-item-icon"/>
                </template>
              </a-input>
            </a-form-item>

            <a-form-item
                label="Password"
                name="password"
                :rules="[{ required: true, message: 'Please input your password!' }]"
            >
              <a-input-password v-model:value="loginFormState.password">
                <template #prefix>
                  <LockOutlined class="site-form-item-icon"/>
                </template>
              </a-input-password>
            </a-form-item>

            <a-form-item>
              <a-form-item name="remember" no-style>
                <a-checkbox v-model:checked="loginFormState.remember">Remember me</a-checkbox>
              </a-form-item>
              <a class="login-form-forgot" href="">Forgot password</a>
            </a-form-item>

            <a-form-item>
              <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button">
                Log in
              </a-button>
              Or
              <a href="">register now!</a>
            </a-form-item>
          </a-form>
        </a-tab-pane>
        <a-tab-pane key="2" tab="注册" force-render>

          <a-form
              :model="loginFormState"

              name="normal_login"
              class="login-form"
              @finish="onFinish"
              @finishFailed="onFinishFailed"
          >
            <a-form-item
                label="Username"
                name="username"
                :rules="[{ required: true, message: 'Please input your username!' }]"
            >
              <a-input v-model:value="loginFormState.username">
                <template #prefix>
                  <UserOutlined class="site-form-item-icon"/>
                </template>
              </a-input>
            </a-form-item>
            <a-form-item
                label="email"
                name="email"
                :rules="[{ required: true, message: 'Please input your username!' }]"
            >
              <a-input v-model:value="loginFormState.username">
                <template #prefix>
                  <UserOutlined class="site-form-item-icon"/>
                </template>
              </a-input>
            </a-form-item>
            <a-form-item
                label="Password"
                name="password"
                :rules="[{ required: true, message: 'Please input your password!' }]"
            >
              <a-input-password v-model:value="loginFormState.password">
                <template #prefix>
                  <LockOutlined class="site-form-item-icon"/>
                </template>
              </a-input-password>
            </a-form-item>

            <a-form-item>
              <a-form-item name="remember" no-style>
                <a-checkbox v-model:checked="loginFormState.remember">Remember me</a-checkbox>
              </a-form-item>
              <a class="login-form-forgot" href="">Forgot password</a>
            </a-form-item>

            <a-form-item>
              <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button">
                Log in
              </a-button>
              Or
              <a href="">register now!</a>
            </a-form-item>
          </a-form>
        </a-tab-pane>

      </a-tabs>

    </a-card>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, computed} from 'vue';
import {UserOutlined, LockOutlined} from '@ant-design/icons-vue';

interface LoginFormState {
  username: string;
  password: string;
  remember: boolean;
}

export default defineComponent({
  components: {
    UserOutlined,
    LockOutlined,
  },
  setup() {
    const loginFormState = reactive<LoginFormState>({
      username: '',
      password: '',
      remember: true,
    });
    const onFinish = (values: any) => {
      console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    const disabled = computed(() => {
      return !(loginFormState.username && loginFormState.password);
    });
    return {
      loginFormState,
      onFinish,
      onFinishFailed,
      disabled,
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
  width: 500px;
  padding: 0 25px;
}
</style>