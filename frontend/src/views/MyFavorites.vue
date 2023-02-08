<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title
          ><ion-icon :icon="heart" color="danger"></ion-icon> Meine Favoriten
          <ion-icon :icon="heart" color="danger"></ion-icon
        ></ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid>
    <ion-row>
      <ion-col>
        <ion-img>
          src="assets/Pictures/Rezept1.jpg"
          alt="FoodBase"
        </ion-img>
    </ion-col>
    </ion-row>
  </ion-grid>
  <ion-grid>
    <ion-row>
      <ion-col>
        Älplermagarone
      </ion-col>
    </ion-row>
  </ion-grid>
  <ion-grid>
    <ion-row>
      <ion-col>
        <ion-icon :icon="time" />
        30 Min
      </ion-col>
      <ion-col>

        <ion-icon :icon="barbell" />
        
        
        
        einfach </ion-col>
    </ion-row>
  </ion-grid>





      




    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">
import { IonIcon } from "@ionic/vue";
import { heart, time, barbell } from "ionicons/icons";
</script>

  
  <style scoped>
</style>