<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div>
          <h2>보관함</h2>
        </div>
        <div class="title-name">
          <h2 @click="$router.push('/')">PAGO BOOKS</h2>
        </div>
      </div>
      <!--profile-logo-end-->
    </div>
    <!--top-container-end-->

    <div class="storage-bottom-container px-5 pt-5 pb-2 justify-content-center">
      <div>
        <SSM :state="0" />
      </div>
    </div>
    <!--storage-bottom-container-end-->
  </div>
  <div class="storage-info px-5">보관함 안내 박스 테스트</div>
  <!--field_end-->
  <Modal @closeModal="loginModal = false" :loginModal="loginModal" />
</template>

<script>
// import $ from 'jquery'
import Modal from "@/components/Modal.vue";
import SSM from "@/components/StorageSelectMenu.vue";

export default {
  name: "papagoPage",
  data() {
    return {
      array: [],
      loginModal: false,
    };
  },
  components: {
    Modal,
    SSM,
  },
};
</script>

<style>
body {
  margin: 0;
}

textarea {
  resize: none;
}

.title-name {
  margin: auto;
}

.profile-logo > div > h2 {
  color: white;
  font-weight: bold;
}

.storage-bottom-container {
  background: white;
  border-radius: 100px 0px 0px 0px;
  overflow: auto;
}

.top-content {
  display: flex;
  justify-content: space-between;
  padding: 2rem 4rem 2rem 4rem;
  color: white;
}

.storage-ts-lg-ch {
  margin-right: 1rem;
  text-align: left;
}

.storage-ts-lg-ch > select {
  font-size: 1rem;
  padding: 0.5rem;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
}

select:hover {
  border-color: none;
}

select:focus {
  outline: none;
}
</style>