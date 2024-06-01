package exercise.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> getList(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        List<Product> products = new ArrayList<>();
        if (min == null && max == null) {
            products = productRepository.findAll(Sort.by(Sort.Order.asc("price")));
        } else if(min != null && max != null) {
            products = productRepository.findByPriceBetween(min, max, Sort.by(Sort.Order.asc("price")));
        } else if (min != null) {
            return productRepository.findByPriceGreaterThanEqual(min, Sort.by(Sort.Order.asc("price")));
        } else {
            return productRepository.findByPriceLessThanEqual(max, Sort.by(Sort.Order.asc("price")));
        }
        return products;
    }

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return product;
    }

}
