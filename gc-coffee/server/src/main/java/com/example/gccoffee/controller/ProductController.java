package com.example.gccoffee.controller;

import com.example.gccoffee.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  // 관리자가 웹 페이지에 접속하기 위한 뷰를 반환해주는 컨트롤러
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {  // 뷰 이름을 리턴해야 함
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("new-product") // getMapping 으로 new-Product 를 렌더링하기 위해서 메소드 생성
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")  // post 메서드를 처리할 수 있는 컨트롤러 필요
    public String newProduct(CreateProductRequest createProductRequest) { //productService 에 요청을 할 때 createProduct 로 처리해야 함
        productService.createProduct(
                createProductRequest.productName(),
                createProductRequest.category(),
                createProductRequest.price(),
                createProductRequest.description());
        return "redirect:/products";  // 뷰이기때문에 redirect 처리해야 함
    }
}
