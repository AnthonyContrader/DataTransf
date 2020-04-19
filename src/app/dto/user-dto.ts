import { SourceOutputType } from './source-output-type.enum'

export class UserDto {
    id: number

    username: string

    password: string

    usertype: SourceOutputType
}
