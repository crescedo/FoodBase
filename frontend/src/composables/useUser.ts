import { onMounted, ref } from "vue";
import { LoginInfo } from "@/model/loginInfo";
import { addNewUser, getMySecret, updateFavorites,getUsersFavorites,removeFromFavorites } from "@/api/users";
import router from "@/router";
import { UserAuthResponse } from "@/model/userAuthResponse";
import { Recipe } from "@/model/recipe";
import { getRecipeById } from '@/api/recipes';


const username = ref("");
const password = ref("");
const newUser: LoginInfo = {};
const me = ref<UserAuthResponse>({
    loginName: "",
    roles: [],
    expiresAt: new Date
});
const myFavorites=ref<Array<Recipe>>(<Recipe[]>[])

export function useUser() {
    const addUser = async () => {
        try {
            newUser.loginName = username.value;
            newUser.passwordHash = password.value;
            await addNewUser(newUser);
            console.log("new user added " + newUser);
            router.push('/')

        } catch (error) {
            console.log(error)
        }
    }
    const addToFavorites = async (recipeId:number) => {
        try {
            const recipe=await getRecipeById(recipeId);
            await updateFavorites(recipe);
            
        } catch (error){console.log(error); }
    }

    const removeRecipeFromFavorites = async (recipeId:number) => {
        try {
            
            await removeFromFavorites(recipeId);
            
        } catch (error){console.log(error); }
    }

    const getFavorites = async () => {
        try {
            
            myFavorites.value=await getUsersFavorites();
                    console.log (myFavorites.value);    
        } catch (error){console.log(error); }
    }

    onMounted(getFavorites)

    return {
        username,
        password,
        addUser,
        addToFavorites,
        myFavorites,
        onMounted,
        removeRecipeFromFavorites

    }
}