package ar.com.educationit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.educationit.domain.Producto;
import ar.com.educationit.domain.TipoProducto;
import ar.com.educationit.enums.ProductoClavesEnums;
import ar.com.educationit.services.ProductoService;

@Controller
public class ProductoController {

	//I.O.C / C.D.I
	@Autowired 
	private ProductoService ps;
		
	@RequestMapping("/list")
	public String listado(Model model) {
		
		List<Producto> productos = this.ps.obtenerTodos();

		model.addAttribute(ProductoClavesEnums.PRODUCTOS.getClave(), productos);
		return "listado";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable(name="id")
						Long id,
						Model model) {
		Producto producto = this.ps.obtenerPorId(id);
		
		model.addAttribute(ProductoClavesEnums.PRODUCTO.getClave(), producto);
		
		List<TipoProducto> tiposProductos = this.ps.findTipoProductos();

		model.addAttribute(ProductoClavesEnums.TIPOS_PRODUCTOS.getClave(), tiposProductos);

		return "edit";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@ModelAttribute(name = "producto") 
			Producto producto
			) {
		
		this.ps.actualizarProducto(producto);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/delete/{codigo}")
	public String delete(@PathVariable(name = "codigo") String id) {		
		
		this.ps.eliminarProducto(id);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/new")
	public ModelAndView newProducto() {
		Producto producto = new Producto();
		
		ModelAndView modelAndView =  new ModelAndView("new_product");
		
		modelAndView.addObject(ProductoClavesEnums.PRODUCTO.getClave(), producto);
		
		List<TipoProducto> tiposProductos = this.ps.findTipoProductos();
		modelAndView.addObject(ProductoClavesEnums.TIPOS_PRODUCTOS.getClave(), tiposProductos);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(
		@ModelAttribute(name = "producto")
		Producto producto) {
	
		this.ps.grabarProducto(producto);
		
		return "redirect:/list";
	}
}
