<template>

  <div id="mainBox"> <!-- 전체 -->
        <div id="headerArea"> <!-- 상단 blue -->
            <div id="loginArea"> <!-- 로그인 버튼 -->
                <button type="button" id="loginBtn" class="loginBtn"><img src="./assets/weblogin1.png" width="50" id="loginImage" ></button>
            </div>
            <br><br><br><br>
            <h1>PAGO BOOKS</h1>
            <h5>로그인을 하시면 저장 기능을 사용하실 수 있습니다.</h5>
            
            <div class="modal">
                <img src="./assets/cancleBtn.png" class="cancleBtn">
                <div id="loginBtns">
                <button type="button" id="naverBtn"><img src="./assets/naverLogo.png" width="35px"> 네이버 아이디로 로그인</button>
                <button type="button" id="googleBtn"><img src="./assets/googleLogo.png" width="40px"> 구글 아이디로 로그인</button>
                <button type="button" id="githubBtn"><img src="./assets/githubLogo.png" width="45px"> Github 아이디로 로그인</button>
            </div>
                <h3>아이디/비밀번호 찾기</h3>
                
            </div>

        </div>
          <div id="images">
            <img src="./assets/1.png" id="firstImage">
            <img src="./assets/2.png" id="secondImage">
            <img src="./assets/3.png" id="thirdImage">
            <router-link to="/SimpleTranse.vue"><img src="./assets/4.png" id="fourthImage"></router-link>
          </div>
        <div id="bottomBox"> <!-- 하단 white -->
            <img src="source/사각형 2.png" id="bottomWhite">
        </div>
    </div>

</template>

<script>


export default {
  name: 'App',
  data() {
    return {
      
    }
  },

  components: {
    
  }
}
</script>

<style>

  body {
    margin: 0;
  }

  #mainBox {
    background-color: #0d66ff;
  }

  #headerArea {
    color: white;
    text-align: center;
  }

  #loginArea {
    padding: 20px;
  }

  #loginImage {
    float: right;
  }

  #loginBtn {
    background-color: #0d66ff;
    border: 0px;
    border-radius: 13px;
    float: right;
  }

  #bottomBox {
    background-color: white;
    border-radius: 90px 0px 0px 0px;
    position: relative;
    z-index: 2;
  }

  #images {
    position: relative;
    z-index: 3;
    top: +100px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #bottomWhite {
    max-width: 100%;
    height: auto;
  }

  .modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 500px;
    height: 500px;

    display: none;
    justify-content: center;
    align-items: center;

    border-radius: 30px;
    background-color: white;

    color: black;
    z-index: 8;
    box-shadow: 2px 2px 10px lightgrey;
  }

  #naverBtn {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #d5d5d5;
    background-color: white;
    width: 300px;
    height: 50px;
    border-radius: 15px;
    margin: 10px;
    margin-top: 15%;
  }

  #googleBtn {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #d5d5d5;
    background-color: white;
    width: 300px;
    height: 50px;
    border-radius: 15px;
    margin: 10px;
  }

  #githubBtn {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #d5d5d5;
    background-color: white;
    width: 300px;
    height: 50px;
    border-radius: 15px;
    margin: 10px;
  }

  #loginBtns {
    display: inline-block;
    padding: 20px;
    vertical-align: center;
  }

  .cancleBtn {
    width: 20px;
    display: flex;
    justify-content: flex-end;
    margin-left: auto;
    padding: 25px;
  }

</style>
