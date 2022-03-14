<template>
  <div>
    <div style="float: right;" @click="loginModal = true"><!--이거 빼고 수정가능-->
      <ProfileItem :profile="getProfile" :email="getEmail"/>
    </div><!--이거 빼고 수정가능-->
    <router-view></router-view>
  </div>
  <Modal @closeModal="loginModal = false" :loginModal="loginModal" />
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import Modal from "@/components/Modal.vue";

export default {
  name: "App",
  data() {
    return {
      loginModal: false,
    };
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
</style>
