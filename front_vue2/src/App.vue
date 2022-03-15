<template>
  <div>
    <div class="profileBox mx-5 mt-5">
      <div @click="loginModal = true"><!--이거 빼고 수정가능-->
        <ProfileItem :profile="getProfile" :email="getEmail"/>
      </div><!--이거 빼고 수정가능-->
      <div class="logoutBtn">
        로그아웃
      </div>
    </div>
    <router-view></router-view>
  </div>
  <div class="px-5"><hr /></div>

  <div class="footer container">
    <p class="mx-3">파고북스 이용약관</p>
    <p class="mx-3">의견제안</p>
    <p class="mx-3">개인정보처리방침</p>
    <p class="mx-3">책임의 한계와 법적고지</p>
    <p class="mx-3">준수사항</p>
  </div>
  <Modal @closeModal="loginModal = false" :loginModal="loginModal" />
</template>

<script>

import ProfileItem from '@/components/ProfileItem.vue'
import Modal from "@/components/Modal.vue"

export default {
  
  name: 'App',
  data() {
    return {
      loginModal : false,
    }
  },
  components: {
    ProfileItem,
    Modal,
  },
  computed:{
    getProfile(){
      if(this.$store.state.userInfo == null)
        return null;
      return this.$store.state.userInfo.profile;
    },
    getEmail(){
      if(this.$store.state.userInfo == null)
        return null;
      return this.$store.state.userInfo.email;
    }
  },
}
</script>

<style>
.profileBox{
  float: right;
}

.profileBox > div {
  float: left;
}

.logoutBtn {
  color: white;
  border: 1px solid white;
  border-radius: 10px;
  cursor: pointer;
  padding: 10px 20px 10px 20px;
}
</style>