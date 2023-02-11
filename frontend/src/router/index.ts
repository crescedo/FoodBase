import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import CookingBook from '../views/CookingBook.vue';
import HomeScreen from '../views/HomeScreen.vue';
import Login from '../views/Login.vue';
import MyFavorites from '../views/MyFavorites.vue';
import TabsPage from '../views/TabsPage.vue';
import UserAccount from '../views/UserAccount.vue';
import SignUp from '../views/SignUp.vue';
import RecipeDetail from '../views/RecipeDetail.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/register',
    component: SignUp,
  },
  {
    path: '/tabs/',
    component: TabsPage,

    children: [
      {
        path: '',
        redirect: '/tabs/homescreen'
      },
      {
        path: 'homescreen',
        component: () => import('@/views/HomeScreen.vue')
      },
      {
        path: 'myfavorites',
        component: () => import('@/views/MyFavorites.vue')
      },
      {
        path: 'searchrecipe',
        component: () => import('@/views/CookingBook.vue')
      }
      ,
      {
        path: 'useraccount',
        component: () => import('@/views/UserAccount.vue')
      }
      ,
      {
        path: 'loginscreen',
        component: () => import('@/views/Login.vue')
      },
      {
        path: 'filterrecipe',
        component: () => import('@/views/FilterRecipe.vue'),
        
      }
            
      ,{
        name:'recipeDetail',
        props: true,
        path:'recipes/:id?',
        component:RecipeDetail,
        
      }


      
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
