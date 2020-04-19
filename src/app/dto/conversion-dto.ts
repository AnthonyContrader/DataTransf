import { SourceOutputType } from './source-output-type.enum';

export class ConversionDto {

    idConversion: number;

    idUser: number

    source: string

    sourceType: SourceOutputType
    outputType: SourceOutputType

    changes: number

}
