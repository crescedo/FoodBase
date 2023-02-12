import { onMounted, ref } from "vue";
import { LoginInfo } from "@/model/loginInfo";
import { addNewUser, getMySecret, updateFavorites } from "@/api/users";
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

    return {
        username,
        password,
        addUser,
        addToFavorites,

    }
}