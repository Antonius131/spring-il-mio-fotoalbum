<template>
   <div>
      <h1>Hello Photos</h1>
      <div v-for="photo in photos" :key="photo.id">
         <h4>{{ photo.title }}</h4>
         <p>{{ photo.description }}</p>
         <img :src="photo.url" :alt="photo.title">
         
         <!-- comments and categories section-->
         <div @click="getCategories(photo.id)">
            <p>Vai ai commenti</p>
            <span v-for="category in categories" :key="category.id">
               {{ category.name }}
            </span>
         </div>
      </div>
   </div>
</template>

<script>
   import axios from 'axios';

   export default {
      name: 'HelloWorld',

      data() {
         return {

            apiUrl: 'http://localhost:8080/api/1',
            photos: [],
            categories: []
         }
      },

      methods: {
         getPhotos() {
            axios.get(this.apiUrl + "/photos/all")
            .then(result => {

               console.log(result.data);
               this.photos = result.data;
            });
         },
         
         getCategories(photoId) {
            axios.get(this.apiUrl + "/ph/category/" + photoId)
            .then(result => {

               console.log(result.data);
               this.categories = result.data;
            });
         },

         getPhotoId(id) {

            console.log(id);
         }
      },

      mounted() {
         this.getPhotos()
      }
   }
</script>

<style scoped lang="scss">

</style>