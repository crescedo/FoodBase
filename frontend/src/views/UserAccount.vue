<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title class="boldText">
          <ion-icon :icon="person" color="medium"></ion-icon> Mein Account
          <ion-icon :icon="person" color="medium"></ion-icon>
        </ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true" class="bgcolor">

      <ion-card>
        <!--falls möglich das ProfilPhoto auf dem User speichern -->
        <ion-img v-if="profilPhoto" :src="profilPhoto.webviewPath" alt="UserPhoto"></ion-img>
        <ion-img v-else :src="me.userInfo?.profilePic?.url" alt="UserPhoto"></ion-img>
        <ion-card-header>
          <ion-card-title>Mein Profil</ion-card-title>
          <ion-grid>
            <ion-row>
              <ion-col size="6">
                <ion-button color="primary" class="ion-padding-end" @click="takePhoto()">
                  Foto aufnehmen
                </ion-button>
              </ion-col>
              <ion-col size="6">
                <ion-button color="primary" class="ion-padding-end">
                  Foto auswählen
                </ion-button>
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-card-header>
        <ion-card-content>
          <ion-list>
            <ion-list-header>
              Meine Infos
            </ion-list-header>
            <ion-item>
              <ion-label class="boldText">Vorname</ion-label>
              <ion-text class="ion-text-left">{{ me.userInfo?.firstName }}</ion-text>
            </ion-item>
            <ion-item>
              <ion-label class="boldText">Nachname</ion-label>
              <ion-text class="ion-text-left">{{ me.userInfo?.lastName }}</ion-text>
            </ion-item>
            <ion-item>
              <ion-label class="boldText">Username</ion-label>
              <ion-text class="ion-text-left">{{ me.loginInfo?.loginName }}</ion-text>
            </ion-item>
            <ion-item>
              <ion-label class="boldText">Mein Hobby</ion-label>
              <ion-text class="ion-text-left">Kochen</ion-text>
            </ion-item>

          </ion-list>

        </ion-card-content>

      </ion-card>
      <ion-card>
        <ion-card-header>
          <ion-card-title>

            Danger zone
          </ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <ion-button expand="block" class="ion-padding-start" color="secondary">
            <ion-icon slot="end" :icon="pencil"></ion-icon>
            Benutzerdaten ändern
          </ion-button>
          <ion-button expand="block" class="ion-padding-start" color="warning">
            <ion-icon slot="end" :icon="mail"></ion-icon>
            Passwort zurücksetzen
          </ion-button>
          <ion-button expand="block" class="ion-padding-start" color="danger">
            <ion-icon slot="end" :icon="logoGithub"></ion-icon>
            Konto löschen
          </ion-button>
        </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>
  
<script setup lang="ts">
import { logoGithub, mail, pencil, person } from "ionicons/icons";
import {
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
  IonButton,
  IonList,
  IonListHeader,
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
  IonText,
  IonBackButton,
  IonCheckbox,
  IonTabButton,
} from "@ionic/vue";
import { usePhotoGallery, UserPhoto } from '../composables/usePhotoGallery';
import { useUser } from "@/composables/useUser";
const { profilPhoto, takePhoto } = usePhotoGallery();
const { me, getCurrentUserInfos } = useUser();
getCurrentUserInfos();
</script>

<style scoped>
.bgcolor {
  --ion-background-color: #eeeeee;
}

.boldText {
  font-weight: 700;
}

.profile-photo {

  border-radius: 100% !important;
  overflow: hidden;
}

ion-button {
  text-transform: none;
}
</style>