<!-- CalendarPage.vue -->
<template>
  <div class="calendar-wrapper">
    <!-- 뷰 전환 컨트롤러 (월간/주간 버튼) -->
    <div class="view-selector-controls">
      <button 
        :class="{ 'active-view-btn': currentView === 'month' }" 
        @click="currentView = 'month'"
        class="view-btn"
      >
        월간
      </button>
      <button 
        :class="{ 'active-view-btn': currentView === 'week' }" 
        @click="currentView = 'week'"
        class="view-btn"
      >
        주간
      </button>
    </div>

    <vue-cal
      v-model:active-view="currentView" 
      :disable-views="['years', 'year', 'day']"
      :events="vueCalEvents"
      @cell-click="handleDateClick"
      :time="false"
      :hide-weekends="false"
      class="vuecal--green-theme"
    />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import VueCal from 'vue-cal'
import 'vue-cal/dist/vuecal.css'

const router = useRouter()

// ===== 너의 식단 기록 데이터 (실제로는 서버에서 가져오겠지!) =====
const mealDates = ref(['2025-08-20', '2025-08-22', '2025-08-25'])

const vueCalEvents = computed(() => {
  return mealDates.value.map(date => ({
    start: date,
    end: date,
    class: 'meal-dot'
  }))
})

function handleDateClick(cell) {
  const clickedDate = cell.toISOString().split('T')[0]
  router.push({ name: 'DietCalendar', params: { date: clickedDate } })
}

// ===== 추가: 현재 뷰 상태 관리 (month 또는 week) =====
const currentView = ref('month') // 기본값은 'month'로 설정!
</script>

<style scoped>
/* 캘린더 전체를 감싸는 래퍼 스타일 */
.calendar-wrapper {
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column; /* 버튼이랑 캘린더 세로로 정렬되게 */
  justify-content: flex-start; /* 위에 버튼부터 정렬 */
  align-items: center; /* 전체는 가운데 정렬 */
  padding: 20px; /* 상단에 패딩 좀 줘서 버튼이 너무 붙지 않게 */
  box-sizing: border-box;
  position: relative; /* 자식 요소 절대 위치 잡을 때 기준이 됨 */
}

/* Vue Cal 컴포넌트 자체에 스타일 적용 (꽉 채우기) */
.vuecal {
  width: 90%;
  height: 90%; /* 부모의 남은 공간을 꽉 채우도록 */
  max-width: 900px; /* 원하는 최대 너비 */
  margin-top: 20px; /* 버튼과 캘린더 사이 간격 */
}

/* 
  기존 Vue Cal의 못생긴 뷰 선택 메뉴 숨기기!! 
  이 부분이 기본적으로 'Month' 'Week' 'Day' 탭이 보이는 부분이야.
*/
:deep(.vuecal__menu) { /* :deep()은 scoped CSS에서 자식 컴포넌트 스타일을 건드릴 때 사용 */
  display: none !important; /* 무조건 숨겨! */
}
/* 그리고 달력 헤더 좌우 버튼은 기본적으로 보일 거야! */

/* ===== 커스텀 뷰 선택 컨트롤러 스타일 ===== */
.view-selector-controls {
  width: 100%;
  max-width: 900px; /* 캘린더와 동일한 너비로 맞춰줘 */
  display: flex;
  justify-content: flex-start; /* 좌측 정렬 */
  gap: 10px; /* 버튼 사이 간격 */
  padding-left: 10px; /* 캘린더 좌측 패딩과 맞춰서 좀 더 깔끔하게 */
}

.view-btn {
  background-color: transparent; /* 배경색 없이! */
  border: 1px solid #156859; /* 테두리색 (이전에 점 색깔과 동일) */
  color: #156859; /* 글자색 */
  padding: 8px 16px;
  border-radius: 20px; /* 동그란 버튼 모양 */
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease; /* 호버/클릭 시 부드러운 전환 */
}

.view-btn:hover {
  background-color: rgba(21, 104, 89, 0.1); /* 호버 시 약간의 배경색 */
}

.view-btn.active-view-btn {
  background-color: #156859; /* 선택됐을 때 배경색 채우기 */
  color: white; /* 글자색 흰색으로 */
}

/* 
  식단 기록 점 스타일! (이전과 동일)
*/
.vuecal__event.meal-dot {
  background-color: #156859;
  border-radius: 50%;
  width: 8px;
  height: 8px;
  position: absolute;
  bottom: 5px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 99;
  opacity: 1;
}
</style>