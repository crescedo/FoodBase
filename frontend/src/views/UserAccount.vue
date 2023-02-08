<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Mein Account</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Blank</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-list>
        <ion-item>
          <ion-label>Benutzerdaten</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Benutzername: VarUsername</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>E-Mailadresse: VarMail</ion-label>
        </ion-item>
      </ion-list>

      <ion-button size="small" >Benutzerdaten ändern</ion-button>
      <ion-button size="small">Passwort zurücksetzen</ion-button>
      <ion-button size="small">Konto löschen</ion-button>
    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">
import {
  IonButton,
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