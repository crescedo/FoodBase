import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Recipe } from '@/model/recipe';

export async function getAllRecipes(): Promise<Recipe[]> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/recipes', config);
        return response.data;
    } catch (error) {
        return <any>error;
    }
}

export async function getAllRecipesByTitle(recipeTitle: string): Promise<Recipe[]> {
    
    if(recipeTitle==="")return new Array<Recipe>;
    const config = {
        withCredentials: true,
        params: {
            title: recipeTitle
        }
    };

    try {
        const response = await axios.get(API_ROOT + '/api/recipes/search', config);
        return response.data;

    } catch (error) {
        console.error(error);
        return <any>error;
    }
}

export async function getRecipeById(recipe_id:number):Promise<Recipe>{
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/recipes/'+recipe_id, config);
        return response.data;
    } catch (error) {
        return <any>error;
    }
}