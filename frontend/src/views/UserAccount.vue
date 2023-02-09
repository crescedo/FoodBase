<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>
          <ion-icon :icon="person" color="medium"></ion-icon> Mein Account
          <ion-icon :icon="person" color="medium"></ion-icon>
        </ion-title>
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
          <ion-label>Profilbild</ion-label>
        </ion-item>
      </ion-list>

      <ion-list>
        <ion-grid>
          <ion-row>
            <ion-col>
              <ion-img
                style="width: 80px; height: 80 px"
                src="assets/Pictures/UserPhoto.jpeg"
                alt="UserPhoto"
              >
              </ion-img>
            </ion-col>
            <ion-col>
              <ion-button size="small" color="medium"
                >Foto aufnehmen</ion-button
              >
              <ion-button size="small" color="medium"
                >Foto auswählen</ion-button
              >
            </ion-col>
          </ion-row>
        </ion-grid>
      </ion-list>

      <ion-list>
        <ion-item>
          <ion-label>Benutzerdaten</ion-label>
        </ion-item>
      </ion-list>
      <ion-grid>
        <ion-row>
          <ion-col>Vorname:</ion-col>
          <ion-col>Var Vorname</ion-col>
        </ion-row>
        <ion-row>
          <ion-col>Nachname:</ion-col>
          <ion-col>Var Nachname</ion-col>
        </ion-row>
        <ion-row>
          <ion-col>Benutzername:</ion-col>
          <ion-col>Var username</ion-col>
        </ion-row>
        <ion-row>
          <ion-col>E-Mailadresse:</ion-col>
          <ion-col>Var Mailadress</ion-col>
        </ion-row>
      </ion-grid>

      <ion-list>
        <ion-item>
          <ion-label>Funktionen</ion-label>
        </ion-item>
      </ion-list>
      <ion-button class="ion-padding-start" size="small" color="medium"
        >Benutzerdaten ändern</ion-button
      >
      <ion-button class="ion-padding-start" size="small" color="medium"
        >Passwort zurücksetzen</ion-button
      >
      <ion-button class="ion-padding-start" size="small" color="medium"
        >Konto löschen</ion-button
      >
    </ion-content>
  </ion-page>
</template>
  
  <script setup lang="ts">
  import { person } from "ionicons/icons";
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
  IonIcon,
  IonSearchbar,
  IonImg,
  IonCol,
  IonGrid,
  IonRow,
  IonBackButton,
  IonCheckbox,
  IonTabButton,
} from "@ionic/vue";
</script>

  <style scoped>
</style>