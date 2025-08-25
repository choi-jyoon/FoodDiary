<template>
  <div class="profile-edit-container">
    <!-- 제목: 넘어온 날짜를 보여주는 센스! -->
    <h2 class="profile-edit-title">
      {{ selectedDate }} 식단 기록하기
    </h2>

    <div class="profile-edit-card">
      <!-- 1. 이름 (필수) -->
      <div class="form-group">
        <label for="mealName">식단 이름</label>
        <input 
          id="mealName"
          v-model="mealName" 
          placeholder="예: 닭가슴살 샐러드" 
          class="required-field"
        />
      </div>

      <!-- 2. 식재료 리스트 (필수) -->
      <div class="form-group flex-start-align"> <!-- 라벨-인풋 상단 정렬을 위해 클래스 추가 -->
        <label for="ingredients">식재료 리스트</label>
        <textarea 
          id="ingredients"
          v-model="ingredients" 
          placeholder="예: 닭가슴살 100g, 양상추 한 줌, 방울토마토 5개&#10;(쉼표나 엔터로 구분해서 입력해주세요!)" 
          rows="4" 
          class="required-field"
        ></textarea>
      </div>

      <!-- 3. 이미지 (옵션) -->
      <div class="form-group">
        <label for="mealImage">이미지</label>
        <input 
          id="mealImage"
          type="file" 
          @change="handleImageUpload" 
          accept="image/*" 
        />
      </div>

      <!-- 4. 레시피 (옵션) -->
      <div class="form-group flex-start-align">
        <label for="recipe">레시피</label>
        <textarea 
          id="recipe"
          v-model="recipe" 
          placeholder="예: 1. 닭가슴살을 에어프라이어에 굽는다.&#10;2. 양상추와 방울토마토를 씻어 준비한다." 
          rows="6"
        ></textarea>
      </div>

      <!-- 5. 칼로리 (옵션) -->
      <div class="form-group">
        <label for="calories">칼로리 (Kcal)</label>
        <input 
          id="calories"
          type="number" 
          v-model="calories" 
          placeholder="예: 350" 
        />
      </div>
    </div>

    <!-- 버튼 그룹 -->
    <div class="button-group">
      <button class="btn primary" @click="submitDiet">기록 완료</button>
      <button class="btn secondary" @click="cancelRecord">취소</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
// import axios from 'axios'; // API 연동 시 주석 해제!

const route = useRoute();
const router = useRouter();

// DietCalendar에서 넘어온 날짜 파라미터
const selectedDate = ref(''); 

// 입력 필드 데이터
const mealName = ref('');
const ingredients = ref('');
const mealImageFile = ref(null); // File 객체를 저장할 ref
const recipe = ref('');
const calories = ref(null); // 숫자로 입력받을 예정

// 컴포넌트가 마운트될 때 실행
onMounted(() => {
  // 라우터 파라미터에서 날짜 가져오기
  if (route.params.date) {
    selectedDate.value = route.params.date;
  }
});

// 이미지 파일 변경 핸들러
function handleImageUpload(event) {
  const file = event.target.files[0];
  if (file) {
    mealImageFile.value = file;
    console.log('이미지 파일 선택됨:', file.name);
  }
}

// '기록 완료' 버튼 클릭 핸들러
async function submitDiet() {
  // 필수 항목 유효성 검사
  if (!mealName.value.trim()) {
    alert('식단 이름을 입력해주세요!');
    return;
  }
  if (!ingredients.value.trim()) {
    alert('식재료 리스트를 입력해주세요!');
    return;
  }

  // 백엔드로 보낼 데이터 준비
  const payload = {
    date: selectedDate.value, // 현재 날짜 파라미터
    mealName: mealName.value,
    ingredients: ingredients.value,
    recipe: recipe.value,
    calories: calories.value // null이면 null, 값이 있으면 숫자
  };

  // 이미지 파일이 있다면 payload에 추가 (FormData 사용 필요)
  // 파일 업로드 시에는 일반적으로 FormData 객체를 사용해서 보내야 해!
  let formData = null;
  if (mealImageFile.value) {
    formData = new FormData();
    formData.append('mealData', JSON.stringify(payload)); // JSON 데이터를 문자열로 변환하여 추가
    formData.append('image', mealImageFile.value); // 이미지 파일 추가
  }

  try {
    // 실제 API 호출 부분 (주석 해제 후 사용)
    // const token = localStorage.getItem("token"); // 토큰이 있다면 가져오기
    // if (formData) {
    //   // 파일 포함 업로드 (Content-Type: multipart/form-data)
    //   await axios.post("YOUR_API_ENDPOINT/diet/upload", formData, {
    //     headers: {
    //       Authorization: `Bearer ${token}`,
    //       'Content-Type': 'multipart/form-data' // 파일 업로드 시 필수
    //     }
    //   });
    // } else {
    //   // 파일 없는 경우 (Content-Type: application/json)
    //   await axios.post("YOUR_API_ENDPOINT/diet/create", payload, {
    //     headers: {
    //       Authorization: `Bearer ${token}`
    //     }
    //   });
    // }

    // 개발 중에는 콘솔에 데이터 출력
    console.log('식단 기록 데이터:', payload);
    if (mealImageFile.value) {
      console.log('업로드할 이미지 파일:', mealImageFile.value.name);
      console.log('FormData:', formData); // FormData는 직접 콘솔에 내용 안 보임.
                                          // console.log([...formData.entries()]); 로 확인 가능.
    }


    alert('식단 기록이 완료되었습니다!');
    // 기록 완료 후, 다시 식단 상세 페이지(DietCalendar)로 이동
    router.push({ name: 'DietCalendar', params: { date: selectedDate.value } });

  } catch (error) {
    console.error('식단 기록 중 오류 발생:', error);
    alert('식단 기록 중 오류가 발생했습니다. 다시 시도해주세요.');
  }
}

// '취소' 버튼 클릭 핸들러
function cancelRecord() {
  // 이전 페이지 (식단 상세 페이지 DietCalendar)로 돌아가기
  router.push({ name: 'DietCalendar', params: { date: selectedDate.value } });
}
</script>

<style scoped>
/* 기존 스타일은 그대로 유지 (IngredientForm에서 가져온 스타일) */
.profile-edit-container {
  max-width: 500px;
  margin: 60px auto;
  padding: 30px 20px;
  font-family: "Noto Sans KR", sans-serif;
  color: #333;
}
.profile-edit-title {
  font-size: 26px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 30px;
}
.profile-edit-card {
  background: #fff;
  border: 1px solid #e5e5e5;
  border-radius: 12px;
  padding: 25px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
}
.form-group {
  display: flex;
  align-items: center; /* 기본적으로 세로 중앙 정렬 */
  justify-content: space-between;
  margin-bottom: 18px;
}
/* textarea가 포함된 form-group은 라벨과 필드를 상단에 정렬 */
.form-group.flex-start-align {
  align-items: flex-start; 
}
.form-group label {
  width: 30%;
  font-weight: 600;
  color: #555;
  padding-top: 10px; /* input과 textarea 상단 패딩에 맞춰 라벨도 살짝 내림 */
}
.form-group input,
.form-group textarea { /* textarea도 스타일 적용 */
  width: 65%;
  padding: 10px 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  color: #333;
  box-sizing: border-box; /* 패딩과 보더가 너비에 포함되도록 */
  resize: vertical; /* textarea 세로 크기 조절 허용 */
}
.form-group input:focus,
.form-group textarea:focus { /* textarea도 포커스 스타일 적용 */
  outline: none;
  border-color: #4CAF50;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.2);
}
/* 필수 필드 스타일 (원한다면 제거하거나 다르게 꾸며도 됨) */
.required-field {
  border-color: #a8e6cf; /* 필수 필드는 살짝 다른 색 테두리 */
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-top: 25px;
}
.btn {
  padding: 12px 20px;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  border: none;
}
.btn.primary {
  background-color: #156859; /* 식단 기록 앱의 테마색으로 통일! */
  color: white;
}
.btn.primary:hover {
  background-color: #104e46;
}
.btn.secondary {
  background-color: #888;
  color: white;
}
.btn.secondary:hover {
  background-color: #666;
}
</style>