<template>
  <button @click="logout">로그아웃</button>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function logout() {
  localStorage.removeItem('accessToken')  // 1. 토큰 삭제
  router.push('/login')                  // 2. 로그인 페이지로 이동
}
</script>
