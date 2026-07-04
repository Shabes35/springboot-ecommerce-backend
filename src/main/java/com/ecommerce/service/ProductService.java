package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.ProductRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private  final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product saveProduct ( Product product){
        return productRepository.save(product);
    }
    public Page<ProductDTO> getAllProducts(
            int page ,
            int size,
            String sortBy,
            String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();
        Page<Product> products =  productRepository.findAll(
                PageRequest.of(page, size ,sort)
        );

        return products.map(this::convertToDTO);
    }
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Product not found with id : "+ id
                        ));

    }
    public ProductDTO getProductById(Long id){
        Product product = getProduct(id);

        return convertToDTO(product);
    }
    public void deleteProduct(Long id){
        if( productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
    public Product updateProduct(Long id,  Product product ){


            Product existing = getProduct(id);


            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
            existing.setCategory(product.getCategory());

            return productRepository.save(existing);

    }

    public Product patchProduct(Long id, Map<String, Object> updates) {
        Product existing = productRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "Product not found with id : "+ id
                        ));

        if(updates.containsKey("name")){
            existing.setName((String)updates.get("name"));
        }
        if (updates.containsKey("description")) {
            existing.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("price")) {
            existing.setPrice(Double.valueOf(updates.get("price").toString()));
        }

        if (updates.containsKey("quantity")) {
            existing.setQuantity(Integer.valueOf(updates.get("quantity").toString()));
        }

        return productRepository.save(existing);
    }

    public List<Product> searchProductByName(String name){
        return productRepository.findByNameContaining(name);

    }
    public Page<Product> findByCategory(
            int page, int size, String sortBy ,String direction , Long id
    ){
     Sort sort = direction.equalsIgnoreCase("desc")
             ? Sort.by(sortBy).descending()
             : Sort.by(sortBy).ascending();

     return productRepository.findByCategoryId(id, PageRequest.of(page , size , sort));

    }

    public long getProductCount(){
        return productRepository.count();
    }

    private ProductDTO convertToDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCategoryName(product.getCategory().getName());

        return productDTO;
    }


}
