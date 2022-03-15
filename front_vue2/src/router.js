import { createWebHistory, createRouter } from "vue-router";

import Home from '/src/pages/Home.vue'
import Papago from '/src/pages/Papago.vue'
import OCR from '/src/pages/OCR.vue'
import STT from '/src/pages/STT.vue'
import TTS from '/src/pages/TTS.vue'
import Storage from '/src/pages/Storage.vue'
import OCRStorage from '/src/pages/OCRStorage.vue'
import PapagoStorage from '/src/pages/PapagoStorage.vue'
import STTStorage from '/src/pages/STTStorage.vue'
import TTSStorage from '/src/pages/TTSStorage.vue'

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
    path: "/stt",
    component: STT,
  },
  {
    path: "/tts",
    component: TTS,
  },
  {
    path: "/storage",
    component: Storage,
  },
  {
    path: "/OCRStorage",
    component: OCRStorage,
  },
  {
    path: "/PapagoStorage",
    component: PapagoStorage,
  },
  {
    path: "/STTStorage",
    component: STTStorage,
  },
  {
    path: "/TTSStorage",
    component: TTSStorage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router; 