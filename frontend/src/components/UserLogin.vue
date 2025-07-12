<template>
  <div class="login">
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <div>
        <label>이메일:</label>
        <input type="email" v-model="email" required />
      </div>
      <div>
        <label>비밀번호:</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit">로그인</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';
import router from "@/router";

export default {
  name: 'UserLogin',
  data() {
    return {
      email: '',
      password: '',
      message: '',
    };
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:8081/user/login', {
          email: this.email,
          password: this.password,
        });

        const token = response.data.token;
        localStorage.setItem('token', token);  // 💡 저장!
        this.message = '로그인 성공!';
        console.log("success login");

        // 로그인 후 메인 페이지로 이동 예시
        console.log("router 이동1");
        console.log(router);
        router.push('/');
        console.log("router 이동");
      } catch (error) {
        this.message = error.response?.data || '로그인 실패';
      }
    },
  },
};
</script>
<style scoped>

</style>