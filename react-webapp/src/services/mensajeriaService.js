import { BehaviorSubject } from "rxjs";

//singleton
class MensajeriaService {

    constructor() {

        if(!MensajeriaService.instance) {

            //rxjs

            //Subject -> emit
            this.mensajeSubjet = new BehaviorSubject({});

            //Observable -> escucha
            this.currentData = this.mensajeSubjet.asObservable();

            this.getCurrent = () => {
                return this.currentData;
            }
            
            this.error = (errorObj) => {
                const newMessage = {
                    mensaje: errorObj.message || 'Error inesperado',
                    type: 'alert alert-danger'
                }
                this.mensajeSubjet.next(newMessage);
                //set timeout
                setTimeout(() => {
                    this.clearMessage();
                }, 2500);
            }

            this.success = (message) => {
                const newMessage = {
                    mensaje: message,
                    type: 'alert alert-success'
                }
                this.mensajeSubjet.next(newMessage);
                //set timeout
                setTimeout(() => {
                    this.clearMessage();
                }, 2500);
            }
            this.clearMessage = () => {
                const newMessage = {
                    mensaje: null,
                    type: null
                }
                this.mensajeSubjet.next(newMessage);
            }
        }

        MensajeriaService.instance = this;
        return this;
    }
}

const instance = new MensajeriaService;
Object.freeze(instance);

export default MensajeriaService;