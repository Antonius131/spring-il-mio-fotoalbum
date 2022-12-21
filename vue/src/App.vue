<template>
  <div id="app">
    <PhotoComp />
  </div>
</template>

<script>
import PhotoComp from './components/PhotoComp.vue';

export default {
  name: 'App',
  components: {
    PhotoComp
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
