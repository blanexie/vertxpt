<template>
  <div class="by">
    <div class="login-card">
      <div>登录/注册</div>
      <br>
      <n-form ref="formRef" :model="formValue"
              label-align="right"
              label-width="auto"
              label-placement="left">
        <n-form-item
            label="姓名"
            path="user.name"
        >
          <n-input v-model:value="formValue.user.name" placeholder="输入姓名"/>
        </n-form-item>
        <n-form-item label="年龄" path="user.age">
          <n-input v-model:value="formValue.user.age" placeholder="输入年龄"/>
        </n-form-item>
        <n-form-item label="电话号码" path="user.phone">
          <n-input v-model:value="formValue.phone" placeholder="电话号码"/>
        </n-form-item>
      </n-form>
    </div>
  </div>
</template>

<script lang="ts">

import {defineComponent, ref} from 'vue'
import {FormInst, useMessage} from 'naive-ui'

export default defineComponent({
  setup() {
    const formRef = ref(null)
    return {
      formRef,
      formValue: ref({
        user: {
          name: '',
          age: ''
        },
        phone: ''
      }),
    }
  }
})
</script>

<style scoped>
.by {
  min-height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-card {
  border: 1px solid red;
  height: 300px;
  width: 550px;
  padding: 25px;
}
</style>