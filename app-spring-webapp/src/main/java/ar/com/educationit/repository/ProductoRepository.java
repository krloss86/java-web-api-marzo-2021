package ar.com.educationit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.educationit.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	public Producto findByCodigo(String codigo);
}
