import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import CookingBook from '../views/CookingBook.vue'
import HomeScreen from '../views/HomeScreen.vue'
import LoginScreen from '../views/LoginScreen.vue'
import MyFavorites from '../views/MyFavorites.vue'
import TabsPage from '../views/TabsPage.vue'
import UserAccount from '../views/UserAccount.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/tabs/homescreen'
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
        path: 'cookingbook',
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
        component: () => import('@/views/LoginScreen.vue')
      },
      {
        path: 'filterrecipe',
        component: () => import('@/views/FilterRecipe.vue')
      }
      ,
      {
        path: 'recipeDetail',
        component: () => import('@/views/RecipeDetail.vue')
      }
      ,
      {
        path: 'loginscreen',
        component: () => import('@/views/LoginScreen.vue')
      }


      
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
