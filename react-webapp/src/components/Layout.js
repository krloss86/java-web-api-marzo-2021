import  React from 'react';
import { Login } from './Login';
import { Mensajeria } from './Mensajeria';
import { Form } from './Form';
import { Navbar} from './Navbar'
import { Listado} from './Listado'

export class Layout extends React.Component {

    //contructor
    constructor() {
        super();

        //state
        this.state = {
            contador: 0
        }

        //raro!!
        this.incrementar = () => {
            // this.state.contador++;
            this.setState(
                {
                    contador: this.state.contador +1
                }
            );
        }

        this.decrementar = () => this.setState(
            {
                contador: this.state.contador - 1
            }
        );
    }

    render() {
        //props
        return (
            <>
            <Mensajeria></Mensajeria>
            <Navbar></Navbar>
            <Login></Login>             
            <Form></Form>
            <Listado></Listado>
            </>
        );
    }
}