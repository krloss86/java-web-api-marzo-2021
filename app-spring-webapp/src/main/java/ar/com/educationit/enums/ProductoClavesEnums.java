package ar.com.educationit.enums;

public enum ProductoClavesEnums {

	PRODUCTOS("productos"), 
	PRODUCTO("producto"), 
	TIPOS_PRODUCTOS("tiposProductos");
	
	private String clave;

	private ProductoClavesEnums(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}
	
}
