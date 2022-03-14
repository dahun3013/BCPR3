<template>
  <div class="storage-ts-container mb-5">
    <div class="storage-ts-output-cont">
      <div style="float: left; width: 20%">
        <h2>문서변환</h2>
      </div>
      <div style="float: left; width: 75%">
        <h3>{{ trans_date }}</h3>
      </div>
      <div style="float: right; width: 5%">
        <button @click="remove()">삭제</button>
      </div>
      <div class="storage-img-box">
        <img :src="input" alt="" class="img-fit" v-show="showInput" />
        <output name="result" v-show="showOutput">
          <div v-html="content"></div>
        </output>
      </div>
      <div style="display: flex">
        <button @click="changeShow()" class="storage-ff1-btn mt-4">
          텍스트 보기
        </button>
        <button @click="download()" class="storage-ff2-btn mt-4">
          다운로드
        </button>
      </div>
    </div>
    <!--storage-ts-output-cont-end-->
  </div>
  <!--storage-ts-container-end-->
</template>

<script>
import axios from "axios";
export default {
  name: "StorageCard",
  props: {
    media_no: Number,
    email: String,
    kind: String,
    input: String,
    output: String,
    trans_date: String,
  },
  data() {
    return {
      showInput: true,
      showOutput: false,
    };
  },
  methods: {
    changeShow() {
      if (this.showInput) this.showInput = false;
      else this.showInput = true;
      if (this.showOutput) this.showOutput = false;
      else this.showOutput = true;
    },
    async remove() {
      let str = "/api/ocr/remove";
      let form = new FormData();
      form.append("email", this.email);
      form.append("media_no", this.media_no);

      await axios
        .post(str, form)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      this.$router.go("/storage");
    },
    download() {
      let str = "/api/ocr/download/" + this.email + "/" + this.media_no + "/";
      if (this.showInput) str += "input";
      else str += "output";

      axios
        .get(str, {
          responseType: "blob",
        })
        .then((res) => {
          const name = res.headers["content-disposition"]
            .split("fileName=")[1]
            .replace(/"/g, "");
          const url = window.URL.createObjectURL(new Blob([res.data]));
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", name); //or any other extension document.body.appendChild(link); link.click();
          document.body.appendChild(link);
          link.click();
          link.remove();
          console.log(res);
          console.log("다운로드 성공");
        })
        .catch((err) => {
          err;
          console.log("다운로드 실패");
        });
    },
  },
  computed: {
    content() {
      return this.output.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style>
.storage-ts-container {
  width: 100%;
  display: flex;
  justify-content: center;
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