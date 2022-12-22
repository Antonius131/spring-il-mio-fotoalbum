<template>
   <div>
      <div class="container p-5">
         <div class="row">
            <div class="col-md-8 offset-md-2 d-flex justify-content-between align-items-center">
               <h1>Hello Photos</h1>
               <div class="input-group" style="width: 30%">
                  <input type="text" placeholder="Cerca" class="form-control" v-model="searchValue">
                  <button type="submit" class="btn btn-primary" @click="getSearchedPhotos()">Cerca</button>
               </div>
            </div>
         </div>
         <div class="row p-2">
            <div 
               class="col-4"
               v-for="photo in photos" :key="photo.id" :class="photo.visible ? '' : 'd-none'"
            >
               <div class="card">
                  <img class="img-fluid card-img-top" :src="photo.url" :alt="photo.title" />
                  <div class="card-body text-start">
                     <h5 class="card-title"><strong>{{ photo.title }}</strong></h5>
                     <h6 class="card-subtitle mb-1">{{ photo.description }}</h6>
                     <p class="text-muted mb-1">{{ photo.tag }}</p>
                     <span v-for="category in photo.categories" :key="category.id">
                        <i>{{ category.name }}, </i>
                     </span>
                  </div>
                  <div class="card-footer text-start">
                     <h6 class="card-title"><strong>Commenti</strong></h6>
                     <div v-for="comment in photo.comments" :key="comment.id">
                        <p>{{ comment.text }}</p>
                     </div>
                     <hr>
                     <div class="input-group">
                        <input 
                           type="text" 
                           v-model="usrComment" 
                           placeholder="Commenta"
                           class="form-control"
                        />
                        <button 
                           class="btn btn-primary" 
                           type="button" 
                           @click="sendComment(photo.id)">Commenta</button>
                     </div>
                  </div>
               </div>
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