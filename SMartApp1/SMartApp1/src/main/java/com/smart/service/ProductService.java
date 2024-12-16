package com.smart.service;

import com.smart.entity.Category;
import com.smart.entity.Product;
import com.smart.repository.CategoryRepository;
import com.smart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(int page) {
        return productRepository.findAll(PageRequest.of(page, 10));
    }

    public String createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return "Product name cannot be null or empty.";
        }
        if (productRepository.existsByName(product.getName())) {
            return "Product name already exists.";
        }
        if (product.getCategory() == null || !categoryRepository.existsById(product.getCategory().getId())) {
            return "Invalid category ID.";
        }
        productRepository.save(product);
        return "Product created successfully.";
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public String updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            if (product.getName() != null && productRepository.existsByName(product.getName())
                    && !existingProduct.get().getName().equals(product.getName())) {
                return "Product name already exists.";
            }
            if (product.getCategory() == null || !categoryRepository.existsById(product.getCategory().getId())) {
                return "Invalid category ID.";
            }
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.setCategory(product.getCategory());
            productRepository.save(updatedProduct);
            return "Product updated successfully.";
        } else {
            return "Product not found.";
        }
    }

    public String deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product deleted successfully.";
        }
        return "Product not found.";
    }
}
