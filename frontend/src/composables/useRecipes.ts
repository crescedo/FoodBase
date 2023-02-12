import { getAllRecipes, getAllRecipesByTitle } from '@/api/recipes';
import { Recipe } from '@/model/recipe';
import { onMounted, ref } from 'vue';

export function useRecipes() {
    const recipes = ref<Recipe[]>([]);
    const title = ref("");

    const getRecipes = async () => {
        try {
            recipes.value = await getAllRecipes();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
    const getRecipesByTitle = async () => {
        if(title.value==="")await getRecipes();
        else{
            try {
                recipes.value = await getAllRecipesByTitle(title.value);

                console.log(recipes.value)
            } catch (error) {
                console.log(error); // FIXME: Errorhandling
            }
        }
        
    }

    onMounted(getRecipes);
    return {
        getRecipes,
        recipes,
        getRecipesByTitle,
        title,

    }
} 