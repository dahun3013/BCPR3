<template>
  <div>
    <ul
      class="nav nav-pills mb-3"
      id="pills-tab"
      role="tablist"
    >
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link"
          :class="{ active: isStateOne() }"
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
          :class="{ active: isStateTwo() }"
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
          class="nav-link"
          :class="{ active: isStateThree() }"
          id="pills-contact-tab"
          data-bs-toggle="pill"
          data-bs-target="#pills-contact"
          type="button"
          role="tab"
          aria-controls="pills-contact"
          aria-selected="false"
          @click="$router.push('/STTStorage')"
        >
          매체번역
        </button>
      </li>
      <li class="nav-item mx-5" role="presentation">
        <button
          class="nav-link"
          :class="{ active: isStateFour() }"
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
  </div>
</template>

<script>
export default {
  name: "StorageSelectMenu",
  props: {
    state: {
      type: Number,
      default: 1,
    },
  },
  methods: {
    isStateOne() {
      console.log("1 : " + this.state);
      if (this.state == 1) return true;
      else return false;
    },
    isStateTwo() {
      console.log("2 : " + this.state);
      if (this.state == 2) return true;
      else return false;
    },
    isStateThree() {
      console.log("3 : " + this.state);
      if (this.state == 3) return true;
      else return false;
    },
    isStateFour() {
      console.log("4 : " + this.state);
      if (this.state == 4) return true;
      else return false;
    },
  },
};
</script>

<style>
</style>
