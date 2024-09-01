<script setup>
import LoginForm from "@/components/LoginForm.vue";
import Banner from "@/components/Banner.vue";
import NotFound from "@/views/NotFound.vue";
import {ref} from "vue";

const isValidLink = ref(true);


</script>

<template>
  <!-- REPLACE -->
  <div v-if="1>0">
    <Banner/>
    <LoginForm/>
  </div>
  <div v-else>
    <NotFound/>
  </div>
</template>