<template>
  <div class="container">
    <!-- 헤더: 이모지 + 날짜 -->
    <div class="header">
      <div class="emoji">🥗</div>
      <div class="date-text">오늘은 {{ today }}</div>
    </div>

    <!-- 기능 카드 영역 -->
    <div class="card-container">
      <div class="card" @click="goToDietRecord">식단 기록하기</div>
      <div class="card" @click="goToCalendar">캘린더로 식단 확인</div>
      <div class="card" @click="goToRecommend">식단 추천받기</div>
      <div class="card" @click="goToRefrigerator">나의 냉장고</div>
    </div>

    <!-- 푸터 -->
    <footer class="footer">
      건강한 한 끼가 건강한 하루를 만듭니다.
    </footer>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const today = ref(new Date().toISOString().slice(0, 10))

function goToDietRecord() {
  router.push(`/diary/${today.value}`)
}
function goToCalendar() {
  router.push('/diary')
}
function goToRecommend() {
  router.push('/recommend')
}
function goToRefrigerator() {
  router.push('/refrigerator')
}
</script>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f0fdf4; /* 연한 연두색 */
  padding: 3rem 1rem;
  position: relative;
}

/* 헤더 */
.header {
  text-align: center;
  margin-bottom: 3rem;
}
.emoji {
  font-size: 5rem;
}
.date-text {
  margin-top: 1rem;
  font-size: 1.75rem;
  font-weight: bold;
  color: #1a202c;
}

/* 카드 영역 */
.card-container {
  width: 100%;
  max-width: 320px;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}
.card {
  background-color: white;
  padding: 1.5rem;
  border-radius: 1rem;
  text-align: center;
  font-weight: 600;
  color: #2d3748;
  border: 2px solid #94d3a2; /* 진한 연초록 테두리 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
  cursor: pointer;
  transition: all 0.2s ease;
}
.card:hover {
  background-color: #d1fae5; /* hover 시 연초록 포인트 */
  transform: translateY(-4px);
}

/* 푸터 */
.footer {
  margin-top: auto;
  text-align: center;
  font-size: 0.85rem;
  color: #4a5568;
  padding-top: 4rem;
}
</style>
