<template>
  <nav class="navbar">
    <!-- 🥗 로고 역할 -->
    <div class="logo">🥗</div>

    <!-- 주요 네비게이션 링크 -->
    <router-link to="/">홈</router-link>
    <router-link to="/refrigerator">냉장고</router-link>
    <router-link to="/calendar">캘린더</router-link>
    <router-link to="/recommend">식단 추천</router-link>
    <router-link to="/cart">장바구니</router-link>

    <!-- 로그인/회원가입/로그아웃 -->
    <div class="auth-menu" @mouseenter="showDropdown = true" @mouseleave="showDropdown = false">
      <template v-if="isLoggedIn">
        <router-link to="/profile" class="mypage-link">마이페이지</router-link>
        <button class="logout-btn" @click="logout">로그아웃</button>
      </template>
      <template v-if="!isLoggedIn">
        <span class="dropdown-trigger">로그인 ▼</span>
        <div v-if="showDropdown" class="dropdown-box">
          <router-link to="/login">로그인</router-link>
          <router-link to="/register">회원가입</router-link>
        </div>
      </template>

      <!-- <template v-else>
        <button class="logout-btn" @click="logout">로그아웃</button>
      </template> -->
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
  data() {
    return {
      showDropdown: false
    };
  },
  computed: {
    isLoggedIn() {
      return !!localStorage.getItem("token");
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  background-color: #4CAF50;
  padding: 14px 20px;
  border-radius: 12px;
  font-weight: bold;
  font-size: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
.logo {
  font-size: 24px;
  margin-right: 20px;
}
.navbar a {
  margin-right: 18px;
  color: white;
  text-decoration: none;
  padding: 8px 12px;
  border-radius: 8px;
  transition: background-color 0.3s;
}
.navbar a:hover {
  background-color: #45a049;
}
.auth-menu {
  position: relative;
  margin-left: auto;
}
.dropdown-trigger {
  cursor: pointer;
  font-weight: bold;
}
.dropdown-box {
  position: absolute;
  top: 30px;
  right: 0;
  background-color: white;
  color: black;
  border: 1px solid #ccc;
  padding: 5px 0;
  border-radius: 8px;
  z-index: 1000;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}
.dropdown-box a {
  display: block;
  padding: 10px 20px;
  text-decoration: none;
  color: black;
}
.dropdown-box a:hover {
  background-color: #f0f0f0;
}
.logout-btn {
  background: none;
  border: none;
  color: white;
  cursor: pointer;
  font-weight: bold;
}
.logout-btn:hover {
  text-decoration: underline;
}
.mypage-link {
  color: white;
  margin-right: 12px;
  text-decoration: none;
  font-weight: bold;
  padding: 8px 12px;
  border-radius: 8px;
  transition: background-color 0.3s;
}
.mypage-link:hover {
  background-color: #45a049;
}
</style>
