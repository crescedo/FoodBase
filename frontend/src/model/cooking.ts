import { Recipe } from "./recipe";

export interface Cooking {
    id?: number;
    recipe?: Recipe; //was soll gekocht werden?
    time?: Date; //Wann soll gekocht werden?
    guests?: number;
}