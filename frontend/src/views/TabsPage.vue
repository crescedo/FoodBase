<template>
  <ion-page>
    <ion-content>
      <ion-tabs>
        <ion-router-outlet></ion-router-outlet>
        <ion-tab-bar slot="bottom">
          <ion-tab-button tab="tab1" href="/tabs/homescreen">
            <ion-icon :icon="home" />
            <ion-label>Home</ion-label>
          </ion-tab-button>
          <ion-tab-button tab="tab2" href="/tabs/myfavorites">
            <ion-icon :icon="heart" color="danger"/>
            <ion-label>Favoriten</ion-label>
          </ion-tab-button>
          <ion-tab-button tab="tab3" href="/tabs/cookingbook">
            <ion-icon :icon="book" color="warning"/>
            <ion-label>Kochbuch</ion-label>
          </ion-tab-button>
          <ion-tab-button tab="tab4" href="/tabs/useraccount">
            <ion-icon :icon="person" color="medium"/>
            <ion-label>Account</ion-label>
          </ion-tab-button>
        </ion-tab-bar>
      </ion-tabs>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
  import { home, list, heart, person, book } from 'ionicons/icons';
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
  IonRouterOutlet,IonTabBar, IonTabs, IonTabButton,
} from "@ionic/vue";
</script>