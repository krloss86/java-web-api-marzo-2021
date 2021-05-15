import React from 'react';
import MensajeriaService from '../services/mensajeriaService';
//import ProuctoDataService from '../services/productoDataService';
import ProductoServie from '../services/productoService';

/**
 * Producto recibo por props la funcion deleteProducto
 */
class Producto extends React.Component {
    constructor () {
        super();
        this.productoService = ProductoServie.instance;
        // this.productoDataService = ProuctoDataService.instance;
        this.mensajeriaService = MensajeriaService.instance;

        this.deleteProducto = (id) => {
            //if(confirm('Desea eliminar')) {
                this.productoService.deleteProducto(id).subscribe(
                    data => {
                        this.mensajeriaService.success(`Se ha eliminado exitosamente el producto ${id}`)
                    },
                    error => {
                        console.log(error);
                        this.mensajeriaService.error({message: `NO se ha eliminado exitosamente el producto ${id}`})
                    }
                );
            //}
        }

        //editar 
    }

    render() {
        // console.log('Producto render');
        // console.log(this.props);

        return (

            <tr>
                <th scope="row">
                    {this.props.producto.id}
                </th>
                <th>
                    {this.props.producto.codigo}
                </th>
                <td>
                    {this.props.producto.titulo}
                </td>
                <td>
                    {this.props.producto.precio}
                </td>
                <td>
                    {this.props.producto.tipoProducto.descripcion}
                </td>
                <td>
                    <button 
                        className="btn btn-danger" 
                        onClick={(e) =>this.deleteProducto(this.props.producto.codigo)}
                    >
                        Eliminar
                    </button>

                    <button 
                        className="btn btn-primary ml-2" 
                        onClick={(e) =>this.editarProducto(this.props.producto)}
                    >
                        Editar
                    </button>
                </td>
            </tr>
            
        );
    }
}

export default Producto;