<script setup>
import Banner from "@/components/Banner.vue";
</script>

<template>
  <Banner/>
  <div class="centered-top">
    <h1>Invalid Link!</h1>
    <p>Your login link is invalid. Please log into the server and use '/login' to get your unique login link!</p>
  </div>
</template>

<style scoped>
.centered-top {
  text-align: center;
  margin-top: 10%;
}

h1 {
  letter-spacing: 3px;
  color: white;
}

p {
  color: white;
  font-size: x-large;
}

</style>