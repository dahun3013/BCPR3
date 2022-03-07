<template>
    <div class="field">
        <div class="top-container px-5 py-5">
            <div class="profile-logo">
                <div style="text-align: left;">
                    <h2>보관함</h2>
                </div>
                <div style="text-align: center;">
                    <h2 @click="$router.push('/Home')">PAGO BOOKS</h2>
                </div>
                <div @click="loginModal = true" style="text-align: right;">
                    <img src="@/assets/weblogin1.png" alt="profile-logo">
                </div>
            </div><!--profile-logo-end-->
        </div><!--top-container-end-->

        <div class="storage-bottom-container px-5 pt-5 pb-2">
            <div class="storage-ts-container">
                <div class="storage-ts-output-cont">
                    <div class="storage-img-box">
                        <img :src="image" alt="" class="img-fit">
                    </div>
                    <div style="display: flex;">
                        <button class="storage-ff1-btn mt-4">JPG / JPEG</button>
                        <button class="storage-ff2-btn mt-4">PNG</button>
                    </div>
                </div><!--ts-output-cont-end-->
            </div><!--storage-ts-container-end-->

            <div class="storage-ts-container">
                <div class="storage-ts-output-cont">
                    <div class="storage-img-box">
                        <img :src="image" alt="" class="img-fit">
                    </div>
                    <div style="display: flex;">
                        <button class="storage-ff1-btn mt-4">JPG / JPEG</button>
                        <button class="storage-ff2-btn mt-4">PNG</button>
                    </div>
                </div><!--ts-output-cont-end-->
            </div><!--storage-ts-container-end-->
        </div><!--storage-bottom-container-end-->
        
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

.storage-bottom-container {
    display: flex;
    background: white;
    border-radius: 100px 0px 0px 0px;
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
    border: 1px solid #DBDBDB;
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
    border: 1px solid #DBDBDB;
    border-radius: 10px;
    vertical-align: middle;
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

.storage-ts-box{
    width: 100%;
    height: 400px;
    border: 1px solid #DBDBDB;
    border-radius: 10px;
    vertical-align: middle;
    overflow: auto;
}

.storage-ts-box::-webkit-scrollbar {
    display: none;
}

.storage-cf-btn {
    padding: 0.5rem;
    background: white;
    border: 1px solid #DBDBDB;
    border-radius: 10px ;
    font-size: 1rem;
}

.storage-ff1-btn{
    width: 100%;
    margin-right: 1rem;
    padding: 0.5rem;
    color: white;
    background: #0d66ff;
    border: 1px solid #DBDBDB;
    border-radius: 10px ;
    font-size: 1rem;
}

.storage-ff2-btn{
    width: 100%;
    margin-left: 1rem;
    padding: 0.5rem;
    color: white;
    background: #0d66ff;
    border: 1px solid #DBDBDB;
    border-radius: 10px ;
    font-size: 1rem;
}

.storage-ts-output-cont {
    width: 100%;
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