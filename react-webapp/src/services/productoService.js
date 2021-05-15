import { BehaviorSubject} from 'rxjs';
import MensajeriaService from './mensajeriaService';
import ProductoDataService from './productoDataService';
import { ajax } from 'rxjs/ajax';
import { map} from 'rxjs/operators';

export class ProductoServie {
    
    constructor() {

        if(!ProductoServie.instance) {
            this.httpClient  = ajax;
            this.productoDataService = ProductoDataService.instance;
            this.mensajeriaService = MensajeriaService.instance;

            this.url = `http://localhost:8080/ws-rest-server/`;
            this.api = 'api/productos';
            this.searchUrl = 'api/productos/filtro/';
            this.tipoProductoApi = 'api/tipoproducto';

            this.subject = new BehaviorSubject([]);
            this.subscription = this.subject.asObservable();
            
            this.findAll = () => {
                return this.httpClient.get(`${this.url}${this.api}`);
            }

            this.search = (titulo) => {
                return this.httpClient.get(`${this.url}${this.searchUrl}${titulo}`)
                .pipe(
                    map(response => {
                        const productos = response.response;
                        this.productoDataService.updateProductos(productos);
                        return response;
                    })
                );
            }

            this.deleteProducto = (id) => {
                return this.httpClient.delete(
                    `${this.url}${this.api}/${id}`,
                    {
                        Authorization: `Basic ${localStorage.getItem('Access-Token')}`
                    }
                ).pipe(
                    map(response => {
                        //console.log(response.response);
                        let actuales = this.productoDataService.getProductosValue();
                        actuales = actuales.filter(x=> x.codigo !== id);
                        this.productoDataService.updateProductos(actuales);
                        return response;
                    })
                );
            }

            this.createProducto = (producto) => {
                return this.httpClient.post(
                    `${this.url}${this.api}`,
                    producto,
                    {
                        Authorization: `Basic ${localStorage.getItem('Access-Token')}`,
                        'Content-Type': 'application/json',
                    }
                ).pipe(
                    map(response => {
                        let actuales = this.productoDataService.getProductosValue();
                        actuales.push(response.response);
                        this.productoDataService.updateProductos(actuales);
                        return response;
                    }
                ));
            }

            this.findTiposProductos = () => {
                return this.httpClient.get(`${this.url}${this.tipoProductoApi}`);
            }

            ProductoServie.instance = this;
            return this;
        }
    }
}

const instance = new ProductoServie();

Object.freeze(instance);

export default ProductoServie;