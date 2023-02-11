
import { getRecipeById } from '@/api/recipes';
import { Recipe } from '@/model/recipe';
import { onMounted, ref } from 'vue';

const recipe_id = ref(0);
const recipe = ref<Recipe>({})
export function useRecipeDetail() {
    const onMount = async () => {

        recipe.value = await getRecipeById(recipe_id.value);
        console.log(recipe.value)
    }
    onMounted(onMount)
    return {
        onMount,
        recipe_id,
        recipe
    }
}