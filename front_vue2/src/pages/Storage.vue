<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div style="text-align: left">
          <h2>보관함</h2>
        </div>
        <div style="text-align: center">
          <h2 @click="$router.push('/')">PAGO BOOKS</h2>
        </div>
        <!-- <div @click="loginModal = true">
          <img src="@/assets/weblogin1.png" alt="profile-logo" />
        </div> -->
      </div>
      <!--profile-logo-end-->
    </div>
    <!--top-container-end-->

    <div class="storage-bottom-container px-5 pt-5 pb-2">
      <div>
        <SSM />
      </div>
    </div>
    <!--storage-bottom-container-end-->
      <div class="row gx-4 gx-lg-5 row-cols-1 row-cols-sm-1 row-cols-lg-3 row-cols-xl-3 justify-content-center">
        <router-view></router-view>
      </div>
  </div>
  <!--field_end-->

  <div class="px-5"><hr /></div>

  <div class="footer container">
    <p class="mx-3">파고북스 이용약관</p>
    <p class="mx-3">의견제안</p>
    <p class="mx-3">개인정보처리방침</p>
    <p class="mx-3">책임의 한계와 법적고지</p>
    <p class="mx-3">준수사항</p>
  </div>
</template>

<script>
// import $ from 'jquery'
import axios from "axios";
import SSM from "@/components/StorageSelectMenu.vue"

export default {
  name: "papagoPage",
  data() {
    return {
      image: "",
      array: [],
    };
  },
  components: {
    SSM,
  },
  mounted() {
    if (
      this.$store.state.userInfo.email != null ||
      this.$store.state.userInfo.email != ""
    ) {
      axios
        .get("/api/ocr/list" + "/" + this.$store.state.userInfo.email)
        .then((res) => {
          console.log(res.data);
          this.array = [];
          this.array = res.data;

          for (var i = 0; i < this.array.length; i++) {
            //console.log("파일명 : " + this.array[i].input);
            this.array[i].input =
              "http://localhost:8200/resources/media_trans/" +
              this.$store.state.userInfo.email +
              "/" +
              this.array[i].input;
          }

          //console.log("다운로드 개수 : " + this.array.length);

          this.image = this.array[0].input;
          // 'http://localhost:8200/resourse/**_trans/이메일/파일명'
          console.log(this.image);
        })
        .catch((err) => {
          console.log(err);
          console.log("다운로드 실패");
        });
    }
  },
  methods: {
    uploadImg() {
      console.log("들어왔다");
      var image = this.$refs["image"].files[0];

      const url = URL.createObjectURL(image);
      this.image = url;
      console.log(url);
      console.log(this.image);
    },
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