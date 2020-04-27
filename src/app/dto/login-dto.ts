export class LoginDTO {

    username: string
    password: string
    rememberMe: boolean

    constructor(username: string, password: string, rememberMe: boolean) {
        this.username = username
        this.password = password
        this.rememberMe = rememberMe
    }

}
