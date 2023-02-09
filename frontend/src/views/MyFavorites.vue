<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title class="boldText"
          ><ion-icon :icon="heart" color="danger"></ion-icon> Meine Favoriten
          <ion-icon :icon="heart" color="danger"></ion-icon
        ></ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="bgcolor">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid>
        <ion-row>
          <ion-col size="9">
            <ion-searchbar class="scolor"
              :search-icon="searchCircle"
              placeholder="Suchen"
            ></ion-searchbar>
          </ion-col>
          <ion-col class="ion-padding-top">
            <div>
              <ion-button size="small" color="danger" v-bind:router-link="filterPath">
                <ion-icon  slot="icon-only" :icon="filter" />
              </ion-button>
              </div>
          </ion-col>
        </ion-row>
      </ion-grid>
      <!-- ion-grid v-for""" -->
      <ion-grid v-bind:router-link="viewRecipe">
        <ion-row>
          <ion-col>
            <ion-img src="assets/Pictures/Repezt1.jpg" alt="FoodBase"></ion-img>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col class="boldText"> Älplermagarone </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            <ion-icon :icon="time" />
            30 Min
          </ion-col>
          <ion-col>
            <ion-icon :icon="barbell" />
            einfach
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">

import { heart, time, barbell, filter } from "ionicons/icons";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";

import {
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
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
  IonTabButton,
} from "@ionic/vue";

const filterPath ="/tabs/filterrecipe"

const viewRecipe ="/tabs/recipeDetail"
</script>

  
  <style scoped>
  .bgcolor{
    --ion-background-color: #FFE3EB
}
  .boldText{
    font-weight: 700;
  }
  .scolor{
    --ion-background-color: #FFFFFF
  }
</style>