
//singleton
class MensajeriaService {

    constructor() {

        if(!MensajeriaService.instance) {

            this.getCurrent = () => {
                return "vacio";
            }

            this.error = (errorObj) => {
                alert(errorObj);
            }

            this.success = (message) => {
                alert(message);
            }
            this.clearMessage = () => {
                alert('Limpar mensajes');
            }
        }

        MensajeriaService.instance = this;
        return this;
    }
}

const instance = new MensajeriaService;
Object.freeze(instance);

export default MensajeriaService;