<template>
   <div>
      <h1>Hello Photos</h1>

      <div>
         <input type="text" placeholder="Cerca" v-model="searchValue">
         <input type="submit" value="Cerca" @click="getSearchedPhotos()">
      </div>

      <div v-for="photo in photos" :key="photo.id" :class="photo.visible ? '' : 'd-none'">
         <h4>{{ photo.title }}</h4>
         <p>{{ photo.description }}</p>
         <p>{{ photo.tag }}</p>
         <img 
            :src="photo.url" :alt="photo.title" 
         />
         
         <!-- comments and categories section-->
         <div v-for="category in photo.categories" :key="category.id">
            <span>{{ category.name }}</span>
         </div>
         <div>
            <div v-for="comment in photo.comments" :key="comment.id">
               <p>{{ comment.text }}</p>
            </div>
            <div>
               <p>Commenta</p>
               <input type="text" v-model="usrComment" @keyup.enter="sendComment(photo.id)"/>
            </div>
         </div>
      </div>
   </div>
</template>

<script>
   import axios from 'axios';

   export default {
      name: 'PhotoComp',

      data() {
         return {

            apiUrl: 'http://localhost:8080/api/1',
            photos: [],

            searchValue: '',
            usrComment: '',
            sndComment: {
               photo: '',
               text: ''
            }
         }
      },

      methods: {

         getIndexFromPhoto(photoId) {

            return this.photos.findIndex(photo => photo.id === photoId);
         },

         getPhotos() {
            axios.get(this.apiUrl + "/photos/all")
            .then(result => {

               this.photos = result.data;
            });
         },
         
         getCategories(photoId) {
            axios.get(this.apiUrl + "/ph/category/" + photoId)
            .then(result => {

               const phCategories = result.data;
               const index = this.getIndexFromPhoto(photoId);

               this.photos[index].categories = phCategories;
            });
         },

         getComments(photoId) {
            axios.get(this.apiUrl + "/ph/comments/" + photoId)
            .then(result => {

               const phComments = result.data;
               const index = this.getIndexFromPhoto(photoId);

               this.photos[index].comments = phComments;
            });
         },

         sendComment(photoId) {
            
            console.log('id: ' +  photoId);
            this.sndComment.photo = photoId;
            this.sndComment.text = this.usrComment;

            axios.post(this.apiUrl + "/send/" + photoId, this.sndComment)
            .then(result => {

               this.getComments(photoId);
               this.usrComment= '';
               
               console.log(result);
            });
         },

         getSearchedPhotos() {

            if(this.searchValue === '') return this.getPhotos();

            axios.get(this.apiUrl + '/photos/search/' + this.searchValue)
            .then(result => {

               this.photos = '';
               this.photos = result.data;
            })
         }
      },

      mounted() {
         this.getPhotos()
      }
   }
</script>

<style scoped lang="scss">

</style>