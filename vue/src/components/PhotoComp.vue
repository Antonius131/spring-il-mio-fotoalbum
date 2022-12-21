<template>
   <div>
      <h1>Hello Photos</h1>
      <div v-for="photo in photos" :key="photo.id">
         <h4>{{ photo.title }}</h4>
         <p>{{ photo.description }}</p>
         <img :src="photo.url" :alt="photo.title">
         
         <!-- comments and categories section-->
         <div @click="
            getCategories(photo.id),
            getComments(photo.id)"
         >
            <p>Vai ai commenti</p>
            <div v-for="category in categories" :key="category.id">
               <span>{{ category.name }}</span>
            </div>
            <div v-for="comment in comments" :key="comment.id">
               <p>{{ comment.text }}</p>
            </div>
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
            categories: [],
            comments: []
         }
      },

      methods: {
         getPhotos() {
            axios.get(this.apiUrl + "/photos/all")
            .then(result => {

               this.photos = result.data;
            });
         },
         
         getCategories(photoId) {
            axios.get(this.apiUrl + "/ph/category/" + photoId)
            .then(result => {

               this.categories = result.data;
            });
         },

         getComments(photoId) {
            axios.get(this.apiUrl + "/ph/comments/" + photoId)
            .then(result => {

               this.comments = result.data;
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