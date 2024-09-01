<script setup>
import LoginForm from "@/components/LoginForm.vue";
import Banner from "@/components/Banner.vue";
import NotFound from "@/views/NotFound.vue";
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

const route = useRoute()
const isValidLink = ref(true);
isValid()

async function isValid() {
  await axios.get("http://localhost:8080/api/login/code/exists/" + route.params.code)
      .then(async function(response) {
        const data = response.data;
        isValidLink.value = data.codeExists
        console.log("valid: " + data.codeExists)
      })
}

</script>

<template>
  <!-- REPLACE -->
  <div v-if="isValidLink">
    <Banner/>
    <LoginForm/>
  </div>
  <div v-else>
    <NotFound/>
  </div>
</template>