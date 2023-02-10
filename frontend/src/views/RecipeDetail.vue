<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title>
          Älplermagarone
          <!-- {{ recipe.title }} -->
        </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense"> </ion-header>
      <ion-grid>
        <ion-row>
          <ion-col>
            <ion-img src="assets/Pictures/Repezt1.jpg" alt="FoodBase"></ion-img>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            <ion-icon :icon="time" /> 30 Min
            <!-- {{       recipe.cookingTime    }} -->
          </ion-col>
          <ion-col>
            <ion-icon :icon="barbell" /> einfach
            <!-- {{ recipe.difficulty }} -->
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            Kategorie>
            <!-- {{ recipe.category }} -->
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            Anzahl Personen: <ion-icon :icon="person" color="medium"></ion-icon>
            <!-- {{ recipe.servings }} -->
            <ion-list>
              Das benötigst du:
              <ion-grid>
                <!--v-for="IngredientQuantity in recipe" :key="recipe.id" // Damit alle Zutaten ausgelesen werden-->
                <ion-row>
                  <ion-col>
                    <!-- {{ IngredientQuantity.quantity + IngredientQuantity.measure }} -->
                    1 dl
                  </ion-col>
                  <ion-col>
                    <!-- {{ IngredientQuantity.ingredient }} -->
                    Milch
                  </ion-col>
                </ion-row>
                <ion-row> <!-- Zeile 56 - 67  löschen, da mit v-for alle zustaten ausgelesen werden -->
                  <ion-col>1 EL</ion-col>
                  <ion-col>Zucker</ion-col>
                </ion-row>
                <ion-row>
                  <ion-col>200 gr.</ion-col>
                  <ion-col>Mehl</ion-col>
                </ion-row>
                <ion-row>
                  <ion-col>1 Pck.</ion-col>
                  <ion-col>Trockenhefe</ion-col>
                </ion-row>
              </ion-grid>
            </ion-list>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>

            Vorgehen:

            <ion-list lines="full">
              <!--v-for="cookingSteps in recipe" :key="recipe.id" // Damit alle Steps ausgelesen werden-->
              <ion-item>
                <ion-label>
                  <ion-col>
                    Step 1
                    <!-- {{ steps.stepOrder }} -->
                  </ion-col>
                  <ion-col>
                    Titel
                    <!-- {{ steps.title }} -->
                  </ion-col>
                </ion-label>
              </ion-item>
              <ion-item>
                <ion-label>
                  content
                  <!-- {{ steps.title }} -->
                </ion-label         >
              </ion-item>
              <ion-item>
                <ion-label>
                  images<!-- {{ steps.imapges }} -->
                </ion-label>
              </ion-item>
            </ion-list>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { time, barbell, filter, person } from "ionicons/icons";
import {
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonButton,
  IonTitle,
  IonToolbar,
  IonBackButton,
  IonPage,
  IonButtons,
  IonHeader,
  IonLabel,
  IonItem,
  IonList,
  IonContent,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";

const cookingBook = "/tabs/cookingbook";
</script>
<style scoped>
</style>
