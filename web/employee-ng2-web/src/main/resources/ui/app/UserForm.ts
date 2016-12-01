
import {Component,Output,EventEmitter} from 'angular2/core';
import {  TestContainer} from './TestContainer';
import {Router, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {User} from './entity/User';
import {UserService} from './services/UserService';


// var golbalVar=
@Component({
    selector: 'user-form',
    // templateUrl:`app/component.html`,
    template: `First Name: <input [(ngModel)]='userform.fname'/>
    Age: <input [(ngModel)]='userform.age'/>
    <div>
        Gender:
        <label>
           Male: <input #male [ngModel]='userform.gender' name="gender" type="radio" [checked]=false (change)="genderOnChange('Male',male.checked)"/>
        </label>
        <label>
           Female: <input #female [ngModel]='userform.gender' name="gender" type="radio" [checked]=false (change)="genderOnChange('Female',female.checked)"/>
        </label>
    </div>
    {{diagnostic}}
    <div *ngFor='#skill of skills ; #i = index'>
        <label> <input #{{skill}} type="checkbox" (change)="addSkill(skill, i, $event)">{{skill}}</label>
    </div>
    <button class='save-btn' (click)='save()'> Save
    </button>
    <test></test>`,
    providers: [UserService],
    directives: [TestContainer] //DI
})
export class UserForm {//controller
    @Output() newUserEvent: EventEmitter<User> = new EventEmitter();
    userform: User = new User();
    

    constructor(private userService: UserService) {

    }
    skills =[ 'Java', 'Angular2','JavaScript' ];

    addSkill(skill, index, event){
        console.log(skill);
        console.log(index);
        console.log(event.target.checked);
        if(event.target.checked){
            this.userform.skills[index] = skill;
        }else{
            this.userform.skills.splice(index,1);
        }
    }
    users: User[] = [];
    save() {
        var observable = this.userService.save(this.userform);
        observable.subscribe(response => {
            console.log(response);
            var nUser = JSON.parse(response._body);
            this.users.push(nUser);
            this.newUserEvent.emit(nUser);
            this.userform = new User();
            console.log('added successfully', this.userform);
        });
    }
    
    genderOnChange(value,checked){
    	console.log(value);
    	console.log(checked);
    	if(checked){
    		this.userform.gender=value;
    	}else{
    		this.userform.gender="";
    	}
    }

   
}
