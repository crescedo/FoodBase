import { Image } from "./image";

export interface UserInfo {
    id?: number;
    firstName?: string;
    lastName?: string;
    profilePic?: Array<Image>;
}