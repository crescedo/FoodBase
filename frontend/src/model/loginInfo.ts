export interface LoginInfo {
    id?: number;
    loginName?: string;
    passwordHash?: string;
    roles?: Array<string>;
}