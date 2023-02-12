<template>
  <ion-page>

    <ion-content :fullscreen="true" class="bgcolor">
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
            <h2>
          Willkommen bei 
            </h2>
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
            <ion-searchbar :search-icon="searchCircle" placeholder="Rezept suchen" class="scolor"  v-model="title" @ion-change="getRecipesByTitle"></ion-searchbar>
          </ion-col>
        </ion-row>
        <br />
      </ion-grid>

    <ion-list>
        <ion-item class="scolor">
          <ion-label >Neues Rezept erfassen</ion-label>
        </ion-item>
      </ion-list>

  <ion-fab class="test">
  <ion-fab-button size="small" color="secondary">
    <ion-icon  :icon="add"></ion-icon>
  </ion-fab-button>
</ion-fab>

    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">
import { defineComponent } from "vue";
import { searchCircle } from 'ionicons/icons';
import { add } from 'ionicons/icons';
import { useRecipes } from "@/composables/useRecipes";

import {
  IonFabButton, 
  IonFab,
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

const {recipes,getRecipesByTitle,title} =useRecipes();



</script>
  
  <style scoped>
    
ion-col {
  text-align: center;

}
.bgcolor{
    --ion-background-color: #B2E9FF
}
.scolor{
    --ion-background-color: #FFFFFF
  }
  .test{
    position: fixed;
    bottom: 77px;
    right: 15px;
  }
</style>