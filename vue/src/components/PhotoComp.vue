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
            getComments(photo.id)
            "
         >
            <p>Vai ai commenti</p>
            <div v-for="category in categories" :key="category.id">
               <span>{{ category.name }}</span>
            </div>
            <div v-for="comment in comments" :key="comment.id">
               <p>{{ comment.text }}</p>
            </div>
            <div>
               <p>Commenta</p>
               <input type="text" v-model="usrComment" @enter="sendComment(photo.id)"/>
            </div>
         </div>
      </div>
   </div>
</template>

<script>
   import axios from 'axios';

   const PHOTO_ID = -1;

   export default {
      name: 'HelloWorld',

      data() {
         return {

            apiUrl: 'http://localhost:8080/api/1',
            photos: [],
            categories: [],
            comments: [],

            photoId: PHOTO_ID,

            usrComment: '',
            sndComment: {
               photo: '',
               text: ''
            }
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

         sendComment(photoId) {
            
            this.sndComment.photo = photoId;
            this.sndComment.text = this.usrComment;

            axios.post(this.apiUrl + "/send/" + photoId, this.sndComment)
            .then(result => {

               this.getComments(photoId);
               this.usrComment= '';
               
               console.log(result);
            });
         }
      },

      mounted() {
         this.getPhotos()
      }
   }
</script>

<style scoped lang="scss">

</style>