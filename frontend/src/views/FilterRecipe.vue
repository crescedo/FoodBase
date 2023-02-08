<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Filter</ion-title>
        <ion-buttons slot="start">
        <ion-back-button></ion-back-button>
      </ion-buttons>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>
</ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonBackButton,
  IonList,
  IonItem,
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
</style>

