<template>
  <div>
    <h2>Home</h2>
    <button @click="logout">로그아웃</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function logout() {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
/* 스타일 필요 시 작성 */
</style>
