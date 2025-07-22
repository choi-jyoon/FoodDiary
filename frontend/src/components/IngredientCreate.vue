<template>
  <div class="profile-edit-container">
    <h2 class="profile-edit-title">프로필 수정</h2>

    <div class="profile-edit-card">
      <div class="form-group">
        <label>재료명</label>
        <input v-model="name" :placeholder="refrigerator.name" />
      </div>

      <div class="form-group">
        <label>수량</label>
        <input type="number" v-model="amount" :placeholder="refrigerator.amount" />
      </div>

      <div class="form-group">
        <label>소비기한</label>
        <input type="date" v-model="expiration_date" :placeholder="refrigerator.expiration_date" />
      </div>

      <div class="form-group">
        <label>카테고리</label>
        <input type="text" v-model="category" :placeholder="refrigerator.category" />
      </div>

      <div class="form-group">
        <label>냉장 섹션</label>
        <select v-model="sections">
          <option v-for="section in sections" :key="section.name" :value="section.id">
            {{ section.name }}
          </option>
        </select>
      </div>
    </div>

    <div class="button-group">
      <button class="btn primary" @click="createRefrigerator">저장</button>
      <button class="btn secondary" @click="$router.push('/refrigerator')">취소</button>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import {ref} from "vue";

export default {
  name: "IngredientCreate",
  data() {
    return {
      refrigerator: {},
      name: "",
      amount: null,
      expiration_date: null,
      category: null,
      sections: [
        { id: 1, name: "냉동" },
        { id: 2, name: "냉장" },
        { id: 3, name: "실온" }
      ]
    };
  },
  async mounted() {
    const token = localStorage.getItem("token");
    const res = await axios.get("http://localhost:8081/refrigerator/", {
      headers: { Authorization: `Bearer ${token}` },
    });
    this.refrigerator= res.data;

    this.name = this.refrigerator.name;
    this.amount = this.refrigerator.amount;
    this.category = this.refrigerator.category;
    this.expiration_date = this.refrigerator.expiration_date;
    this.section = this.refrigerator.section;
  },
  methods: {
    ref,
    async createRefrigerator() {
      const token = localStorage.getItem("token");
      await axios.post(
          "http://localhost:8081/refrigerator/create",
          {
            name: this.name,
            amount: this.amount,
            expiration_date: this.expiration_date,
            category: this.category,
            section: this.section,
          },
          {
            headers: { Authorization: `Bearer ${token}` },
          }
      );
      this.$router.push("/refrigerator"); // 저장 후 다시 프로필 보기 화면으로
    }
  }
};
</script>

<style scoped>
/* 전체 컨테이너 */
.refrigerator-form-container {
  max-width: 500px;
  margin: 60px auto;
  padding: 30px 20px;
  font-family: "Noto Sans KR", sans-serif;
  color: #333;
}

/* 제목 */
.refrigerator-title {
  font-size: 26px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 30px;
}

/* 카드 박스 */
.refrigerator-card {
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