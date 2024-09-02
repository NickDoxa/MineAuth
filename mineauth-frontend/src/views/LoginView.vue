<script setup>
import LoginForm from "@/components/LoginForm.vue";
import Banner from "@/components/Banner.vue";
import NotFound from "@/views/NotFound.vue";
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";
import RegisterForm from "@/components/RegisterForm.vue";
import ServerBanner from "@/components/ServerBanner.vue";

const route = useRoute()
const isValidLink = ref(true)
const isValidUUID = ref(true)
const uuid = ref("")
checkLink()
checkUUID()

async function checkLink() {
  await axios.get("http://localhost:8080/api/login/code/exists/" + route.params.code)
      .then(async function(response) {
        const data = response.data;
        isValidLink.value = data.objExists
      }).catch(error => console.log("ERROR CAUGHT: " + error))
}

async function checkUUID() {
  await axios.get("http://localhost:8080/api/login/code/" + route.params.code)
      .then(async function (response) {
        const data = response.data;
        if (data.uuid !== undefined) {
          uuid.value = data.uuid
        }
      }).catch(error => {
        console.log("ERROR CAUGHT: " + error)
      })
  await axios.get("http://localhost:8080/api/user/exists/" + uuid.value)
      .then(async function(response) {
        const data = response.data;
        isValidUUID.value = data.objExists
      }).catch(error => console.log("ERROR CAUGHT: " + error))
}

</script>

<template>
  <div v-if="isValidLink">
    <Banner/>
    <div v-if="isValidUUID">
      <LoginForm/>
    </div>
    <div v-else>
      <RegisterForm/>
    </div>
    <ServerBanner/>
  </div>
  <div v-else>
    <NotFound/>
  </div>
</template>