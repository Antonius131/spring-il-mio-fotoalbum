<template>
   <div class="input-group">
      <input 
         type="text" 
         v-model="userComment" 
         placeholder="Commenta"
         class="form-control"
      />
      <button 
         class="btn btn-primary" 
         type="button" 
         @click="$emit('send', userComment, id), userComment = ''"
         >
            Commenta
      </button>
   </div>
</template>

<script>

export default {

   props: {
      id: Number
   },

   data() {
      return {
         
         userComment: ''
      }
   },
}
</script>

<style>
</style>