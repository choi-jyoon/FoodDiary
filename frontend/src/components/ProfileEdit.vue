<template>
  <div class="profile-edit-container">
    <h2 class="profile-edit-title">프로필 수정</h2>

    <div class="profile-edit-card">
      <div class="form-group">
        <label>닉네임</label>
        <input v-model="nickname" :placeholder="profile.nickname" />
      </div>

      <div class="form-group">
        <label>현재 체중</label>
        <input type="number" v-model="weight" :placeholder="profile.weight" />
      </div>

      <div class="form-group">
        <label>키</label>
        <input type="number" v-model="height" :placeholder="profile.height" />
      </div>

      <div class="form-group">
        <label>목표 체중</label>
        <input type="number" v-model="goal_weight" :placeholder="profile.goalweight" />
      </div>

      <div class="form-group">
        <label>건강 테마</label>
        <select v-model="foodthemaId">
          <option v-for="theme in foodThemes" :key="theme.id" :value="theme.id">
            {{ theme.name }}
          </option>
        </select>
      </div>
    </div>

    <div class="button-group">
      <button class="btn primary" @click="updateProfile">저장</button>
      <button class="btn secondary" @click="$router.push('/profile')">취소</button>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "ProfileEdit",
  data() {
    return {
      profile: {},
      nickname: "",
      weight: null,
      height: null,
      goal_weight: null,
      foodthemaId: null,
      foodThemes: [
        { id: 1, name: "다이어트" },
        { id: 2, name: "근력 강화" },
        { id: 3, name: "유지어터" }
      ]
    };
  },
  async mounted() {
    const token = localStorage.getItem("token");
    const res = await axios.get("http://localhost:8081/profile/", {
      headers: { Authorization: `Bearer ${token}` },
    });
    this.profile = res.data;

    // 기존 값 초기화 (수정시 바로 변경 가능)
    this.nickname = this.profile.nickname;
    this.weight = this.profile.weight;
    this.height = this.profile.height;
    this.goal_weight = this.profile.goalweight;
    this.foodthemaId = this.profile.foodthemaId;
  },
  methods: {
    async updateProfile() {
      const token = localStorage.getItem("token");
      await axios.put(
          "http://localhost:8081/profile/edit",
          {
            nickname: this.nickname,
            weight: this.weight,
            height: this.height,
            goalweight: this.goal_weight,
            foodthemaId: this.foodthemaId,
          },
          {
            headers: { Authorization: `Bearer ${token}` },
          }
      );
      this.$router.push("/profile"); // 저장 후 다시 프로필 보기 화면으로
    }
  }
};
</script>

<style scoped>
/* 전체 컨테이너 */
.profile-edit-container {
  max-width: 500px;
  margin: 60px auto;
  padding: 30px 20px;
  font-family: "Noto Sans KR", sans-serif;
  color: #333;
}

/* 제목 */
.profile-edit-title {
  font-size: 26px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 30px;
}

/* 카드 박스 */
.profile-edit-card {
  background: #fff;
  border: 1px solid #e5e5e5;
  border-radius: 12px;
  padding: 25px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
}

/* 각 항목 그룹 */
.form-group {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 18px;
}

/* 라벨 */
.form-group label {
  width: 30%;
  text-align: left;
  font-weight: 600;
  color: #555;
}

/* 인풋/셀렉트 공통 */
.form-group input,
.form-group select {
  width: 65%;
  padding: 10px 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  color: #333;
  transition: border 0.2s ease;
}

/* 포커스 효과 */
.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #4CAF50;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.2);
}

/* 버튼 그룹 */
.button-group {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-top: 25px;
}

/* 버튼 공통 */
.btn {
  padding: 12px 20px;
  border-radius: 8px;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  border: none;
  transition: background 0.2s ease, transform 0.1s ease;
}

/* 저장 버튼 */
.btn.primary {
  background-color: #4CAF50;
  color: white;
}

.btn.primary:hover {
  background-color: #45a049;
  transform: scale(1.03);
}

/* 취소 버튼 */
.btn.secondary {
  background-color: #888;
  color: white;
}

.btn.secondary:hover {
  background-color: #666;
  transform: scale(1.03);
}
</style>