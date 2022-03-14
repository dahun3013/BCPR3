import { createWebHistory, createRouter } from "vue-router";

import Home from '/src/pages/Home.vue'
import Papago from '/src/pages/Papago.vue'
import OCR from '/src/pages/OCR.vue'
import TTS from '/src/pages/TTS.vue'
import STT from '/src/pages/STT.vue'
import Storage from '/src/pages/Storage.vue'

const routes = [
  {
    path: "/Home",
    component: Home,
  },
  {
    path: "/papago",
    component: Papago,
  },
  {
    path: "/ocr",
    component: OCR,
  },
  {
    path: "/tts",
    component: TTS,
  },
  {
    path: "/stt",
    component: STT,
  },
  {
    path: "/storage",
    component: Storage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router; 