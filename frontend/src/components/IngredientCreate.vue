<template>
  <div class="profile-edit-container">
    <h2 class="profile-edit-title">{{ isUpdate ? "재료 수정" : "재료 추가" }}</h2>

    <div class="profile-edit-card">
      <div class="form-group">
        <label>재료명</label>
        <input v-model="name" placeholder="예: 계란" />
      </div>

      <div class="form-group">
        <label>수량</label>
        <input type="number" v-model="amount" placeholder="예: 3" />
      </div>

      <div class="form-group">
        <label>소비기한</label>
        <input type="date" v-model="expiration_date" />
      </div>

      <div class="form-group">
        <label>카테고리</label>
        <input v-model="category" placeholder="예: 유제품, 육류 등" />
      </div>

      <div class="form-group">
        <label>냉장 섹션</label>
        <select v-model="section">
          <option disabled value="">-- 선택하세요 --</option>
          <option v-for="sectionOption in sections" :key="sectionOption.id" :value="sectionOption.name">
            {{ sectionOption.name }}
          </option>
        </select>
      </div>
    </div>

    <div class="button-group">
      <button class="btn primary" @click="submitRefrigerator">{{ isUpdate ? "수정 완료" : "추가 완료" }}</button>
      <button class="btn secondary" @click="$router.push('/refrigerator')">취소</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "IngredientForm",
  data() {
    return {
      isUpdate: false,
      id: null,
      name: "",
      amount: null,
      expiration_date: null,
      category: "",
      section: "",
      sections: [
        { id: 1, name: "냉동" },
        { id: 2, name: "냉장" },
        { id: 3, name: "실온" }
      ]
    };
  },
  async mounted() {
    const query = this.$route.query;

    if (query && query.id) {
      // ✏️ 수정 모드
      this.isUpdate = true;
      this.id = query.id;
      this.name = query.name || "";
      this.amount = Number(query.amount) || null;
      this.category = query.category || "";
      this.expiration_date = query.expiration_date || null;
      this.section = query.section || "";
    }
  },
  methods: {
    async submitRefrigerator() {
      const token = localStorage.getItem("token");
      const payload = {
        name: this.name,
        amount: this.amount,
        expirationDate: this.expiration_date,
        category: this.category,
        section: this.section
      };

      if (this.isUpdate) {
        await axios.put(`http://localhost:8081/refrigerator/update/${this.id}`, payload, {
          headers: { Authorization: `Bearer ${token}` }
        });
      } else {
        await axios.post("http://localhost:8081/refrigerator/create", payload, {
          headers: { Authorization: `Bearer ${token}` }
        });
      }

      this.$router.push("/refrigerator");
    }
  }
};
</script>

<style scoped>
/* 스타일은 기존 코드 그대로 유지 */
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
  align-items: center;
  justify-content: space-between;
  margin-bottom: 18px;
}
.form-group label {
  width: 30%;
  font-weight: 600;
  color: #555;
}
.form-group input,
.form-group select {
  width: 65%;
  padding: 10px 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  color: #333;
}
.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #4CAF50;
  box-shadow: 0 0 4px rgba(76, 175, 80, 0.2);
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
  background-color: #4CAF50;
  color: white;
}
.btn.primary:hover {
  background-color: #45a049;
}
.btn.secondary {
  background-color: #888;
  color: white;
}
.btn.secondary:hover {
  background-color: #666;
}
</style>
