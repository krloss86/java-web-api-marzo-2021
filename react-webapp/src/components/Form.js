import  React from 'react';

export class Form extends React.Component {

    constructor() {
        super();
    }

    render() {
        //props
        return (
            <div className="row">
                <div className="col-6">
                    <form >
                        <div className="form-group">
                            <label htmlFor="tituloBusqueda">Título</label>
                            <input type="text" className="form-control" 
                                name="tituloBusqueda" 
                                id="tituloBusqueda"
                            ></input>
                        </div>
                        <button className="btn btn-primary">Buscar Productos</button>
                    </form>
                </div>
                <div className="col-6">
                    <form>                
                        <div className="form-group">
                            <label htmlFor="codigo">Codigo</label>
                            <input type="text" className="form-control" 
                                name="codigo"
                                id="codigo"
                            ></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Título</label>
                            <input type="text" className="form-control" 
                                id="password"
                                name="titulo"
                            ></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="precio">Precio</label>
                            <input type="text" className="form-control" 
                                name="precio" 
                                id="precio"
                            ></input>
                        </div>
                        <div className="form-group">
                            <select className="custom-select" id="tipoProducto" aria-label="Example select with button addon">
                            </select>
                        </div>
                        <button className="btn btn-success">Grabar</button>
                    </form>
                </div>
            </div>
        )
    }
}