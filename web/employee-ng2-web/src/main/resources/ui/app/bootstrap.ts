import {bootstrap}    from 'angular2/platform/browser'
import {provide}    from 'angular2/core'
import {CORE_DIRECTIVES} from 'angular2/common'
import {ROUTER_BINDINGS, ROUTER_PROVIDERS, LocationStrategy, HashLocationStrategy} from 'angular2/router'
import { HTTP_BINDINGS } from 'angular2/http';
import {TopContainer} from './component'



bootstrap(TopContainer, [
    ROUTER_PROVIDERS,
    CORE_DIRECTIVES,
    HTTP_BINDINGS,
    provide(LocationStrategy, {useClass: HashLocationStrategy })
]);

