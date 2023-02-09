import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Recipe } from '@/model/recipe';

export async function getAllRecipes(): Promise<Recipe[]>   { 
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