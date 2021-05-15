import  React from 'react';
import { Login } from './Login';
import { Mensajeria } from './Mensajeria';
import { Form } from './Form';
import { Navbar} from './Navbar';
import { Listado} from './Listado';
import { AppService} from '../services/appService';

export class Layout extends React.Component {

    //contructor
    constructor() {
        super();

        let token = localStorage.getItem('Access-Token');

        //state
        this.state = {
            logged: token != null
        }
        
        this.appService = AppService.instance;
    }

    render() {
        //props
        // console.log(this.state.productos);
        return (
            <>
            <Mensajeria></Mensajeria>
            {
                this.state.logged && 
                <Navbar></Navbar>
            }
            {
                !this.state.logged &&
                <Login></Login>
            }            
            {
                this.state.logged &&
                <Form></Form>
            }
            {/* <button className="btn btn-primary" onClick={this.findProductos}>Cargar productos</button> */}
            {
                this.state.logged &&
                <Listado productos={this.state.productos}></Listado>
            }
            </>
        );
    }

    //
    componentDidMount() {
        this.appService.getCurrent().subscribe(
            data => {
                this.setState(
                    {
                        logged: data.logged
                    }
                )
            }
        );
    }
}