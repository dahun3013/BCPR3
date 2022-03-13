<template>
  <div class="storage-ts-container col mb-5">
    <div class="storage-ts-output-cont">
      <div class="storage-img-box">
        <img :src="input" alt="" class="img-fit" />
      </div>
      <div style="display: flex">
        <button class="storage-ff1-btn mt-4">JPG / JPEG</button>
        <button class="storage-ff2-btn mt-4">PNG</button>
      </div>
    </div>
    <!--storage-ts-output-cont-end-->
  </div>
  <!--storage-ts-container-end-->
</template>

<script>
export default {
  name: "StorageCard",
  props: {
    media_no: Number,
    email: String,
    kind: String,
    input: String,
    output: String,
    trans_date: String,
  },
};
</script>

<style>
</style>