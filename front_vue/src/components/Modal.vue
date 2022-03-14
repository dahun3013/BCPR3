<template>
    <div class="login-modal px-5 py-5" v-if="loginModal == true">
    <h4>로그인</h4>
    <div class="loginBtns">
      <div>
        <img src="@/assets/naverLogo.png" alt=""> 네이버 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/kakaoLogo.png" alt="">카카오 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/googleLogo.png" alt="">구글 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/githubLogo.png" alt="">깃허브 아이디로 로그인
      </div>
    </div>
    <div>
      <button @click="$emit('closeModal')">닫기</button>
    </div>
  </div><!--login-modal-end-->
</template>

<script>
export default {
    name: 'LoginModal',
    props : {
        loginModal : Boolean,
    },
    methods: {

    },
}
</script>

<style>

</style>