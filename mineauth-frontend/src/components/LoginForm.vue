<script setup>
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

const username = ref("")
const password = ref("")
const uuid = ref("")
const submitted = ref(false)
const logged = ref(false)

const route = useRoute()
retrieveLoginCode()

async function retrieveLoginCode() {
  await axios.get("http://localhost:8080/api/login/code/" + route.params.code)
      .then(async function (response) {
        const data = response.data;
        if (data.uuid !== undefined) {
          uuid.value = data.uuid
        }
      }).catch(error => {
        console.log("ERROR CAUGHT: " + error)
      })
}

async function submitLogin() {
  const loginDto = {
    uuid: "",
    loggedIn: false
  }
  const userDto = {
    uuid: uuid.value,
    username: username.value,
    password: password.value,
  }
  await axios.post("http://localhost:8080/api/login/submit", userDto)
      .then(async function (response) {
        const data = response.data
        if (data.uuid === undefined) return
        loginDto.uuid = data.uuid
        loginDto.loggedIn = data.loggedIn
        console.log(loginDto)
        submitted.value = true
        logged.value = loginDto.loggedIn;
  }).catch(function (error) {
    console.log("ERROR CAUGHT: " + error)
  })
}
</script>

<template>
  <div class="centered-container">
    <div v-if="!submitted">
      <h1>Login</h1>
      <form @submit.prevent>
        <label>Username</label><br>
        <input type="text" v-model="username" placeholder="Username..."/><br><br>
        <label>Password</label><br>
        <input type="password" v-model="password" placeholder="Password..."/><br>
        <button type="submit" style="margin:10px"  id="submit-btn" @click="submitLogin"><b>Login</b></button>
      </form>
    </div>
    <div v-else>
      <div v-if="logged">
        <h1>Login Success</h1>
        <p>you can now return to the game!</p>
      </div>
      <div v-else>
        <h1>Login Failed</h1>
        <p>please return to the game and perform the '/login' command again to retry!</p>
      </div>
    </div>
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