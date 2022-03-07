<template>
    <div class="field">
        <div class="top-container px-5 py-5">
            <div class="profile-logo">
                <div>
                    <h2>매체번역</h2>
                </div>
                <div>
                    <h2 @click="$router.push('/Home')">PAGO BOOKS</h2>
                </div>
                <div @click="loginModal = true">
                    <img src="@/assets/weblogin1.png" alt="profile-logo">
                </div>
            </div><!--profile-logo-end-->
        </div><!--top-container-end-->

        <div class="mtt-bottom-container px-5 pt-5 pb-2">
            <div class="mtt-ts-container">
                <div class="mtt-ts-input-cont">
                    <div class="mtt-ts-box">
                        <img :src="image" alt="">
                    </div>
                    <div class="mtt-cf-btn mt-4">
                        <label for="chooseFile">파일 가져오기</label>
                        <form method="post" enctype="multipart/form-data">
                            <input ref="image" @change="uploadImg()" type="file" id="chooseFile" name="chooseFile" accept="image/*" style="display: none;">
                        </form>
                        
                    </div>
                </div><!--ts-input-cont-end-->

                <div class="ts-output-cont">
                    <div class="mtt-ts-box">
                        <form action="#">
                            <output name="result" for="text"></output>
                        </form>
                    </div>
                    <div style="display: flex;">
                        <div class="mtt-ts-lg-ch mt-4">
                            <select name="ts-lg" id="ts-lg">
                                <option value="kr">한국어</option>
                                <option value="en">영어</option>
                                <option value="jp">일본어</option>
                                <option value="cn">중국어</option>
                                <option value="gm">독일어</option>
                                <option value="sp">스페인어</option>
                            </select>
                        </div>
                        <div class="mtt-trans-btn mt-4">
                            <button>번역하기</button>
                        </div>
                    </div>
                    
                </div><!--ts-output-cont-end-->
            </div><!--ts-container-end-->
            <br>
            <div style="text-align: center; font-weight: bold; margin-top: 21px; margin-bottom: 21px">
                <p>[ Tip : 로그인을 하시면 자료를 보관하고 내려받을 수 있습니다 ]</p>
            </div>
        </div><!--mtt-bottom-container-end-->
    </div><!--field_end-->

    <div class="px-5"><hr></div>
    
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
                <img src="@/assets/naverLogo.png" alt=""> 네이버 아이디로 로그인
            </div>
            <div>
                <img src="@/assets/kakaoLogo.png" alt="">카카오 아이디로 로그인
            </div>
            <div>
                <img src="@/assets/googleLogo.png" alt="">구글 아이디로 로그인
            </div>
            <div>
                <img src="@/assets/githubLogo.png" alt="">깃허브 아이디로 로그인
            </div>
        </div>
        <div>
            <button @click="loginModal = false">닫기</button>
        </div>
    </div><!--login-modal-end-->

</template>

<script>

// import $ from 'jquery'

export default {
    name: 'papagoPage',
    data() {
        return {
            image : '',
            loginModal : false,
        }
    },
    components : {
        
    },

    methods: {
        uploadImg() {
            console.log('들어왔다')
            var image = this.$refs['image'].files[0]

            const url = URL.createObjectURL(image)
            this.image = url
            console.log(url)
            console.log(this.image)
            }
    },

    mounted() {
        
    },
}
</script>

<style>
body {
    margin: 0;
}

textarea {
    resize: none;
}

.profile-logo > div > h2{
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
    border: 1px solid #DBDBDB;
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
    border: 1px solid #DBDBDB;
    border-radius: 25px;
    text-align: center;
}

.mtt-ts-box {
    width: 100%;
    height: 400px;
    border: 1px solid #DBDBDB;
    border-radius: 10px;
    padding: 1rem;
    text-align: left
}

.mtt-cf-btn {
    padding: 0.5rem;
    background: white;
    border: 1px solid #DBDBDB;
    border-radius: 10px ;
    font-size: 1rem;
}

.mtt-trans-btn{
    width: 100%;
    margin-left: 1rem;
    padding: 0.5rem;
    background: #0d66ff;
    border: 1px solid #DBDBDB;
    border-radius: 10px ;
    font-size: 1rem;
}

.mtt-trans-btn > button{
    width: 100%;
    color: white;
    background: #0d66ff;
    border: none;
    border-radius: 10px ;
    font-size: 1rem;
}

.ts-output-cont {
    width: 35%;
    margin: 1rem;
    padding: 2rem 3rem 2rem 3rem;
    border: 1px solid #DBDBDB;
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