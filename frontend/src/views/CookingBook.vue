<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title><ion-icon :icon="book"></ion-icon> Kochbuch
          <ion-icon :icon="book"></ion-icon></ion-title>
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
            <ion-searchbar :search-icon="searchCircle" placeholder="suchen"></ion-searchbar>
          </ion-col>
          <ion-col class="ion-padding-top">
            <div>
              <ion-button size="small" color="danger" v-bind:router-link="filterPath">
                <ion-icon slot="icon-only" :icon="filter" />
              </ion-button>
            </div>
          </ion-col>
        </ion-row>
      </ion-grid>


      <ion-list>
        <ion-item v-for="recipe in recipes" :key="recipe.id">
          <ion-card >
            <img alt="Silhouette of mountains" src=""/>
            <ion-card-header>
              <ion-card-title>Card Title</ion-card-title>
              <ion-card-subtitle>Card Subtitle</ion-card-subtitle>
            </ion-card-header>

            <ion-card-content>
              Here's a small text description for the card content. Nothing more, nothing less.
            </ion-card-content>
          </ion-card>
        </ion-item>
      </ion-list>
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
  IonCard,
  IonCardTitle,
  IonCardHeader,
  IonCardContent,
  IonCardSubtitle
} from "@ionic/vue";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";
import { useRecipes } from "../composables/useRecipes";

const { recipes, getRecipes } = useRecipes();
const filterPath = "/tabs/filterrecipe"

const viewRecipe = "/tabs/recipeDetail"
</script>

  
<style scoped>

</style>