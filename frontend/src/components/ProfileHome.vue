<template>
  <div class="profile-container">
    <h2 class="profile-title">내 프로필</h2>

    <!-- 프로필 정보 박스 -->
    <div class="profile-card">
      <div v-if="profile">
        <div class="profile-item">
          <span class="label">닉네임</span>
          <span class="value">{{ profile.nickname }}</span>
        </div>
        <div class="profile-item">
          <span class="label">현재 체중</span>
          <span class="value">{{ profile.weight }} kg</span>
        </div>
        <div class="profile-item">
          <span class="label">현재 신장</span>
          <span class="value">{{ profile.height }} cm</span>
        </div>
        <div class="profile-item">
          <span class="label">목표 체중</span>
          <span class="value">{{ profile.goalweight }} kg</span>
        </div>
        <div class="profile-item">
          <span class="label">건강 테마</span>
          <span class="value">{{ profile.foodthemaId || '선택 안됨' }}</span>
        </div>
      </div>

      <!-- 프로필이 없을 때 -->
      <div v-else>
        <div class="profile-item"><span class="label">닉네임</span><span class="value">-</span></div>
        <div class="profile-item"><span class="label">현재 체중</span><span class="value">-</span></div>
        <div class="profile-item"><span class="label">현재 신장</span><span class="value">-</span></div>
        <div class="profile-item"><span class="label">목표 체중</span><span class="value">-</span></div>
        <div class="profile-item"><span class="label">건강 테마</span><span class="value">-</span></div>

        <button class="btn primary" @click="goCreate">➕ 프로필 등록하기</button>
      </div>
    </div>

    <!-- 수정 버튼 -->
    <div class="button-group" v-if="profile">
      <button class="btn secondary" @click="goEdit">✏️ 수정하러 가기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ProfileHome',
  data() {
    return {
      profile: null,
      message: '',
    };
  },
  mounted() {
    this.loadProfile();
  },
  methods: {
    async loadProfile() {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8081/profile/", {
          headers: { Authorization: `Bearer ${token}` },
        });
        this.profile = response.data;
      } catch (error) {
        this.message = error.response?.data || '프로필 불러오기 실패';
        this.profile = null;
      }
    },
    goCreate() {
      this.$router.push("/profile/create");
    },
    goEdit() {
      this.$router.push("/profile/edit");
    }
  },
};
</script>

<style scoped>
/* 전체 레이아웃 */
.profile-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  font-family: "Noto Sans KR", sans-serif;
  text-align: center;
}

/* 제목 */
.profile-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

/* 프로필 박스 */
.profile-card {
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}

/* 각 항목 라벨 & 값 정렬 */
.profile-item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
}

.profile-item:last-child {
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

.btn.primary {
  background-color: #4CAF50;
  color: white;
}

.btn.primary:hover {
  background-color: #45a049;
}

.btn.secondary {
  background-color: #007BFF;
  color: white;
}

.btn.secondary:hover {
  background-color: #0069d9;
}
</style>
