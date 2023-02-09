<template>
    <ion-page>
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-title><ion-icon :icon="heart" color="danger"></ion-icon> Meine Favoriten <ion-icon :icon="heart" color="danger"></ion-icon></ion-title>
        </ion-toolbar>
      </ion-header>
        <ion-content :fullscreen="true">
        <ion-header collapse="condense">
          <ion-toolbar>
            <ion-title size="large">Blank</ion-title>
          </ion-toolbar>
        </ion-header>
  


        </ion-content>
    </ion-page>
  </template>
  
  <script setup lang="ts">
  import {   IonIcon,  } from '@ionic/vue';
  import { heart} from 'ionicons/icons';



   </script>
  
  <style scoped>
 
  </style>