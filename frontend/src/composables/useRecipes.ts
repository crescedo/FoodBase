import { getAllRecipes } from '@/api/recipes';
import { Recipe } from '@/model/recipe';
import { onMounted, ref } from 'vue';

export  function useRecipes(){
    const recipes = ref<Recipe[]>([]);


    const getRecipes = async () => {
        try {
            recipes.value = await getAllRecipes();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }
    onMounted(getRecipes);
    return {
        getRecipes,
        recipes,
       
    }
} 