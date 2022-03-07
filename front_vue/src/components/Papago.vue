<template>
  <div>
      <div>
          <div class="container">
        <div class="top-content">
            <h2>간단번역</h2>
            <div id="loginArea"> <!-- 로그인 버튼 -->
                <button type="button" id="loginBtn" class="loginBtn"><img src="@/assets/weblogin1.png" width="50" id="loginImage"></button>
            </div>
        </div>
        <div class="round-cont">
            <div style="width: 100%; display: flex; justify-content: center;">
                <div class="ts-input-cont">
                    <div class="ts-lg-ch">
                        번역언어 선택 영역(지우고 사용하세요)
                    </div>
                    <div class="ts-input">
                        지우고 사용하세요
                    </div>
                    <button class="trans-btn">번역하기</button>
                </div>
                <div class="ts-output-cont">
                    <div class="ts-lg-ch">
                        번역언어 선택 영역(지우고 사용하세요)
                    </div>
                    <div class="ts-output">
                        지우고 사용하세요
                    </div>
                </div>
            </div>
        </div>
        <div class="output-record">
            기록하기
        </div>
    </div>
      </div>
      <div>

      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
  html {
      margin: 0;
      width: 100%;
      height: 100%;
  }

  body {
      margin: 0;

  }

  .container {
      width: 100%;
      height: 100%;
      background: #0d66ff;
      position: relative;
  }

  .top-content {
      display: flex;
      justify-content: space-between;
      padding: 2rem 4rem 2rem 4rem;
      color: white;
  }

  .round-cont {
      height: 100%;
      background: white;
      border: 1px;
      border-radius: 50px 0px 0px 0px;
      padding-top: 5rem;
      padding-left: 10rem;
      padding-right: 10rem;
  }

  .ts-lg-ch {
      text-align: right;
      margin-bottom: 2rem;
  }

  .ts-input-cont {
      width: 35%;
      height: 500px;
      margin: 1rem;
      padding: 3rem;
      border: 1px solid black;
      border-radius: 50px 0px 0px 0px;
      text-align: center;
  }

  .ts-input {
      height: 350px;
      border: 1px solid black;
      text-align: left
  }

  .ts-input-cont > .trans-btn {
      width: 70%;
      height: 10%;
      bottom: 0px;
      margin-top: 3rem;
      margin-bottom: 2rem;
      margin-left: auto;
      margin-right: auto;
      background: white;
      border-radius: 15px;
      font-size: 1rem;
  }

  .ts-output-cont {
      width: 35%;
      margin: 1rem;
      padding: 3rem;
      border: 1px solid black;
      border-radius: 50px 0px 0px 0px;
  }

  .ts-output {
      height: 350px;
      border: 1px solid black;
  }

  .output-record {
      float: right;
      text-align: center;
      width: 20%;
      height: 10%;
      margin: 0px;
      padding: 2rem;
      background: #0d66ff;
      border-radius: 50px 0px 0px 0px;
  }
</style>