<template>
    <ion-page>
        <ion-content justify-content-center>
            <div>
                <ion-item>
                    <ion-label>Username</ion-label>
                    <ion-input v-model="username" placeholder="Username"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-label>Password</ion-label>
                    <ion-input v-model="password" type="password" placeholder="Password"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-button @click="addNewUser()">Click ME!</ion-button> 
                </ion-item>
            </div>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import { useUser } from "@/composables/useUser";
import { stringLiteral } from "@babel/types";
import {
    IonPage,
    IonContent,
    IonInput,
    IonLabel,
    IonRow,
    IonCol,
    IonGrid,
    IonButton,
    IonItem,
    IonImg,
    IonText,
} from "@ionic/vue";
import { Ref } from "vue";


const { username, password, addUser: addNewUser } = useUser();


</script>

<style scoped>
#container {
    text-align: center;

}
</style>

