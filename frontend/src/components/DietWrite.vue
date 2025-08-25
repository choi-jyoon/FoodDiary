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

    <!-- 식단 기록 버튼 클릭 시 이제 모달이 뜸! -->
    <button
      class="record-btn"
      v-if="!hasAnyMealRecord"
      @click="showRecordOptionsModal = true"
    >
      식단 기록하기
    </button>

    <!-- 모달창 영역 -->
    <div v-if="showRecordOptionsModal" class="modal-overlay" @click.self="showRecordOptionsModal = false">
      <div class="modal-content">
        <h3>어떻게 기록할까요?</h3>
        <div class="modal-options">
          <button class="modal-option-btn" @click="selectRecordOption('load')">
            내 식단에서 불러오기
          </button>
          <button class="modal-option-btn" @click="selectRecordOption('new')">
            새로운 식단 기록하기
          </button>
        </div>
        <button class="modal-close-btn" @click="showRecordOptionsModal = false">닫기</button>
      </div>
    </div>
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
      breakfast: '', // 여기에 나중에 아침 식단 내용 들어감
      lunch: '',     // 여기에 나중에 점심 식단 내용 들어감
      dinner: ''     // 여기에 나중에 저녁 식단 내용 들어감
    })

    const mealMap = {
      아침: 'breakfast',
      점심: 'lunch',
      저녁: 'dinner'
    }

    // 기록이 하나라도 있으면 '식단 기록하기' 버튼을 숨기기 위한 계산된 속성
    const hasAnyMealRecord = computed(() =>
      Object.values(meals.value).some(val => val && val.trim() !== '')
    )

    // 모달창 표시 여부를 관리하는 ref
    const showRecordOptionsModal = ref(false)

    // 모달창에서 옵션 선택 시 실행될 함수
    function selectRecordOption(option) {
      showRecordOptionsModal.value = false // 모달 닫기

      if (option === 'load') {
        // '내 식단에서 불러오기' 페이지로 이동
        router.push({ name: 'DietLoad', params: { date: selectedDate } }) 
      } else if (option === 'new') {
        // '새로운 식단 기록하기' 페이지로 이동
        router.push({ name: 'CustomDietWrite', params: { date: selectedDate } })
      }
    }

    onMounted(() => {
      // 컴포넌트 마운트 시, 해당 날짜의 식단 기록을 불러오는 API 호출 (나중에 여기에 구현)
      // 예시: axios.get(`/api/diet/${selectedDate}`).then(res => { meals.value = res.data })
      // 임시로 데이터 넣어보기 (테스트용)
      // meals.value = {
      //   breakfast: '닭가슴살 샐러드',
      //   lunch: '',
      //   dinner: '구운 두부와 버섯'
      // }
    })

    return {
      selectedDate,
      meals,
      mealMap,
      hasAnyMealRecord,
      showRecordOptionsModal, // 모달 상태 반환
      selectRecordOption     // 모달 옵션 선택 함수 반환
    }
  }
}
</script>

<style scoped>
/* 기존 스타일은 유지하면서 필요한 부분만 수정 또는 추가! */
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
  padding: 20px; /* 내부 meal-card들과의 간격 */
  border: 2px solid #a8e6cf; /* 전체 테두리 색상 (연두색 계열) */
  border-radius: 12px; /* 전체 테두리 모서리 둥글게 */
  background: #f9fdfb; /* 살짝 밝은 배경색 (옵션) */
  box-shadow: 0 4px 12px rgba(0,0,0,0.1); /* 전체에도 그림자 추가! */
  margin-bottom: 20px; /* 버튼과 간격 확보 (필요시 조절) */
}

/* ===== 1. 아침/점심/저녁 카드 테두리 강화 (수정 부분!) ===== */
.meal-card {
  border: 2px solid #94d3a2; /* 더 두껍고 테마색으로 테두리! */
  border-radius: 8px;
  padding: 16px;
  background: #fff;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* 그림자도 좀 더 진하게! */
  transition: transform 0.2s ease-in-out; /* 마우스 오버 효과 줄까? */
}
.meal-card:hover {
    transform: translateY(-2px); /* 살짝 떠오르는 효과 */
}

.meal-header {
  font-weight: bold;
  margin-bottom: 8px;
  color: #156859; /* 헤더 글자색도 테마색으로 맞춰주면 더 이쁨! */
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
  transition: background 0.3s ease;
}
.record-btn:hover {
  background: #104e46; /* 호버 시 색상 약간 진하게 */
}


/* ===== 2. 모달창 디자인 (새로 추가!) ===== */
.modal-overlay {
  position: fixed; /* 화면 전체를 덮도록 고정 */
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.6); /* 반투명 검정색 배경 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000; /* 다른 요소들 위에 오도록 높은 z-index 설정 */
}

.modal-content {
  background: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  max-width: 400px; /* 모달의 최대 너비 */
  width: 90%; /* 화면 크기에 따라 유동적으로 */
  text-align: center;
  position: relative; /* 닫기 버튼 배치를 위해 */
  animation: modal-fade-in 0.3s ease-out forwards; /* 나타나는 애니메이션 */
}

@keyframes modal-fade-in {
  from { opacity: 0; transform: translateY(-20px); }
  to { opacity: 1; transform: translateY(0); }
}

.modal-content h3 {
  margin-top: 0;
  margin-bottom: 25px;
  font-size: 20px;
  color: #333;
}

.modal-options {
  display: flex;
  flex-direction: column; /* 버튼 세로 정렬 */
  gap: 15px; /* 버튼 사이 간격 */
  margin-bottom: 20px;
}

.modal-option-btn {
  display: block;
  width: 100%;
  padding: 15px;
  font-size: 17px;
  background: #70afa2; /* 식단 기록하기 버튼과 동일한 색상 */
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s ease, transform 0.1s ease;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.modal-option-btn:hover {
  background: #d1fae5;
  transform: translateY(-1px); /* 살짝 눌리는 느낌 */
}

.modal-close-btn {
  background: #ccc;
  color: #333;
  border: none;
  border-radius: 6px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s ease;
}

.modal-close-btn:hover {
  background: #bbb;
}
</style>