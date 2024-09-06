<script setup>

import {ref} from "vue";
import axios from "axios";

const bannerExists = ref(false)
const imageUrl = ref("")
getServerBanner()

async function getServerBanner() {
  await axios.get("/api/images/banner")
      .then(async function (response) {
        const data = response.data;
        if (data.url !== undefined) {
          bannerExists.value = true
          imageUrl.value = data.url
        }
      }).catch(error => {
        bannerExists.value = false
        console.log("No banner found!")
        console.log("Banner not found exception reason: " + error)
      })
}

</script>

<template>
<div v-if="bannerExists" class="centered">
  <img alt="Server Banner" :src="imageUrl" class="banner">
</div>
</template>

<style scoped>
.banner {
  width: 35%;
  height: auto;
  text-align: center;
  margin: auto;
}

.centered {
  text-align: center;
  color: white;
  margin-top: 5%;
}
</style>