import { BehaviorSubject } from 'rxjs';
import { ajax } from 'rxjs/ajax';
import { map } from 'rxjs/operators';

export class AppService {
    constructor() {
        if(!AppService.instance) {
            
            this.url = 'http://localhost:8080/ws-rest-server/api/';
            
            let token = localStorage.getItem('Access-Token');
            //rxjs
            this.behaviorSubject = new BehaviorSubject({logged: token!=null});
            this.currentData = this.behaviorSubject.asObservable();

            this.updateLogged = (logged) => {
                this.behaviorSubject.next(logged);
            }   
        
            this.getCurrent = () => {
                return this.currentData;
            }

            this.login = (username, password) => {
                return ajax.post(`${this.url}auth?username=${username}&password=${password}`)
                    .pipe(
                        map(response => {
                            localStorage.setItem('Access-Token', response.xhr.getResponseHeader('access-token'));
                        })
                );
            }

            this.logout = () => {

                 //elimina el access token guardado en el localstorage
                localStorage.removeItem('Access-Token');
                localStorage.removeItem('tipoProductos');
                localStorage.removeItem('username');

                this.updateLogged({logged:false});
            }

            this.getUserName = () => {
                let base64 = atob(localStorage.getItem('Access-Token'));
                let username = '';
                if(base64) {
                    username = base64.split(":")[0];
                }
                return username;
            }
        }
        //hace singleton esta instancia AppService
        AppService.instance = this;
        return this;
    }
 }
 
 const instance = new AppService();
 
 Object.freeze(instance);

export default AppService;