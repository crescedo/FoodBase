import { API_ROOT } from "@/config/development";
import { LoginInfo } from "@/model/loginInfo";
import { Recipe } from "@/model/recipe";
import { User } from "@/model/user";
import { UserAuthResponse } from "@/model/userAuthResponse";
import axios from "axios";


export async function addNewUser(loginInfo: LoginInfo): Promise<any> {
    const config = {
        withCredentials: true
    }
    try {
        console.log(loginInfo);
        const response = await axios.post(API_ROOT + '/api/users', loginInfo, config);
        return response.data;
    } catch (error) {
        return error;
    }
}
export async function getMe(): Promise<User> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/me', config);
        return response.data;
    } catch (error) {
        return <any>error;
    }
}
export async function getCurrentUserDetails():Promise<User>{
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/users/me', config);
        return response.data;
    } catch (error) {
        return <any>error;
    }
}

export async function updateFavorites(recipe: Recipe): Promise<any>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.put(API_ROOT + '/api/users/favorites', recipe, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}
export async function getUsersFavorites():Promise<Recipe[]>{

    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/users/favorites',  config);
        return response.data;
    } catch (error) {
        return <any> error;   
    }
}
export async function removeFromFavorites(id: number): Promise<any>   {
    const config = {        
        withCredentials: true,
        params: {
            recipeId: id
        }
    }
    try {
        const response = await axios.delete(API_ROOT + '/api/users/favorites', config);
        return response.data;
    } catch (error) {
        return error;   
    }
}
