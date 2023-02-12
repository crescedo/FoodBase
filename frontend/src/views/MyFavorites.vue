<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title class="boldText"><ion-icon :icon="heart" color="danger"></ion-icon> Meine Favoriten
          <ion-icon :icon="heart" color="danger"></ion-icon></ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="bgcolor">
      <ion-header collapse="condense">
      </ion-header>
      <ion-grid>
        <ion-row>
          <ion-col size="9">
            <ion-searchbar class="scolor" :search-icon="searchCircle" placeholder="Suchen"></ion-searchbar>
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
      <ion-card v-for="recipe in myFavorites" :key="recipe.id" v-bind:router-link="'/tabs/recipes/' + recipe.id">


        <ion-img :src="recipe.thumbnailUrl?.url"> </ion-img>
        <ion-card-header>
          <ion-card-title>
            {{
  recipe.title
            }}
          </ion-card-title>
          <ion-card-subtitle>
            Kategorie: <i>{{ recipe.category?.name }}</i>
            <br>
            Erstellt von: <i>{{ recipe.creator?.loginInfo?.loginName }}</i>
            <br>
            Vorbereitungszeit: <i>{{ Number(recipe.isoString?.match(/(\d+)H/)?.[1] || 0) }} h {{
              Number(recipe.isoString?.match(/(\d+)M/)?.[1] || 0)
            }} min</i>
            <br>
            Schwierigkeit: <ion-icon v-for="n in recipe.difficulty" :key="n" :icon="star" />
          </ion-card-subtitle>
          
        </ion-card-header>
        <ion-card-content> </ion-card-content>
      </ion-card>


    </ion-content>
  </ion-page>
</template>
  
<script setup lang="ts">

import { heart, time, barbell, filter, star } from "ionicons/icons";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";
import { useUser } from "@/composables/useUser";
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
  IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle,
} from "@ionic/vue";
import { useRecipes } from "../composables/useRecipes";


const { myFavorites, onMounted } = useUser();
//const { recipes, getRecipes } = useRecipes();
const filterPath = "/tabs/filterrecipe";

const viewRecipe = "/tabs/recipeDetail";
</script>

  
<style scoped>
.bgcolor {
  --ion-background-color: #FFE3EB
}

.boldText {
  font-weight: 700;
}

.scolor {
  --ion-background-color: #FFFFFF
}
</style>