<template>
  <div>
    <div @click="loginModal = true">
      <ProfileItem :email="getEmail" :profile="getProfile" />
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
export default {
  name: "App",
  data() {
    return {
      loginModal: false,
    };
  },
  components: {
    ProfileItem,
  },
};
</script>

<style>
</style>