import { Cooking } from "./cooking";
import { LoginInfo } from "./loginInfo";
import { Recipe } from "./recipe";
import { UserInfo } from "./userInfo";



export interface User {
    id?: number;
    userInfo?: UserInfo;// basisinformationen aller user
    loginInfo?: LoginInfo; // login Informationen
    favorites?: Array<Recipe>; // eine Liste, wo alle favoriten gespeichert werden
    cookings?: Array<Cooking>; // alle Rezepte die gekocht werden 
}