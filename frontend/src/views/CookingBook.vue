<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title class="boldText">
          <ion-icon :icon="book" color="warning"></ion-icon> Mein Kochbuch
          <ion-icon :icon="book" color="warning"></ion-icon>
        </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="bgcolor">
      <ion-grid>
        <ion-row>
          <ion-col size="9">
            <ion-searchbar
              :search-icon="searchCircle"
              placeholder="Search"
              class="scolor"
              v-model="title"
              @ion-change="getRecipesByTitle"
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

      <ion-card
        v-for="recipe in recipes"
        :key="recipe.id"
        v-bind:router-link="'/tabs/recipes/' + recipe.id"
      >
        <ion-img :src="recipe.thumbnailUrl?.url"> </ion-img>
        <ion-card-header>
          <ion-card-title>
            {{ recipe.title }}
          </ion-card-title>
          <ion-card-subtitle>
            Kategorie: <i>{{ recipe.category?.name }}</i>
            <br />
            Erstellt von: <i>{{ recipe.creator?.loginInfo?.loginName }}</i>
            <br />
            Vorbereitungszeit:
            <i
              >{{ Number(recipe.isoString?.match(/(\d+)H/)?.[1] || 0) }} h
              {{ Number(recipe.isoString?.match(/(\d+)M/)?.[1] || 0) }} min
            </i>
            <br />
            Schwierigkeit:
            <ion-icon v-for="n in recipe.difficulty" :key="n" :icon="star" />
          </ion-card-subtitle>
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
  star,
  add,
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
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";
import { useRecipes } from "../composables/useRecipes";
import { useUser } from "../composables/useUser";

const { recipes, title, getRecipesByTitle } = useRecipes();
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