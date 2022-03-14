<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div>
          <h2>문서변환 보관함</h2>
        </div>
        <div style="text-align: center; margin-right: 140px">
          <h2 @click="$router.push('/')">PAGO BOOKS</h2>
        </div>
      </div>
      <!--profile-logo-end-->
    </div>
    <!--top-container-end-->

    <div class="storage-bottom-container px-5 pt-5 pb-2">
      <SSM :state="1" />
    </div>
    <!--storage-bottom-container-end-->
  </div>
  <!--field_end-->

  <div
    class="
      row
      gx-4 gx-lg-5
      row-cols-1 row-cols-sm-1 row-cols-lg-3 row-cols-xl-3
      justify-content-center
    "
  >
    <StorageCard
      :media_no="a.media_no"
      :email="a.email"
      :kind="a.kind"
      :input="a.input"
      :output="a.output"
      :trans_date="a.trans_date"
      v-for="(a, i) in array"
      :key="i"
    />
  </div>

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
import StorageCard from "@/components/StorageCard.vue";
import SSM from "@/components/StorageSelectMenu.vue";

export default {
  name: "papagoPage",
  data() {
    return {
      image: "",
      array: [],
    };
  },
  components: {
    StorageCard,
    SSM,
  },
  mounted() {
    console.log(this.$store.state.userInfo.email);
    console.log(this.$store.state.userInfo.profile);
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

::-webkit-scrollbar {
  display: none;
}

.storage-ts-container {
  width: 100%;
  display: flex;
  justify-content: center;
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

.storage-img-box {
  width: 100%;
  height: 400px;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  overflow: auto;
}

.storage-img-box::-webkit-scrollbar {
  display: none;
}

.img-fit {
  width: 100%;
  border-radius: 10px;
  object-fit: contain;
}

.storage-ff1-btn {
  width: 100%;
  margin-right: 1rem;
  padding: 0.5rem;
  color: white;
  background: #0d66ff;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  font-size: 1rem;
}

.storage-ff2-btn {
  width: 100%;
  margin-left: 1rem;
  padding: 0.5rem;
  color: white;
  background: #0d66ff;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  font-size: 1rem;
}

.storage-ts-output-cont {
  width: 100%;
  margin: 1rem;
  padding: 2rem 3rem 2rem 3rem;
  border: 1px solid #dbdbdb;
  border-radius: 25px;
}

.output-record {
  text-align: end;
}

.output-record > button {
  text-align: center;
  width: 20%;
  padding: 1.25rem;
  background: #0d66ff;
  border-radius: 50px 0px 0px 0px;
}
</style>