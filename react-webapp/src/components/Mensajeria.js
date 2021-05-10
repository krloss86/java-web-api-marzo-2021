import React from "react";
import MensajeriaService from "../services/appService";

export class Mensajeria extends React.Component {

    constructor() {
        super();

        //aca uso la instancia de MensajeriaSrevice
        this.mensajeriaService = MensajeriaService.instance;
    }

    render() {
        return(
            <div className="row">
                <div className="col-12">
                    <div className="alert alert-primary" role="alert">
                        A simple primary alert—check it out!
                    </div>
                </div>
            </div>
        )
    }

    //hooks antes de render, 
    componentDidMount() {
        this.mensajeriaService.getCurrent();
    }
}