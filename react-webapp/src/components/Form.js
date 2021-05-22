import  React from 'react';
import ProductoDataService from '../services/productoDataService';
import ProductoService from '../services/productoService';
import MensajeriaService from '../services/mensajeriaService';

export class Form extends React.Component {

    constructor() {
        super();
        this.state = {
            codigo:"",
            titulo:"",
            precio:"",
            tipoProducto:"",
            tiposProductos:[],
            tituloBusqueda: ""
        };

        this.productoService = ProductoService.instance;
        this.productoDataService = ProductoDataService.instance;
        this.mensajeriaService = MensajeriaService.instance;

        this.handleChange = event => {
            let isCheckbox = event.target.type === "checkbox";
            this.setState(
                {
                    [event.target.name] : isCheckbox
                    ? event.target.checked 
                    : event.target.value
                }
            );
        }

        this.handleChangeSelect = event => {
            this.setState(
                {
                    "tipoProducto": event.target.value
                }
            );
        }

        this.createProducto = event => {
            event.preventDefault();
            //json
            let producto = {
                codigo: this.state.codigo,
                titulo: this.state.titulo,
                precio: this.state.precio,
                tipoProducto: {
                    id: this.state.tipoProducto
                }
            }
            
            this.productoService.createProducto(producto).subscribe(
                data => {
                    console.log(data.response);
                    this.setState({
                        codigo: '',
                        titulo: '',
                        precio: '',
                        tipoProducto: {
                            id: 0
                        }
                    });
                    this.mensajeriaService.success(`Se ha creado exitosamente el producto`);
                },
                error => {
                    this.mensajeriaService.error(`No se ha creado exitosamente el producto, ${error}`);
                }
            );            
        }
        
        this.buscarProducto = event => {
            event.preventDefault();
            if(this.state.tituloBusqueda) {
                this.productoService.search(this.state.tituloBusqueda).subscribe(
                    data => {
                        this.productoDataService.updateProductos(data.response);
                    },error => {
                        this.mensajeriaService.error(error);
                    }
                );
            }else {
                this.productoService.findAll().subscribe(
                    response => {
                        this.productoDataService.updateProductos(response.response);
                    },error => {
                        this.mensajeriaService.error(error);
                    }
                );
            }
        }
    }

    componentDidMount()  {
        debugger;
        //cargo los tipo de productos
        this.productoService.findTiposProductos().subscribe(
            data => {
                const tiposProductos = data.response;
                localStorage.setItem('tiposProductos', JSON.stringify(tiposProductos));

                this.setState({
                    tiposProductos: tiposProductos
                });
            }
        );
    }

    render() {
        //obtenemos los tipos de productos
        const tipoProducto = JSON.parse(localStorage.getItem('tiposProductos')) || [];
        
        return (
            <div className="row mt-3">
                <div className="col-6">
                    <form onSubmit={this.buscarProducto}>
                        <div className="form-group">
                            <label htmlFor="tituloBusqueda">Título</label>
                            <input type="text" className="form-control" 
                                name="tituloBusqueda" 
                                id="tituloBusqueda"
                                value={this.tituloBusqueda}
                                onChange={this.handleChange}
                            ></input>
                        </div>
                        <button className="btn btn-primary">Buscar Productos</button>
                    </form>
                </div>
                <div className="col-6">
                    <form onSubmit={this.createProducto}>                
                        <div className="form-group">
                            <label htmlFor="codigo">Codigo</label>
                            <input type="text" className="form-control" 
                                name="codigo"
                                id="codigo"
                                value={this.state.codigo}
                                onChange={this.handleChange}
                            ></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Título</label>
                            <input type="text" className="form-control" 
                                id="password"
                                name="titulo"
                                value={this.state.titulo}
                                onChange={this.handleChange}
                            ></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="precio">Precio</label>
                            <input type="text" className="form-control" 
                                name="precio" 
                                id="precio"
                                value={this.state.precio}
                                onChange={this.handleChange}
                            ></input>
                        </div>
                        <div className="form-group">
                            <select className="custom-select" id="tipoProducto" aria-label="Example select with button addon"
                                value={this.state.tipoProducto}
                                onChange={this.handleChangeSelect}>
                                    {
                                        tipoProducto.map(function(tipo, index){
                                            return <option key={index} id={index} value={tipo.id}>{tipo.descripcion}</option>
                                        })
                                    }
                            </select>
                        </div>
                        <button className="btn btn-success">Grabar</button>
                    </form>
                </div>
            </div>
        )
    }
}