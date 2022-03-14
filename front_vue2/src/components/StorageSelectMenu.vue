<template>
  <div>
    <ul
      class="nav nav-pills mb-3 justify-content-center"
      id="pills-tab"
      role="tablist"
    >
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link active"
          id="pills-home-tab"
          data-bs-toggle="pill"
          data-bs-target="#pills-home"
          type="button"
          role="tab"
          aria-controls="pills-home"
          aria-selected="true"
          @click="$router.push('/OCRStorage')"
        >
          문서변환
        </button>
      </li>
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link"
          id="pills-profile-tab"
          data-bs-toggle="pill"
          data-bs-target="#pills-profile"
          type="button"
          role="tab"
          aria-controls="pills-profile"
          aria-selected="false"
        >
          음성변환
        </button>
      </li>
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link active"
          id="pills-contact-tab"
          data-bs-toggle="pill"
          data-bs-target="#pills-home"
          type="button"
          role="tab"
          aria-controls="pills-home"
          aria-selected="true"
          @click="$router.push('/STTStorage')"
        >
          매체번역
        </button>
      </li>
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link"
          id="pills-contact-tab"
          data-bs-toggle="pill"
          data-bs-target="#pills-contact"
          type="button"
          role="tab"
          aria-controls="pills-contact"
          aria-selected="false"
          @click="$router.push('/PapagoStorage')"
        >
          간단번역
        </button>
      </li>
    </ul>
    <div
      class="
        row
        gx-4 gx-lg-5
        row-cols-1 row-cols-sm-1 row-cols-lg-3 row-cols-xl-3
        justify-content-center
      "
    >
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "StorageSelectMenu",
};
</script>

<style>
</style>