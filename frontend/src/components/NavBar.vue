<!-- NavBar.vue -->
<template>
  <nav class="navbar">
    <router-link to="/">홈</router-link>
    <router-link to="/refrigerator">냉장고</router-link>

    <div class="auth-menu" @mouseenter="showDropdown = true" @mouseleave="showDropdown = false">
      <template v-if="!isLoggedIn">
        <span class="dropdown-trigger">로그인 ▼</span>
        <div v-if="showDropdown" class="dropdown-box">
          <router-link to="/login">로그인</router-link>
          <router-link to="/register">회원가입</router-link>
        </div>
      </template>


      <template v-else>
        <button class="logout-btn" @click="logout">로그아웃</button>
      </template>
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
  padding: 10px;
  color: white;
}
.navbar a {
  margin-right: 16px;
  color: white;
  text-decoration: none;
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
  top: 25px;
  right: 0;
  background-color: white;
  color: black;
  border: 1px solid #ccc;
  padding: 5px 0;
  border-radius: 5px;
  z-index: 1000;
}
.dropdown-box a {
  display: block;
  padding: 8px 16px;
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
</style>
