package ar.com.educationit.rest.enums;

public enum ProductoErrorsEnum {
	PRODUCTO_VACIO("Producto nulo"), 
	PRODUCTO_CODIGO_VACIO("Código Vacío"), 
	PRODUCTO_ID_INVALIDO("Id de producto Inválido");
	
	private String error;
	
	private ProductoErrorsEnum(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
