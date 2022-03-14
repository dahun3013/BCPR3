<template>
  <div @click="loginModal = true"><!--이거 빼고 수정가능-->
    <ProfileItem :profile="getProfile" :email="getEmail"/>
  </div><!--이거 빼고 수정가능-->
  <div>
    <router-view></router-view>
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
  }

}
</script>

<style>

</style>