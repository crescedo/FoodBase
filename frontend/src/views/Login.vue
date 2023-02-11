<template>
  <ion-page>
    <ion-content :fullscreen="true" padding>
      <ion-grid>
        <ion-row justify-content-center>
          <ion-col align-self-center size-md="6" size-lg="5" size-xs="12">
            <ion-text color="primary">
              <h2>Willkommen bei</h2>
            </ion-text>
            <ion-img
              src="assets/Pictures/FoodBase.jpg"
              alt="FoodBase"
            ></ion-img>
            <ion-text color="tertiary">
              <h3>Trete in die Welt des Kochen und Backens ein</h3>
            </ion-text>
            <div padding>
              <ion-item>
                <ion-input
                  type="text"
                  placeholder="Login Name"
                  v-model="username"
                ></ion-input>
              </ion-item>
              <ion-item>
                <ion-input
                  type="password"
                  placeholder="Password"
                  v-model="password"
                ></ion-input>
              </ion-item>
            </div>
            <div padding>
              <ion-button size="large" @click="login" expand="block"
              v-bind:router-link="homScreenPath">Login</ion-button
              >
            </div>
            <ion-text> <p>Passwort vergessen?</p></ion-text>
            <ion-text>
              Kein Account?
              <ion-button shape="round" v-bind:router-link="registerPath">Registrieren</ion-button>
            </ion-text>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent,
  IonInput,
  IonRow,
  IonCol,
  IonGrid,
  IonButton,
  IonItem,
  IonImg,
  IonText,
} from "@ionic/vue";
import { useLogin } from "../composables/useLogin";

const { username, password, login } = useLogin();

const homScreenPath ="/tabs/HomeScreen"
const registerPath = "/register";
</script>

<style scoped>

#container {
  text-align: center;

}

</style>

