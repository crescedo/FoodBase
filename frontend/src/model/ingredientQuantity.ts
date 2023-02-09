import { Ingredient } from "./ingredient";
import { Measure } from "./measure";

export interface IngredientQuantity {
    id?: number;
    quantity?: number;
    ingredient?: Ingredient;
    measure?: Measure;
}