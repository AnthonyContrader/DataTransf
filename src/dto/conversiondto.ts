import { Sourcetype } from './sourcetype';

export class ConversionDTO {

    idConversion: number;

    idUser: number

    source: string

    sourceType: Sourcetype
    outputType: Sourcetype

    changes: number

}