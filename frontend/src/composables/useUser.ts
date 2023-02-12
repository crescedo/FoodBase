import { onMounted, ref } from "vue";
import { LoginInfo } from "@/model/loginInfo";
import { addNewUser, getMe, getCurrentUserDetails, updateFavorites, getUsersFavorites, removeFromFavorites } from "@/api/users";
import router from "@/router";
import { UserAuthResponse } from "@/model/userAuthResponse";
import { Recipe } from "@/model/recipe";
import { getRecipeById } from '@/api/recipes';
import { User } from "@/model/user";


const username = ref("");
const password = ref("");
const newUser: LoginInfo = {};
const me = ref<User>({})
const myFavorites = ref<Array<Recipe>>(<Recipe[]>[])

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

    const addToFavorites = async (recipeId: number) => {
        try {
            const recipe = await getRecipeById(recipeId);
            await updateFavorites(recipe);

        } catch (error) { console.log(error); }
    }

    const removeRecipeFromFavorites = async (recipeId: number) => {
        try {

            await removeFromFavorites(recipeId);

        } catch (error) { console.log(error); }
    }
    const getCurrentUserInfos = async () => {
        try {
            me.value= await getCurrentUserDetails();
console.log(me.value);

        } catch (error) { console.log(error); }
    }

    const getFavorites = async () => {
        try {

            myFavorites.value = await getUsersFavorites();
            console.log(myFavorites.value);
        } catch (error) { console.log(error); }
    }

    onMounted(getFavorites)

    return {
        username,
        password,
        me,
        myFavorites,
        addUser,
        addToFavorites,
        onMounted,
        removeRecipeFromFavorites,
        getCurrentUserInfos
    }
}