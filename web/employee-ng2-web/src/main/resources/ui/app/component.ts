
import {Component} from 'angular2/core';
import {UserForm} from './UserForm';
import {UserGrid} from './UserGrid';
import {Router, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {User} from './entity/User';


// var golbalVar=
@Component({
    selector: 'top-container',
    // templateUrl:`app/component.html`,
    template: `<h1>Welcome to User Form</h1>
    <user-form (newUserEvent)="newUserChange($event)"></user-form>
    <user-grid [users]="users"></user-grid>`,
    directives: [UserForm, UserGrid] //DI
})
export class TopContainer {//controller
    users: User[] = [];
    constructor(){
        console.log('initializing the top container');
    }
   
       newUserChange(user:User){
        console.log('Received the new user change event');
        this.users.push(user);
        console.log(user);
    }
}
