<template>
  <div class="container mt-5" style="max-width: 500px;">
    <h2 class="text-center mb-4">회원가입</h2>
    <form @submit.prevent="register">
      <div class="mb-3">
        <label for="email" class="form-label">이메일</label>
        <input
            type="email"
            v-model="email"
            class="form-control"
            id="email"
            required
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">비밀번호</label>
        <input
            type="password"
            v-model="password"
            class="form-control"
            id="password"
            required
        />
      </div>
      <button type="submit" class="btn btn-primary w-100">가입하기</button>
    </form>
    <div v-if="message" class="alert alert-info mt-3 text-center">
      {{ message }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserRegister',
  data() {
    return {
      email: '',
      password: '',
      message: '',
    };
  },
  methods: {
    async register() {
      try {
        const response = await axios.post('http://localhost:8081/register', {
          email: this.email,
          password: this.password,
        });
        this.message = response.data;
      } catch (error) {
        this.message = error.response?.data || '회원가입 실패';
      }
    },
  },
};
</script>
