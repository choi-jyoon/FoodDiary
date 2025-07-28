<template>
  <div class="refrigerator-container">
    <h2 class="refrigerator-title">🥶 나의 냉장고</h2>

    <div class="fridge-box">
      <!-- 냉동칸 -->
      <div class="fridge-section">
        <h3>냉동칸</h3>
        <div class="item-grid">
          <div v-for="item in frozenItems" :key="item.id" class="item">
            <button class="edit-btn" @click="goUpdate(item)"></button>
            <i :class="item.icon"></i>
            <span>{{ item.name }}</span>
          </div>
        </div>
      </div>

      <!-- 냉장칸 -->
      <div class="fridge-section">
        <h3>냉장칸</h3>
        <div class="item-grid">
          <div v-for="item in chilledItems" :key="item.id" class="item">
            <button class="edit-btn" @click="goUpdate(item)"></button>
            <i :class="item.icon"></i>
            <span>{{ item.name }}</span>
          </div>
        </div>
      </div>

      <!-- 추가 버튼 -->
      <button class="add-btn" @click="goCreate">➕</button>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'RefrigeratorManage',
  data() {
    return {
      ingredients: [],
      frozenItems: [],
      chilledItems: [],
      message: '',
    };
  },
  mounted() {
    this.loadRefrigerator();
  },
  methods: {
    async loadRefrigerator() {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8081/refrigerator", {
          headers: { Authorization: `Bearer ${token}` },
        });
        this.ingredients = response.data.ingredients;

        // 냉동/냉장 분리
        this.frozenItems = this.ingredients.filter(item => item.section === "냉동");
        this.chilledItems = this.ingredients.filter(item => item.section === "냉장");

      } catch (error) {
        this.message = error.response?.data || '냉장고 불러오기 실패';
        this.ingredients = [];
      }
    },
    goCreate() {
      this.$router.push("/refrigerator/create");
    },
    goUpdate(item) {
      this.$router.push({
        path: '/refrigerator/update',
        query: {
          id: item.id,
          name: item.name,
          amount: item.amount,
          category: item.category,
          expiration_date: item.expirationDate,
          section: item.section
        }
      });
    }
  },
};
</script>
<style scoped>
/* 전체 레이아웃 */
.refrigerator-container {
  max-width: 500px;
  margin: 40px auto;
  text-align: center;
  font-family: "Noto Sans KR", sans-serif;
}

/* 제목 */
.refrigerator-title {
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 20px;
}

/* 냉장고 전체 박스 */
.fridge-box {
  position: relative;
  background: #f9f9f9;
  border: 3px solid #ddd;
  border-radius: 15px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

/* 각 칸(냉동/냉장) */
.fridge-section {
  padding: 15px;
  border-bottom: 2px solid #e0e0e0;
}
.fridge-section:last-child {
  border-bottom: none;
}

.fridge-section h3 {
  font-size: 18px;
  margin-bottom: 10px;
  text-align: left;
  color: #444;
}

/* 아이템 그리드 */
.item-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
  margin-top: 10px;
}

/* 개별 아이템 */
.item {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: white;
  padding: 8px;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}
.item input[type="checkbox"] {
  margin-bottom: 5px;
}
.item i {
  font-size: 24px;
  color: #555;
}
.item span {
  font-size: 14px;
  margin-top: 4px;
}

/* 추가 버튼 */
.add-btn {
  position: absolute;
  bottom: 12px;
  right: 12px;
  width: 45px;
  height: 45px;
  background-color: #4caf50;
  color: white;
  font-size: 22px;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}
.add-btn:hover {
  background-color: #45a049;
}
</style>
