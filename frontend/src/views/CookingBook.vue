<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title
          ><ion-icon :icon="book"></ion-icon> Kochbuch
          <ion-icon :icon="book"></ion-icon
        ></ion-title>
   <!--       <div>
              <ion-button size="small" color="green" v-bind:router-link="viewRecipe">
                <ion-icon  slot="icon-only" :icon="addCircleOutline" />
              </ion-button>
              </div>-->
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
          <ion-col size="9">
            <ion-searchbar
              :search-icon="searchCircle"
              placeholder="Rezept suchen"
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
          <ion-col> Älplermagarone </ion-col>
                  </ion-row>
        <ion-row>
          <ion-col>
            <ion-icon :icon="time" />
            <!-- cookingtime""" -->
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
import { book, time, barbell, filter, addCircleOutline } from "ionicons/icons";
import {
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonButton
} from "@ionic/vue";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";

const filterPath ="/tabs/filterrecipe"

const viewRecipe ="/tabs/recipeDetail"
</script>

  
  <style scoped>
</style>