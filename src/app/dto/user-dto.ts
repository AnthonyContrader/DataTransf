import { UserType } from './user-type.enum'

export class UserDto {
    id: number

    username: string

    password: string

    usertype: UserType
}
