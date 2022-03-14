<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div>
          <h2>매체번역</h2>
        </div>
        <div>
          <h2 @click="$router.push('/')">PAGO BOOKS</h2>
        </div>
        <!-- <div @click="loginModal = true">
          <img src="@/assets/weblogin1.png" alt="profile-logo" />
        </div> -->
      </div>
      <!--profile-logo-end-->
    </div>
    <!--top-container-end-->

    <div class="mtt-bottom-container px-5 pt-5 pb-2">
      <div class="mtt-ts-container">
        <div class="mtt-ts-input-cont">
          <div>
            <form>
          
              <audio class="player" controls ref="player" style="display:none">
                <source src="" ref="source" />
              </audio>
              <video class="player2" controls ref="player2">
                <source src="" ref="source" />
              </video>
              <div class="ocr-ts-lg-ch mt-4">
                <select name="ts-lg" id="ts-lg" v-model="lang">
                  <option value="Kor" selected>한국어</option>
                  <option value="Eng">영어</option>
                  <option value="Jpn">일본어</option>
                  <option value="Chn">중국어</option>
                </select>
              </div>
              <!-- <button type="button" @click="sendData()">전송</button> -->
            </form>
          </div>
          <div class="mtt-cf-btn mt-4">
            <label for="chooseFile">파일 가져오기</label>
            <form method="post" enctype="multipart/form-data">
              <input
                ref="image"
                @change="uploadImg()"
                type="file"
                id="chooseFile"
                name="chooseFile"
                accept="audio/* video/*"
                style="display: none"
              />
            </form>
          </div>
        </div>
        <!--ts-input-cont-end-->

        <div class="ts-output-cont">
          <div class="mtt-ts-box">
            <form action="#">
              <output name="result" for="text">
                <div v-html="content"></div>
              </output>
            </form>
          </div>

          <div style="display: flex">
            <div class="ocr-ts-lg-ch mt-4">
              <select name="ts-lg" id="ts-lg" v-model="papagolang">
                <option value="ko">한국어</option>
                <option value="en">영어</option>
                <option value="ja">일본어</option>
                <option value="zh-CN">중국어</option>
                <option value="de">독일어</option>
                <option value="es">스페인어</option>
              </select>
            </div>
            <button @click="translation" class="ocr-trans-btn mt-4">
              번역하기
            </button>
          </div>
          <div v-show="isLogin">
            <button @click="upload" class="ocr-trans-btn mt-4">저장하기</button>
          </div>
        </div>
        <!--ts-output-cont-end-->
      </div>
      <!--ts-container-end-->
      <br />
      <div
        style="
          text-align: center;
          font-weight: bold;
          margin-top: 21px;
          margin-bottom: 21px;
        "
      >
        <p>[ Tip : 로그인을 하시면 자료를 보관하고 내려받을 수 있습니다 ]</p>
      </div>
    </div>
    <!--mtt-bottom-container-end-->
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

export default {
  name: "papagoPage",
  data() {
    return {
      image: "",
      file: ``,
      lang: "Kor",
      text: "",
      papagolang: "en",
      showInput: false,
    };
  },
  components: {

  },
  methods: {
    async upload() {
      let form = new FormData();
      form.append("email", this.$store.state.userInfo.email);
      form.append("trans_date", new Date().toISOString());
      form.append("kind", "images");
      form.append("input", this.$refs["image"].files[0]);
      form.append("output", this.text);

      await axios

        .post("/api/Stt/upload", form, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log("refreshToken error : ", err.config);
        });
    },

    async translation() {
      let form = new FormData();
      if (this.lang == "Kor") {
        this.lang = "ko";
      }
      if (this.lang == "Eng") {
        this.lang = "en";
      }
      if (this.lang == "Jpn") {
        this.lang = "ja";
      }
      if (this.lang == "Chn") {
        this.lang = "zh-CN";
      }
      form.append("text", this.text);
      form.append("from_language", this.lang);
      form.append("to_language", this.papagolang);
      await axios
        .post("/api/papago/json", form, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          
          console.log(res);
          this.text = res.data;
          console.log(this.text);
          this.lang="Eng"
          console.log(this.lang);
        })
        .catch((err) => {
          console.log("refreshToken error : ", err.config);
        });
    },
    async uploadImg() {
      console.log("들어왔다");
      this.showInput=true;
      this.$refs.source.src = "";
      var image = this.$refs["image"].files[0];
      const url = URL.createObjectURL(image);
      console.log(`image : ` + image);
      this.$refs.source.src = url;
      this.$refs.player.load();
      this.$refs.player2.load();
      //this.image = url;
      let form = new FormData();
      console.log(`url :` + url);
      form.append("file", image);
      form.append("lang", this.lang);
      await axios
        .post("/api/Stt", form, {
          headers: {
            "Content-Type": "multipart/form-data",
            Accept: "*/*",
          },
        })
        .then((res) => {
          console.log(res);
          console.log(res.data);
          console.log(res.data.text);
          this.text = res.data.text;
        })
        .catch((err) => {
          console.log("refreshToken error : ", err.config);
        });
    },
  },
  mounted() {},
  computed: {
    content() {
      return this.text.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
    isLogin() {
      return this.$store.state.isLogin;
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
.mtt-bottom-container {
  background: white;
  border-radius: 100px 0px 0px 0px;
}
.mtt-ts-container {
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
.mtt-ts-lg-ch {
  margin-right: 1rem;
  text-align: left;
}
.mtt-ts-lg-ch > select {
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
.mtt-ts-input-cont {
  width: 35%;
  margin: 1rem;
  padding: 2rem 3rem 2rem 3rem;
  border: 1px solid #dbdbdb;
  border-radius: 25px;
  text-align: center;
}
.mtt-ts-box {
  width: 100%;
  height: 400px;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  padding: 1rem;
  text-align: left;
  overflow: auto;
}
.mtt-ts-box::-webkit-scrollbar {
  display: none;
}
.mtt-cf-btn {
  padding: 0.5rem;
  background: white;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  font-size: 1rem;
}
.mtt-trans-btn {
  width: 100%;
  margin-left: 1rem;
  padding: 0.5rem;
  background: #0d66ff;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  font-size: 1rem;
}
.mtt-trans-btn > button {
  width: 100%;
  color: white;
  background: #0d66ff;
  border: none;
  border-radius: 10px;
  font-size: 1rem;
}
.ts-output-cont {
  width: 35%;
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
.player {
  width: 100%;
}
.player2{
  width:100%;
}
</style>
