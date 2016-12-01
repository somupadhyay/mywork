import {Component,Input} from 'angular2/core';
import {Router, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {User} from './entity/User';
import {UserService} from './services/UserService';
import {Ellipsis} from './pipes/ellipsis';

@Component({
    selector: 'user-grid',
    // templateUrl:`app/component.html`,
    template: `<div>
    User list:
    <ol>
        <li *ngFor='#user of users'>{{user.fname |uppercase|ellipsis}},{{user.age}},{{user.gender}} <button class='save-btn'  (click)='delete(user)'>X</button></li>
    </ol></div>`,
    pipes:[Ellipsis],
    providers: [UserService]
})
export class UserGrid{
    @Input() users: User[] = [];

    constructor(private userService: UserService) {

    }

     delete(user: User) {
        console.log('Starting to delete the record:');
        console.log(user);
        var observable = this.userService.delete(user.id);
        observable.subscribe(response => {
            console.log(response);
            console.log('Successfully deleted.');
            var length = this.users.length;
            for (var i = 0; i < length; i++) {
                if (this.users[i].id === user.id) {
                    this.users.splice(i, 1);
                    length = this.users.length;
                    break;
                }
            }

        });
    }
}