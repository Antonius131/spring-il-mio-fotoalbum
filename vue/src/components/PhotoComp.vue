<template>
   <div>
      <h1>Hello Photos</h1>
      <div v-for="photo in photos" :key="photo.id">
         <h4>{{ photo.title }}</h4>
         <p>{{ photo.description }}</p>
         <img :src="photo.url" :alt="photo.title">
      </div>
   </div>
</template>

<script>
   import axios from 'axios';

   export default {
      name: 'HelloWorld',

      data() {
         return {

            apiUrl: 'http://localhost:8080/api/1/photos',
            photos: []
         }
      },

      mounted() {
         axios.get(this.apiUrl + "/all")
            .then(result => {

               console.log(result.data);
               this.photos = result.data;
            });
      }
   }
</script>

<style scoped lang="scss">

</style>