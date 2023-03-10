import {User} from "@/model/user" 
import {Step} from "@/model/step" 
import {Image} from "@/model/image" 
import {Category} from "@/model/category" 
import {IngredientQuantity} from "@/model/ingredientQuantity" 


export interface Recipe {
    id?: number;
    title?: string;
    descriptionShort?:string;
    createdAt?: Date;
    creator?: User;
    cookingSteps?: Array<Step>;
    ingredients?: Array<IngredientQuantity>;
    keyWords?: Array<string>;
    thumbnailUrl?: Image; // Link zu einem Bild der als thumbnail verwendet wird
    category?: Category;
    difficulty?: number; // difficulty as 3/5 chef hats
    cookingTime?: Date; //Kochzeit des Rezepts
    isoString?:string;
    servings?: number; //fü
}