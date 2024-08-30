<script setup>
import Banner from "@/components/Banner.vue";

</script>

<template>
  <Banner/>
  <div class="centered-container">
    <h1>Login</h1>
    <form>
      <label>Username</label><br>
      <input type="text" placeholder="Username..."><br><br>
      <label>Password</label><br>
      <input type="password" placeholder="Password..."><br>
      <button style="margin:10px"  id="submit-btn"><b>Login</b></button>
    </form>
  </div>
</template>

<style scoped>
.centered-container {
  display: block;
  position: relative;
  text-align: center;
  height: 30%;
  width: 30%;
  border: 2px solid black;
  border-radius: 10px;
  padding: 5px;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  margin: auto;
  background-color: white;
}

#submit-btn {
  background-color: lightslategray;
  color: #1D1D1D;
  width: 100px;
  height: 30px;
  font-size: large;
}
</style>