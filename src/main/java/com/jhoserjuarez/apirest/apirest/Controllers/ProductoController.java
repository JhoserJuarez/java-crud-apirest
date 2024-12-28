package com.jhoserjuarez.apirest.apirest.Controllers;

import java.util.List;

import com.jhoserjuarez.apirest.apirest.Entities.Producto;
import com.jhoserjuarez.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontró el producto con el ID: "+id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto DetailProduct) {
        Producto producto= productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("No se encontró el producto con el ID"));

        producto.setName(DetailProduct.getName());
        producto.setPrice(DetailProduct.getPrice());

        return productoRepository.save(producto);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, @RequestBody Producto DetailProduct){
        Producto producto= productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("No se encontró el producto con el ID"));
        productoRepository.save(producto);
        return "El producto con el ID "+id+" fue eliminado correctamente";
    }
}
