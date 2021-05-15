import React from "react";
import MensajeriaService from "../services/mensajeriaService";

export class Mensajeria extends React.Component {

    constructor() {
        super();

        this.state = {
            mensaje: null, 
            type: null   
        }

        //aca uso la instancia de MensajeriaSrevice
        this.mensajeriaService = MensajeriaService.instance;
    }

    render() {
        let clase = `col-12 ${this.state.type} alert-dismissible fade show`;
        return(
            <>
            {
                this.state.mensaje !=null && 
                <div className="row">
                    <div className={clase} role="alert">
                        {this.state.mensaje}
                        <button type="button" onClick={this.mensajeriaService.clearMessage} className="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            }
            </>
        )
    }

    //hooks antes de render, 
    componentDidMount() {
        //escuchar los mensajes que emita MensajeriaService
        this.mensajeriaService.getCurrent().subscribe(
            data =>  this.setState({
                mensaje: null || data.mensaje,
                type: data.type
            })
        )
    }
}