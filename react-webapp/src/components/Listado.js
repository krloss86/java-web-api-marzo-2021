import React from 'react';
import Producto from './Producto';
import ProductoService from '../services/productoService';
import ProductoDataService from '../services/productoDataService';

export class Listado extends React.Component {

    constructor() {
        super();

        this.state = {
            productos: []
        }

        this.productoDataService  = ProductoDataService.instance;
        this.productoService = ProductoService.instance;
    }

    componentDidMount() {
        this.productoService.findAll().subscribe(
            data => {
                this.productoDataService.updateProductos(data.response);
            }
        );

        this.productoDataService.getProductos().subscribe(
            data => this.setState(
                {
                    productos: data
                }
            )
        );
    }

    render() {
        return (
            <div className="row mt-3">
                <div className="col-12">
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Título</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Codigo</th>
                                <th scope="col">Tipo</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.productos.map(function(producto, index){
                                    // return <tr>
                                    // <th scope="row">{prod.id}</th>
                                    // <td>{prod.titulo}</td>
                                    // <td>{prod.precio}</td>
                                    // <td>{prod.codigo}</td>
                                    // <td>{prod.tipoProducto.descripcion}</td>
                                    // </tr>
                                    return <Producto key={index} producto={producto}> </Producto>
                                })
                            }
                        </tbody>
                    </table>

                </div>
            </div>
        )
    }
}