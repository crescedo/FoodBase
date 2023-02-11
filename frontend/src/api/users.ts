import { API_ROOT } from "@/config/development";
import { LoginInfo } from "@/model/loginInfo";
import axios from "axios";


export async function addNewUser(loginInfo: LoginInfo): Promise<any>   {
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