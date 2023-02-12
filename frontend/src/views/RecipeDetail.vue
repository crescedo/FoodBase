<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title> Rezept: {{ recipe.title }} </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense"> </ion-header>

      <ion-card>
        <img
          :alt="recipe.title + ' thumbnail'"
          :src="recipe.thumbnailUrl?.url"
        />
        <ion-card-header>
          <ion-card-title class="ion-text-left">
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
              {{ Number(recipe.isoString?.match(/(\d+)M/)?.[1] || 0) }} min</i
            >
            <br />
            Schwierigkeit:
            <ion-icon v-for="n in recipe.difficulty" :key="n" :icon="star" />
          </ion-card-subtitle>

          <ion-item>
            <ion-grid>
              <ion-row>
                <br />
                {{ recipe.descriptionShort }}
              </ion-row>
              <ion-row>
                <ion-col>
                  <ion-button
                    expand="block"
                    color="danger"
                    @click="addToFavorites(recipe.id!)"
                  >
                    <ion-icon slot="icon-only" :icon="heart" />
                  </ion-button>
                </ion-col>
                <ion-col size="6" class="ion-text-center">
                  <ion-button expand="block" color="primary">
                    <ion-icon slot="icon-only" :icon="add" />
                  </ion-button>
                </ion-col>
              </ion-row>
            </ion-grid>
          </ion-item>
        </ion-card-header>

        <ion-card-content>
          <ion-list>
            <ion-item>Zutaten:</ion-item>
            <ion-card-header
              v-for="ingredient in recipe.ingredients"
              :key="ingredient.id"
              class="ion-no-padding"
            >
              {{ ingredient.quantity }} - {{ ingredient.ingredient?.name }}
            </ion-card-header>
          </ion-list>
          <ion-list>
            <ion-item> Zubereitung: </ion-item>
            <ion-card v-for="step in recipe.cookingSteps" :key="step.id">
              <ion-card-header>
                <ion-card-subtitle>
                  {{ step.stepOrder }} - {{ step.title }}
                </ion-card-subtitle>
                <ion-text>
                  {{ step.content }}
                </ion-text>
              </ion-card-header>

              <ion-card-content>
                <ion-list>
                  <ion-item v-for="image in step.images" :key="image.id">
                    <ion-img :src="image.url"></ion-img>
                    <br /> </ion-item
                ></ion-list>
              </ion-card-content>
            </ion-card>
          </ion-list>
        </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  time,
  barbell,
  filter,
  person,
  card,
  text,
  star,
  heart,
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
  IonItemDivider,
  IonText,
  IonTitle,
  IonToolbar,
  IonBackButton,
  IonPage,
  IonButtons,
  IonHeader,
  IonLabel,
  IonItem,
  IonList,
  IonListHeader,
  IonContent,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
} from "@ionic/vue";

import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";
import { useRoute } from "vue-router";
import { useRecipeDetail } from "../composables/useRecipeDetail";
import { useUser } from "../composables/useUser";
import RecipeView from "../components/RecipeView.vue";

const { addToFavorites } = useUser();

const { recipe, recipe_id, onMount } = useRecipeDetail();
//console.log('hello')
const route = useRoute();
recipe_id.value = parseInt(route.params.id.toString());

const cookingBook = "/tabs/cookingbook";
</script>
<style scoped>
.scolor {
  --ion-background-color: #ffffff;
}
</style>
