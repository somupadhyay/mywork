import {Http, Headers, Response} from 'angular2/http';
import {Injectable} from 'angular2/core';
import {User} from '../entity/User';

@Injectable()
export class UserService{
    constructor(private http:Http){

    }

    save(userModel:User){
        console.log('service called');
        var myHeaders:Headers = new Headers();
        myHeaders.set("content-type",'application/json');
        return this.http.post('/users',JSON.stringify(userModel),{
            headers:myHeaders
        });
    }

    delete(id:number){
        console.log('Request received to delete the entry with Id:'+id);
        // var myHeaders:Headers = new Headers();
        // myHeaders.set("content-type",'application/json');
        return this.http.delete('/users/'+id);
    }
}