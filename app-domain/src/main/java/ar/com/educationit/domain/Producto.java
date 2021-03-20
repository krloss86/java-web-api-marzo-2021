package ar.com.educationit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", length = 50, nullable = false)
	private String titulo;
	
	@Column(name = "precio", nullable = false)
	private Float precio;
	
	@Column(name="codigo", length = 6, nullable = false, unique = true)
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_producto", referencedColumnName = "id")
	private TipoProducto tipoProducto;
	
	public Producto() {
		
	}

	public Producto(String titulo, Float precio, String codigo, TipoProducto tipoProducto) {
		this.titulo = titulo;
		this.precio = precio;
		this.codigo = codigo;
		this.tipoProducto = tipoProducto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", codigo=" + codigo
				+ ", tipoProducto=" + tipoProducto + "]";
	}
	
	
}
