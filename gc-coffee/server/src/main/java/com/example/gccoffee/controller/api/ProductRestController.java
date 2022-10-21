package com.example.gccoffee.controller.api;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public List<Product> productList(@RequestParam Optional<Category> category) {  // 필요하면 product 에 RequestParam 을 추가해서 카테고리별로 조회하게 할 수 있다.
        return category
                .map(productService::getProductsByCategory) // 카테고리가 만약에 존재하면 map 처리를 해서 productService 의 getproductcategory 로
                .orElse(productService.getAllProducts());  // 아니면 product service 의 전체 product 가 반환되게 처리할 수 있다.
    }

}
