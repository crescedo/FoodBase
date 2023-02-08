<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button></ion-back-button>
        </ion-buttons>
        <ion-title>Filter</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-item>
        <ion-label>Menüart</ion-label>
      </ion-item>
      <ion-item>
        <ion-checkbox slot="start"></ion-checkbox>
        <ion-label>Frühstück</ion-label>
      </ion-item>
      <ion-item>
        <ion-checkbox slot="start"></ion-checkbox>
        <ion-label>Snacks</ion-label>
      </ion-item>
      <ion-item>
        <ion-checkbox slot="start"></ion-checkbox>
        <ion-label>Vorspeise</ion-label>
      </ion-item>
      <ion-item>
        <ion-checkbox slot="start"></ion-checkbox>
        <ion-label>Hauptgericht</ion-label>
      </ion-item>
<ion-item>
        <ion-checkbox slot="start"></ion-checkbox>
        <ion-label>Dessert</ion-label>
      </ion-item>

    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
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
} from "@ionic/vue";
</script>

  <style scoped>
  ion-checkbox {
    --size: 25px;
    --background-checked: #eb445a;
  }
  
  ion-checkbox::part(container) {
    border-radius: 6px;
    border: 2px solid #eb445a;
  }
</style>

