<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div>
          <h5></h5>
        </div>
        <div @click="loginModal = true">
          <ProfileItem :email="null" :image="null" />
        </div>
      </div>

      <div class="top-banner my-5">
        <h1 class="display-1" style="font-weight: bold">PAGO BOOKS</h1>
        <h5 class="mt-3">
          당신의 자료를 손쉽게 번역하고, 변환하고, 저장해보세요!
        </h5>
      </div>
    </div>
    <!--top-container-end-->

    <div class="bottom-container px-5 pt-5">
      <div class="main-btn">
        <img
          src="@/assets/1.png"
          alt="문서변환"
          @click="$router.push('/ocr')"
        />
        <img
          src="@/assets/2.png"
          alt="음성변환"
          @click="$router.push('/tts')"
        />
        <img
          src="@/assets/3.png"
          alt="매체번역"
          @click="$router.push('/mtt')"
        />
        <img
          src="@/assets/4.png"
          alt="간단번역"
          @click="$router.push('/papago')"
        />
      </div>

      <br /><br /><br /><br />

      <div style="text-align: center; font-weight: bold">
        <p @click="$router.push('/storage')">
          [ Tip : 로그인을 하시면 자료를 보관하고 내려받을 수 있습니다 ]
        </p>
      </div>

      <br /><br /><br />

      <hr />

      <div class="footer container">
        <p class="mx-3">파고북스 이용약관</p>
        <p class="mx-3">의견제안</p>
        <p class="mx-3">개인정보처리방침</p>
        <p class="mx-3">책임의 한계와 법적고지</p>
        <p class="mx-3">준수사항</p>
      </div>
    </div>
    <!--bottom-container-end-->
    <Modal @closeModal="loginModal = false" :loginModal="loginModal" />
  </div>
  <!--field-end-->
</template>

<script>
import Modal from "@/components/Modal.vue";
import ProfileItem from "@/components/ProfileItem.vue";

export default {
  name: "HomePage",
  data() {
    return {
      loginModal: false,
      email: null,
      image: null,
    };
  },

  components: {
    Modal,
    ProfileItem,
  },

  mounted() {
    if (this.$store.state.userInfo != null) {
      this.email = this.$store.state.userInfo.email;
      this.image = this.$store.state.userInfo.profile;
    }
  },
};
</script>

<style>
body {
  margin: 0;
}

.field {
  background: #0d66ff;
}

.top-container {
  width: 100%;
  height: 50%;
}

.profile-logo {
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

.profile-logo > div {
  text-align: center;
  vertical-align: middle;
}

.profile-logo > div > img {
  width: 65%;
}

.top-banner > h5 {
  color: #f1f1f5;
  text-align: center;
}

.display-1 {
  color: white;
  text-align: center;
}

.main-btn {
  text-align: center;
}

.main-btn > img {
  cursor: pointer;
}

.bottom-container {
  background: white;
  border-radius: 100px 0px 0px 0px;
}

.footer {
  display: flex;
  justify-content: center;
}

.footer > p {
  margin-top: 0.5rem;
  font-size: 0.75rem;
}
</style>