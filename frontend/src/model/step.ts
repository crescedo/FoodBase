import { Image } from "./image";

export interface Step {
    id?: number;
    title?: string;
    content?: string;
    images?: Array<Image>;
    stepOrder?: number;
}