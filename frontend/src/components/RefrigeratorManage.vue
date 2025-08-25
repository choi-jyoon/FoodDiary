<template>
  <div class="refrigerator-container">
    <h2 class="title">🧊 나의 냉장고</h2>

    <div class="fridge-box">
      <div class="section" id="frozen">
        <h3>냉동칸</h3>
        <div class="items-grid">
          <div
            v-for="item in frozenItems"
            :key="item.id"
            class="ingredient-item"
            @click="goUpdate(item)"
          >
            <div class="icon-circle">
              <i :class="getIconClass(item.category)"></i>
            </div>
            <span class="item-label">{{ item.name }}</span>
          </div>
        </div>
      </div>

      <div class="section" id="chilled">
        <h3>냉장칸</h3>
        <div class="items-grid">
          <div
            v-for="item in chilledItems"
            :key="item.id"
            class="ingredient-item"
            @click="goUpdate(item)"
          >
            <div class="icon-circle">
              <i :class="getIconClass(item.category)"></i>
            </div>
            <span class="item-label">{{ item.name }}</span>
          </div>
        </div>
      </div>

      <button class="add-btn" @click="goCreate">+</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RefrigeratorManage",
  data() {
    return {
      ingredients: [],
      frozenItems: [],
      chilledItems: [],
    };
  },
  mounted() {
    this.loadRefrigerator();
  },
  methods: {
    async loadRefrigerator() {
      try {
        const token = localStorage.getItem("token");
        const res = await axios.get("http://localhost:8081/refrigerator", {
          headers: { Authorization: `Bearer ${token}` },
        });

        this.ingredients = res.data.ingredients;
        this.frozenItems = this.ingredients.filter((i) => i.section == "냉동");
        this.chilledItems = this.ingredients.filter((i) => i.section == "냉장");
      } catch (e) {
        console.error(e);
      }
    },
    goCreate() {
      this.$router.push("/refrigerator/create");
    },
    goUpdate(item) {
      this.$router.push({
        path: "/refrigerator/update",
        query: {
          id: item.id,
          name: item.name,
          amount: item.amount,
          category: item.category,
          expiration_date: item.expirationDate,
          section: item.section,
        },
      });
    },
    getIconClass(category) {
      const map = {
        육류: "fas fa-drumstick-bite",
        유제품: "fas fa-solid fa-cheese",
        계란: "fas fa-solid fa-egg",
        채소: "fas fa-solid fa-carrot",
        과일: "fas fa-apple-alt",
        가공식품: "fas fa-box",
        생선: "fas fa-solid fa-fish",
        기타: "fas fa-ice-cream",
      };
      return map[category] || "fas fa-question-circle";
    },
  },
};
</script>

<style scoped>
.refrigerator-container {
  max-width: 600px;
  margin: 30px auto;
  padding: 20px;
  background-color: #f0f9ff;
  border-radius: 15px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: "Noto Sans KR", sans-serif;
  text-align: center;
}

.title {
  font-size: 26px;
  margin-bottom: 24px;
  font-weight: bold;
}

.fridge-box {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.section {
  background-color: #ffffff;
  padding: 15px;
  border-radius: 12px;
  border: 2px solid #d0e7ff;
}

.section#frozen {
  border-color: #90cdf4;
}
.section#chilled {
  border-color: #b2f2bb;
}

.section h3 {
  font-size: 18px;
  margin-bottom: 10px;
  color: #333;
  text-align: left;
}

.items-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.ingredient-item {
  background: #f8fbff;
  border-radius: 10px;
  padding: 10px;
  text-align: center;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
  cursor: pointer;
}
.ingredient-item:hover {
  background-color: #d1fae5; /* hover 시 연초록 포인트 */
  transform: translateY(-4px);
}

.icon-circle {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background-color: #e0f2fe;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  margin: 0 auto 5px;
  color: #333;
}

.item-label {
  font-size: 14px;
  font-weight: 500;
}

.add-btn {
  grid-column: span 2;
  margin-top: 20px;
  width: 50px;
  height: 50px;
  font-size: 24px;
  border-radius: 50%;
  border: none;
  background-color: #4caf50;
  color: white;
  cursor: pointer;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}
</style>
