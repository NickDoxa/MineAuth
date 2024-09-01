<script setup>
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";
import LoginForm from "@/components/LoginForm.vue";

const username = ref("")
const password = ref("")
const uuid = ref("")
const submitted = ref(false)

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

async function submitCredentials() {
  const loginDto = {
    uuid: "",
    loggedIn: false
  }
  const userDto = {
    uuid: uuid.value,
    username: username.value,
    password: password.value,
  }
  await axios.post("http://localhost:8080/api/user/create", userDto)
      .then(async function (response) {
        const data = response.data
        if (data.uuid === undefined) return
        submitted.value = true
      }).catch(function (error) {
        console.log("ERROR CAUGHT: " + error)
      })
}

</script>

<template>
  <div class="centered-container" v-if="!submitted">
    <h1>Register</h1>
    <p>Create a unique username and password for server authentication.
      Do <b>NOT</b> use your Mojang or Microsoft account credentials!</p>
    <p><i>Registration UUID:</i> {{uuid}}</p>
    <form @submit.prevent>
      <label>Username</label><br>
      <input type="text" v-model="username" placeholder="Username..."/><br><br>
      <label>Password</label><br>
      <input type="password" v-model="password" placeholder="Password..."/><br>
      <button type="submit" style="margin:10px"  id="submit-btn" @click="submitCredentials"><b>Register</b></button>
    </form>
  </div>
  <div v-else>
    <LoginForm/>
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