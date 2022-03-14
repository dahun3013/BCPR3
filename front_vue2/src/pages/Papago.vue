<template>
  <div class="field">
    <div class="top-container px-5 py-5">
      <div class="profile-logo">
        <div>
          <h2>간단번역</h2>
        </div>
        <div>
          <h2 @click="$router.push('/Home')">PAGO BOOKS</h2>
        </div>
        <div @click="loginModal = true">
          <img src="@/assets/weblogin1.png" alt="profile-logo" />
        </div>
      </div>
      <!--profile-logo-end-->
    </div>
    <!--top-container-end-->

    <div class="papago-bottom-container px-5 pt-5 pb-2">
      <div class="ts-container">
        <div class="ts-input-cont">
          <div class="ts-lg-ch">
            <select v-model = "from_language" name="ts-lg" id="ts-lg1" onchange="categoryChange(this)">
              <option value="ko">한국어</option>
              <option value="en">영어</option>
              <option value="ja">일본어</option>
              <option value="zh-CN">중국어</option>
              <option value="de">독일어</option>
              <option value="es">스페인어</option>
            </select>
          </div>
          <div>
            <textarea
              v-model="input"
              class="ppg-ts-box"
              placeholder="번역할 내용을 입력하세요"
            ></textarea>
          </div>
          <div class="ppg-trans-btn mt-4">
            <button @click="translation">번역하기</button>
          </div>
        </div>
        <!--ts-input-cont-end-->

        <div class="ts-output-cont">
          <div class="ts-lg-ch">
            <select v-model= "to_language" name="ts-lg" id="ts-lg2" >
              <option value="ko">한국어</option>
              <option value="en">영어</option>
              <option value="ja">일본어</option>
              <option value="zh-CN">중국어</option>
              <option value="de">독일어</option>
              <option value="es">스페인어</option>
            </select>
          </div>
          <div class="ppg-ts-box" style="margin-bottom: 30px">
            <form action="#">
              <output name="result" for="text">
                <div v-html="content"></div>
              </output>
            </form>
          </div>
          <div class="ppg-save-btn mt-4">
            <button>보관하기</button>
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
          margin-top: 7px;
          margin-bottom: 7px;
        "
      >
        <p>[ Tip : 로그인을 하시면 자료를 보관하고 내려받을 수 있습니다 ]</p>
      </div>
    </div>
    <!--papago-bottom-container-end-->
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

  <div class="login-modal px-5 py-5" v-if="loginModal == true">
    <h4>로그인</h4>
    <div class="loginBtns">
      <div>
        <img src="@/assets/naverLogo.png" alt="" /> 네이버 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/kakaoLogo.png" alt="" />카카오 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/googleLogo.png" alt="" />구글 아이디로 로그인
      </div>
      <div>
        <img src="@/assets/githubLogo.png" alt="" />깃허브 아이디로 로그인
      </div>
    </div>
    <div>
      <button @click="loginModal = false">닫기</button>
    </div>
  </div>
  <!--login-modal-end-->
</template>

<script>
// import $ from 'jquery'
import axios from "axios";
export default {
  name: "papagoPage",
  data() {
    return {
      loginModal: false,
      input: "",
      output: "",
      from_language: "ko",
      to_language: "en"
    };
  },
  methods: {
    async translation() {
      let form = new FormData();
      form.append("text", this.input);
      form.append("from_language", this.from_language);
      form.append("to_language", this.to_language);
      await axios
        .post("/api/papago/json", form, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          this.output = res.data;
          
        })
        .catch((err) => {
          console.log("refreshToken error : ", err.config);
        });
    },
  },
  components: {},
  computed: {
    content() {
      return this.output.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
  mounted() {},
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

.bottom-container {
  background: white;
  border-radius: 100px 0px 0px 0px;
}

.papago-bottom-container {
  background: white;
  border-radius: 100px 0px 0px 0px;
}

.ts-container {
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

.ts-lg-ch {
  text-align: left;
  margin-bottom: 1.5rem;
}

.ts-lg-ch > select {
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

.ts-input-cont {
  width: 35%;
  height: 100%;
  margin: 1rem;
  padding: 2rem 3rem 2rem 3rem;
  border: 1px solid #dbdbdb;
  border-radius: 25px;
  text-align: center;
}

.ppg-ts-box {
  width: 100%;
  height: 350px;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  padding: 1rem;
  text-align: left;
}

.ppg-trans-btn > button {
  width: 100%;
  padding: 0.5rem;
  background: #0d66ff;
  color: white;
  border: none;
  border-radius: 10px;
  font-size: 1rem;
  font-weight: bold;
}

.ppg-save-btn > button {
  width: 100%;
  padding: 0.5rem;
  background: white;
  color: #0d66ff;
  border: 1px solid #0d66ff;
  border-radius: 10px;
  font-size: 1rem;
  font-weight: bold;
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
</style>