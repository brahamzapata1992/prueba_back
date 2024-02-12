package com.ecommerce_instrumentos.services.admin.adminproduct;

import com.ecommerce_instrumentos.dto.ProductDto;
import com.ecommerce_instrumentos.entity.Category;
import com.ecommerce_instrumentos.entity.Product;
import com.ecommerce_instrumentos.repository.CategoryRepository;
import com.ecommerce_instrumentos.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con id: " + productDto.getCategoryId()));


        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductsByName(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Actualiza los campos solo si no son nulos en el DTO
            if (productDto.getName() != null) {
                existingProduct.setName(productDto.getName());
            }
            if (productDto.getDescription() != null) {
                existingProduct.setDescription(productDto.getDescription());
            }
            if (productDto.getPrice() != null) {
                existingProduct.setPrice(productDto.getPrice());
            }

            // Actualiza la imagen solo si se proporciona una nueva imagen
            if (productDto.getImg() != null) {
                existingProduct.setImg(productDto.getImg().getBytes());
            }

            // Actualiza la categoría solo si se proporciona un nuevo categoryId
            if (productDto.getCategoryId() != null) {
                Category newCategory = categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con id: " + productDto.getCategoryId()));
                existingProduct.setCategory(newCategory);
            }

            // Guarda y devuelve la entidad actualizada
            Product updatedProduct = productRepository.save(existingProduct);
            return updatedProduct.getDto();
        }

        throw new NoSuchElementException("Producto no encontrado con id: " + id);
    }

    public List<ProductDto> getProductsByCategories(List<Long> categoryIds) {
        List<Product> products = productRepository.findByCategory_IdIn(categoryIds);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }




}
