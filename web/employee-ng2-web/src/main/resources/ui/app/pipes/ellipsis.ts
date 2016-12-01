import {PipeTransform, Pipe} from 'angular2/core';

@Pipe({ name: 'ellipsis' })
export class Ellipsis implements PipeTransform {
    transform(text: string, config: any[]): any {
        let limit = 6;
        if (config && config[0]) {
            limit = config[0];
        }
        return (text && text.length > limit) ? text.substr(0, limit) + '...' : text;
    }
}