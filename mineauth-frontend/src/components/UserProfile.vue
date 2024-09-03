<script setup>
import {ref} from "vue";

const props = defineProps(["uuid"])

const imageSrc = ref("https://api.mineatar.io/body/full/")

imageSrc.value = imageSrc.value + props.uuid;

</script>

<template>
  <div class="centered-img">
    <img alt="Player Skin Render" :src="imageSrc" class="img-banner">
  </div>
</template>

<style scoped>

.img-banner {
  width: auto;
  height: 30%;
}

.centered-img {
  text-align: center;
}

</style>