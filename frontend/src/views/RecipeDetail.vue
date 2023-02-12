<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title>
          Rezept: {{ recipe.title }}
        </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense"> </ion-header>

      <ion-card>
        <img :alt="recipe.title + ' thumbnail'" :src="recipe.thumbnailUrl?.url" />
        <ion-card-header>
          <ion-card-title class="ion-text-left">
            {{ recipe.title }}
          </ion-card-title>

          <ion-card-subtitle>
            Kategorie: <i>{{ recipe.category?.name }}</i>
            <br>
            Erstellt von: <i>{{ recipe.creator?.loginInfo?.loginName }}</i>
            <br>
            Erstellt am: <i>{{ recipe.createdAt!}}</i>
            <br>
            Vorbereitungszeit: <i>{{ Number(recipe.isoString?.match(/(\d+)H/)?.[1] || 0) }} h {{
              Number(recipe.isoString?.match(/(\d+)M/)?.[1] || 0)
            }} min</i>
            <br>
            Schwierigkeit: <ion-icon v-for="n in recipe.difficulty" :key="n" :icon="star" />
          </ion-card-subtitle>
          
          

            <ion-grid>
              <ion-row class="ion-text-left">
                <ion-text>{{ recipe.descriptionShort }}</ion-text>
              </ion-row>
              <ion-row>

                <ion-col  >

                  <ion-button shape="round" expand="block" color="primary" v-if="!isFavorite(recipe.id!)"
                    @click="addToFavorites(recipe.id!); setOpen(true)">
                    <ion-icon slot="end" :icon='heart' />
                    Zu Favoriten hinnzufügen
                  </ion-button>
        
                  <ion-alert :is-open="isOpenRef" :header="recipe.title" 
                    message= "wurde zu den Favoriten hinzugefügt" :buttons="['OK']"

                    @didDismiss="setOpen(false); reloadPage()">
                  </ion-alert>

                  <ion-button shape="round" expand="block" color="danger" v-if="isFavorite(recipe.id!)" @click="removeRecipeFromFavorites(recipe.id!);reloadPage()">
                    <ion-icon slot="end" :icon='heartOutline' />
                    Aus Favoriten entfernen
                  </ion-button>

                </ion-col>

              </ion-row>
            </ion-grid>
          

        </ion-card-header>

        <ion-card-content>
          <ion-list>
            <ion-card-title>
            Zutaten:
            </ion-card-title>
            <br>
            <ion-card-subtitle v-for="ingredient in recipe.ingredients" :key="ingredient.id">
              {{ ingredient.quantity }} {{ ingredient.measure?.nameShort }} - {{ ingredient.ingredient?.name }}
            </ion-card-subtitle>
          </ion-list>
          <ion-list>
            <ion-list-header>
              <ion-item>Zubereitung:</ion-item>
            </ion-list-header>
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
                    <br>
                  </ion-item></ion-list>
              </ion-card-content>


            </ion-card>


          </ion-list>

        </ion-card-content>
      </ion-card>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { time, barbell, filter, person, card, text, star, heart, add, heartCircleOutline, heartOutline } from "ionicons/icons";
import {
  IonAlert,
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonButton,
  IonPopover,
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
  IonCardSubtitle
} from "@ionic/vue";

import { defineComponent, ref } from "vue";
import { searchCircle } from "ionicons/icons";
import { useRoute } from "vue-router";
import { useRecipeDetail } from "@/composables/useRecipeDetail";
import { useUser } from "@/composables/useUser";
import RecipeView from "@/components/RecipeView.vue";
import router from "@/router";

const { addToFavorites } = useUser();

const { recipe, recipe_id } = useRecipeDetail();
const route = useRoute();
const { myFavorites, onMounted, removeRecipeFromFavorites } = useUser();

function isFavorite(id: number): boolean {
  for (const f of myFavorites.value) {
    if (f && f.id === id) {
      return true;
    }
  }
  return false;
}
const isOpenRef = ref(false);
const setOpen = (state: boolean) => (isOpenRef.value = state);
function reloadPage() {
  // router.push('/tabs/searchrecipe');
  location.reload();
}
function getDateString(createdAt:Date):string{
  console.log(createdAt)
 /*  const shortDate =createdAt.toLocaleDateString("en-US", {
    year: "numeric",
    month: "short",
    day: "numeric"
  }); */
  return "";
}

recipe_id.value = parseInt(route.params.id.toString());


const cookingBook = "/tabs/cookingbook";
</script>
<style scoped>
.scolor {
  --ion-background-color: #ffffff;
}
</style>
