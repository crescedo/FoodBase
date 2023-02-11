import { ref } from "vue";
import { LoginInfo } from "@/model/loginInfo";
import { addNewUser } from "@/api/users";
import router from "@/router";

const username = ref("");
const password = ref("");
const newUser: LoginInfo = {};

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
    return {
        username,
        password,
        addUser,
    }
}