<!-- DietCalendar.vue -->
<template>
  <div class="vc-container">
    <div class="calendar-container">
      <v-calendar
          is-expanded
          @dayclick="goToDetail"
          :styles="calendarStyle"
      />
    </div>
  </div>
</template>

<script>

import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()


    function goToDetail(day) {
      const selectedDate = day.date.toISOString().split('T')[0]
      router.push(`/diet/${selectedDate}`)
    }

    const calendarStyle = {
      wrapper: {
        height: '100%'
      },
    }

    return {
      goToDetail,
      calendarStyle
    }
  }
}
</script>

<style scoped>
.calendar-container {
  width: 100vw;
  height: 90vh; /* 혹은 100vh; */
  padding: 2rem;
  box-sizing: border-box;
}
.vc-container {
  height: 100%;
}
</style>
