<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title class="boldText">
          <ion-icon :icon="book" color="warning"></ion-icon> Kochbuch
          <ion-icon :icon="book" color="warning"></ion-icon>
        </ion-title>
        <!--       <div>
                <ion-button size="small" color="green" v-bind:router-link="viewRecipe">
                  <ion-icon  slot="icon-only" :icon="addCircleOutline" />
                </ion-button>
                </div>-->
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="bgcolor">
      <ion-grid>
        <ion-row>
          <ion-col size="9">
            <ion-searchbar
              class="scolor"
              :search-icon="searchCircle"
              placeholder="Suchen"
            ></ion-searchbar>
          </ion-col>
          <ion-col class="ion-padding-top">
            <div>
              <ion-button
                size="small"
                color="warning"
                v-bind:router-link="filterPath"
              >
                <ion-icon slot="icon-only" :icon="filter" />
              </ion-button>
            </div>
          </ion-col>
        </ion-row>
      </ion-grid>
      <ion-card v-for="recipe in recipes" :key="recipe.id">
        <ion-img> {{ recipe.thumbnailUrl?.url }} </ion-img>
        <ion-card-header>
          <ion-card-title>
            {{ recipe.title
            }}   <ion-button
              size="small"
              color="danger"
              v-bind:router-link="filterPath">
              <ion-icon slot="icon-only" :icon="heart" />
            </ion-button>
          </ion-card-title>
          <ion-card-subtitle>{{
              recipe.category
            }}</ion-card-subtitle>
                    <ion-card-subtitle v-bind:router-link="viewRecipe"
            ><ion-icon :icon="time" />{{
              recipe.cookingTime
            }}</ion-card-subtitle
          >
          <ion-card-subtitle v-bind:router-link="viewRecipe"
            ><ion-icon :icon="barbell" />
            {{ recipe.difficulty }}</ion-card-subtitle>
        </ion-card-header>

        <ion-card-content> </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

 
<script setup lang="ts">
import {
  book,
  time,
  barbell,
  filter,
  addCircleOutline,
  heart,
} from "ionicons/icons";
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
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";
import { useRecipes } from "../composables/useRecipes";
const { recipes, getRecipes } = useRecipes();
const filterPath = "/tabs/filterrecipe";

const viewRecipe = "/tabs/recipeDetail";
</script>

  
  <style scoped>

ion-card-header.ios {
    display: flex;
    flex-flow: column-reverse;
  }

.bgcolor {
  --ion-background-color: #fffed9;
}
.boldText {
  font-weight: 700;
}
.scolor {
  --ion-background-color: #ffffff;
}
</style>