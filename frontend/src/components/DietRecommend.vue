<template>
  <div class="recommend-container">
    <h2 class="recommend-title">식단 추천하기</h2>

    <!-- 박스 -->
    <div class="diet-card">

        <div class="diet-item">
          <span class="label">닉네임</span>
          <span class="value">{{ profile.nickname }}</span>
        </div>
        <div class="diet-item">
          <span class="label">현재 체중</span>
          <span class="value">{{ profile.weight }} kg</span>
        </div>
        <div class="diet-item">
          <span class="label">현재 신장</span>
          <span class="value">{{ profile.height }} cm</span>
        </div>
        <div class="diet-item">
          <span class="label">목표 체중</span>
          <span class="value">{{ profile.goalweight }} kg</span>
        </div>
        <div class="diet-item">
          <span class="label">건강 테마</span>
          <span class="value">{{ profile.foodthemaId || '선택 안됨' }}</span>
        </div>

    </div>

    <!-- 다시하기 버튼 -->
    <div class="button-group" v-if="recommend">
      <button class="btn secondary" @click="goCreate">✏️ 다시하기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'DietRecommend',
  data() {
    return {
      recommend: null,
      message: '',
    };
  },
  mounted() {
    this.loadDiet();
  },
  methods: {
    async loadProfile() {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8081/recommend", {
          headers: { Authorization: `Bearer ${token}` },
        });
        this.recommend = response.recommend;
      } catch (error) {
        this.message = error.response?.data || '추천 식단 불러오기 실패';
        this.recommend = null;
      }
    },
    goCreate() {
      this.$router.push("/recommend");
    },
  },
};
</script>

<style scoped>
/* 전체 레이아웃 */
.recommend-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  font-family: "Noto Sans KR", sans-serif;
  text-align: center;
}

/* 제목 */
.recommend-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

/* 프로필 박스 */
.diet-card {
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}

/* 각 항목 라벨 & 값 정렬 */
.diet-item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
}

.diet-item:last-child {
  border-bottom: none;
}

.label {
  font-weight: 600;
  color: #555;
}

.value {
  color: #333;
}

/* 버튼 스타일 */
.button-group {
  margin-top: 20px;
}

.btn {
  padding: 10px 16px;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  border: none;
  margin-top: 15px;
}

.btn.secondary {
  background-color: #007BFF;
  color: white;
}

.btn.secondary:hover {
  background-color: #0069d9;
}
</style>
