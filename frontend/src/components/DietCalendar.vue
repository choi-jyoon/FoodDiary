<template>
  <div class="detail-container">
    <h2>{{ selectedDate }} 식단 기록</h2>

    <div class="meal-cards">
      <div class="meal-card" v-for="meal in ['아침', '점심', '저녁']" :key="meal">
        <div class="meal-header">{{ meal }}</div>
        <div class="meal-body">
          <div v-if="meals[mealMap[meal]]">
            {{ meals[mealMap[meal]] }}
          </div>
          <div v-else class="no-record">기록 없음</div>
        </div>
      </div>
    </div>

    <button
        class="record-btn"
        v-if="!hasAnyMealRecord"
        @click="goToRecordPage"
    >
      식단 기록하기
    </button>
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

export default {
  setup() {
    const route = useRoute()
    const router = useRouter()
    const selectedDate = route.params.date

    const meals = ref({
      breakfast: '',
      lunch: '',
      dinner: ''
    })

    const mealMap = {
      아침: 'breakfast',
      점심: 'lunch',
      저녁: 'dinner'
    }

    const hasAnyMealRecord = computed(() =>
        Object.values(meals.value).some(val => val && val.trim() !== '')
    )

    function goToRecordPage() {
      router.push({ name: 'DietWrite', params: { date: selectedDate } })
    }

    onMounted(() => {
      // 추후 axios 호출로 교체
      // e.g., axios.get(`/diet?date=${selectedDate}`).then(res => { meals.value = res.data })
    })

    return {
      selectedDate,
      meals,
      mealMap,
      hasAnyMealRecord,
      goToRecordPage
    }
  }
}
</script>


<style scoped>
.detail-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 24px;
}

h2 {
  margin-bottom: 24px;
  text-align: center;
}

.meal-cards {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.meal-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 16px;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,0,0,0.05);
}

.meal-header {
  font-weight: bold;
  margin-bottom: 8px;
}

.no-record {
  color: #888;
  font-style: italic;
}

.record-btn {
  display: block;
  width: 100%;
  margin-top: 32px;
  padding: 14px;
  font-size: 16px;
  background: #156859;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
</style>
