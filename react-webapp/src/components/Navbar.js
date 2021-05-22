import React, { Fragment } from "react";
import { AppService} from '../services/appService';

export class Navbar extends React.Component {

    constructor() {
        super();
        this.state = {
            username: ''
        }
        this.appService = AppService.instance;

    }
    
    logout = (e) => {
        e.preventDefault();
        let confirma = window.confirm('¿Está seguro?');
        if(confirma) {
            this.appService.logout();
        }
    }

    componentDidMount() {
        let username = this.appService.getUserName();
        this.setState(
            {
                username: username
            }
        );
    }

    render() {
        return (
            <nav className="navbar navbar-light">
                <a className="navbar-brand">Usuario: {this.state.username}</a>
                <button className="btn btn-info my-2 my-sm-0" type="submit" onClick={this.logout}>
                    Logout
                </button>
            </nav>
        );
    }
}