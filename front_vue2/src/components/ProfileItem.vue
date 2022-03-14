<template>
  <div id="profile">
    <img :src="profile" alt="profile-logo" />
    <p>{{ email }}</p>
  </div>
</template>
<script>
export default {
  name: "ProfileItem",
  props: {
    profile: {
      type: String,
      default: require("@/assets/weblogin1.png"),
    },
    email: {
      type: String,
      default: "로그인 요청드립니다.",
    },
  },
  data() {
    return {};
  },
  methods: {},
  computed: {},
};
</script>
<style scoped>
#ProfileItem {
  width: 5vw;
  height: 5vh;
  background-color: slategray;
}
</style>
