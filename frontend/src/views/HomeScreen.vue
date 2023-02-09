<template>
  <ion-page>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid>
        <ion-row>
          <ion-col></ion-col>
        </ion-row>
      </ion-grid>
      <ion-grid>
        <ion-row>
          <ion-col size-xl="3">
            <br />
            <br />
            <br />
            <br />
            <br />
          Willkommen bei 
          <br />
        </ion-col>
        </ion-row>
      </ion-grid>
      <ion-grid>
        <ion-row>
          <br />
          <ion-col>
            <ion-img
              src="assets/Pictures/FoodBase.jpg"
              alt="FoodBase"
            ></ion-img>
          </ion-col>
          <br />
          <br />
        </ion-row>
      </ion-grid>
      <ion-grid>

        <br />
        <br />
        <ion-row>
          <ion-col>
            <ion-searchbar :search-icon="searchCircle" placeholder="Rezept suchen"></ion-searchbar>
          </ion-col>
        </ion-row>
        <br />
        <br />
      </ion-grid>
    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">
import { defineComponent } from "vue";
import { searchCircle } from 'ionicons/icons';

import {
  IonButton,
  IonBackButton,
  IonList,
  IonItem,
  IonCheckbox,
  IonLabel,
  IonInput,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonTabButton,
} from "@ionic/vue";

</script>
  
  <style scoped>
    
ion-col {
  text-align: center;

}
</style>