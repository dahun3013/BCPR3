<template>
  <div>
    <div v-on:click="googleLoginBtn">
      <img style="width: 46px; height: 46px" src="@/assets/logo.png" />
    </div>
    <div v-on:click="naverlogin">
      <img style="width: 46px; height: 46px" src="@/assets/logo.png" />
    </div>
  </div>
  <!-- 구글 버튼은 custom으로 만들었기 때문에 다음과 같이 스타일을 적용해준다. -->
</template>

<script>
import axios from "axios";
export default {
  name: "App",
  data() {
    return {
      client_id: "1e16I888ZQ5DJ1r2T29_",
      callbackUrl: "http://localhost:8100/api/auth/naver/callback",
      //  FIXME state 값 random string 으로 변경
      state: 123,
      naverLoginURL:
        "https://nid.naver.com/oauth2.0/authorize?response_type=code",
    };
  },

  methods: {
    async naverlogin() {
      const url = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${this.client_id}&redirect_uri=${this.callbackUrl}&state=1234`;
      window.location.replace(url);
    },
    /*
    //구글 버튼
    googleLoginBtn(){
      window.gapi.signin2.render('my-signin2', {
        scope: 'profile email',
        width: 240,
        height: 50,
        longtitle: true,
        theme: 'dark',
        onsuccess: this.onSuccess,
        onfailure: this.onFailure,
      });
      setTimeout(function () {
        if(!self.googleLoginCheck) {
          const auth = window.gapi.auth2.getAuthInstance();
          auth.isSignedIn.get();
          document.querySelector('.abcRioButton').click();
        }
      },500)
    },
    //구글 로그인 이후 실행되는 콜백함수(성공)
    async onSuccess(googleUser) {
      //const user_join_type = "g"
      const profile = googleUser.getBasicProfile();
      const googleEmail = profile.getEmail();
      const googleProfile = profile.getImageUrl();

      let googleData = {};
      googleData.email = googleEmail;
      googleData.password = 'DMTT';
      googleData.profile = googleProfile;
      await axios.get('/api/user/'+googleEmail).
      then(res=>{
        console.log("인증성공 ")
        console.log(res.data.email)
        console.log(res.data.profile)
        if(res.data==null || res.data=='')
        {
          axios.post('/api/user/save/normal',
          JSON.stringify(googleData),
          {headers: { "Content-Type": `application/json`, },})
          .then(res => {
            res
            console.log("가입성공")
          })
          .catch(err => {
            err
            console.log("가입에러")
          })
        }
      })
      .catch(err=>{
        err
        console.log("인증에러")
      })
    },
    //구글 로그인 콜백함수 (실패)
    onFailure(error) {
      // eslint-disable-next-line
      console.log(error);
    },
    */
  },
};
</script>
