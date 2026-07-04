package com.ecommerce.controller;


import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public Product addProduct( @Valid @RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping("/page")
    public Page<ProductDTO> getProducts(
            @RequestParam (defaultValue = "0")int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam (defaultValue = "id")String sortBy,
            @RequestParam (defaultValue = "asc")String direction

    ){
        return productService.getAllProducts(page , size, sortBy,direction);

    }
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public  void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct( @PathVariable Long id, @RequestBody Product product){
         return productService.updateProduct(id,product);
    }

    @PatchMapping("/{id}")
    public Product patchProduct(
            @PathVariable  Long id,
            @RequestBody Map<String ,Object> updates

    ){
        return productService.patchProduct(id , updates);
    }


    @GetMapping("/search")
    public List<Product>searchProductByNameContains(@RequestParam String name){
        return productService.searchProductByName(name);
    }
    @GetMapping("/category/{id}")
    public Page<Product> findByCategory(
            @RequestParam (defaultValue = "0")int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam (defaultValue = "id")String sortBy,
            @RequestParam (defaultValue = "asc")String direction,
            @PathVariable Long id
    ){
        return productService.findByCategory(page , size, sortBy, direction, id);
    }
    @GetMapping("/count")
    public long getProductCount(){
        return productService.getProductCount();
    }

}
