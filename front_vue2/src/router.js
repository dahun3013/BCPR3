import { createWebHistory, createRouter } from "vue-router";

import Home from '/src/pages/Home.vue'
import Papago from '/src/pages/Papago.vue'
import OCR from '/src/pages/OCR.vue'
import MTT from '/src/pages/MTT.vue'
import TTS from '/src/pages/TTS.vue'
import Storage from '/src/pages/Storage.vue'

const routes = [
  {
    path: "/",
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
    path: "/mtt",
    component: MTT,
  },
  {
    path: "/tts",
    component: TTS,
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