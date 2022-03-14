import { createWebHistory, createRouter } from "vue-router";

import Papago from '@/pages/Papago.vue'
import OCR from '@/pages/OCR.vue'
import MTT from '@/pages/MTT.vue'
import STT from '@/pages/STT.vue'
import Storage from '@/pages/Storage.vue'

const routes = [
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