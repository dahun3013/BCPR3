<template>
  <div>
    <router-view></router-view>
    <Modal @openModal="modal = true" :modal="modal" />
    <Modal @closeModal="modal = false" :modal="modal"/>
  </div>
</template>

<script>

import Modal from '@/components/Modal.vue'

export default {
  name: 'App',
  data() {
    return {
      modal : true,
    }
  },
  components: {
    Modal,
  }

}
</script>

<style>

</style>