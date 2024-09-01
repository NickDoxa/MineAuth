<script setup>
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

const username = ref("")
const password = ref("")
const uuid = ref("")
const route = useRoute()
retrieveLoginCode()

async function retrieveLoginCode() {
  await axios.get("http://localhost:8080/api/login/code/" + route.params.code)
      .then(async function (response) {
        const data = response.data;
        if (data.uuid !== undefined) {
          uuid.value = data.uuid;
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
        if (data.uuid === undefined) {
          console.log("NULL RECEIVED")
          //TODO THIS MEANS THE LINK WAS NOT ABLE TO GENERATE THEIR UUID, SEND NOT FOUND
          return
        }
        loginDto.uuid = data.uuid
        loginDto.loggedIn = data.loggedIn
        console.log(loginDto)
        if (loginDto.loggedIn) {
           //TODO SEND SUCCESS
          console.log("success login")
        } else {
          //TODO SEND FAILED
          console.log("failed login")
        }
  }).catch(function (error) {
    console.log("ERROR CAUGHT: " + error)
  })
}
</script>

<template>
  <div class="centered-container">
    <h1>Login</h1>
    <form @submit.prevent>
      <label>Username</label><br>
      <input type="text" v-model="username" placeholder="Username..."/><br><br>
      <label>Password</label><br>
      <input type="password" v-model="password" placeholder="Password..."/><br>
      <button type="submit" style="margin:10px"  id="submit-btn" @click="submitLogin"><b>Login</b></button>
      <!-- DEBUG TESTING -->
      <br><button @click="console.log(username + ' ' + password + ' ' + uuid)">TEST</button>
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