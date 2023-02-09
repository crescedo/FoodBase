<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title> RezeptName</ion-title>
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
          <ion-col>
            <ion-img src="assets/Pictures/Repezt1.jpg" alt="FoodBase"></ion-img>
          </ion-col>
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
        <ion-row>
          <ion-col> Kategorie> </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            Das benötigst du:
            <ion-list>
              <ion-item>
                <ion-label>Salz</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Pfeffer</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Butter</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Mehl</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Hefe</ion-label>
              </ion-item>
            </ion-list>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            so gehst du vor:
            <ion-list>
              <ion-item>
                <ion-label>Step 1</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Step 2</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Step 3</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Step 4</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Step 5</ion-label>
              </ion-item>
            </ion-list>
          </ion-col>
        </ion-row>
      </ion-grid>
     </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { time, barbell, filter } from "ionicons/icons";
import {
    IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonButton, IonTitle, IonToolbar, IonBackButton, IonPage, IonButtons, IonHeader, IonLabel, IonItem, IonList, IonContent
} from "@ionic/vue";
import { defineComponent } from "vue";
import { searchCircle } from "ionicons/icons";



const cookingBook = "/tabs/cookingbook";
</script>
<style scoped>
</style>
