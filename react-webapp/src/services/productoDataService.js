import { BehaviorSubject } from "rxjs";

export class ProductoDataService {

    constructor() {
        if(!ProductoDataService.instance) {
            this.mensajeSubject = new BehaviorSubject([]);
            this.currentData = this.mensajeSubject.asObservable();

            this.updateProductos = (productos) => {
                this.mensajeSubject.next(productos);
            }

            this.getProductos = () => {
                return this.currentData;
            }

            this.getProductosValue = () => {
                return this.currentData.source.value;
            }
        }
        ProductoDataService.instance = this;
        return this;
    }
}

const instance = new ProductoDataService();

Object.freeze(instance);

export default ProductoDataService;