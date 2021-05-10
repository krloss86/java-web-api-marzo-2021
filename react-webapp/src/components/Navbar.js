import React from "react";

export class Navbar extends React.Component {

    constructor() {
        super();
    }

    render() {
        return (
            <div className="row">
                <div className="col-12">
                    <form>
                        <div className="form-gruop">
                            <label>Usuario: ANONIMO</label>
                            <button className="btn btn-info pull-right">Logout</button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}