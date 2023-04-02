package com.example.shopmejavabackend.services;

import com.example.shopmejavabackend.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductsService {
    RestTemplate restTemplate = new RestTemplate();
    public List<Product> getProducts() throws Exception{

        String PRODUCTS_URL = "http://fakestoreapi.com/products";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("user-agent", "Application");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<List<Product>> response =
                    restTemplate.exchange(
                            PRODUCTS_URL,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<Product>>(){});
            List<Product> products = response.getBody();
            return products;

        }catch (Exception e) {
            throw e;
        }
    }

    public Product getProductById(int id) throws Exception{
        String PRODUCT_URL = "http://fakestoreapi.com/products/"+id;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("user-agent", "Application");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Product> response = restTemplate
                    .exchange(PRODUCT_URL, HttpMethod.GET, entity, Product.class);
            Product product = response.getBody();
            return product;
        } catch (Exception e) {
            throw e;
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.InternalServerError ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
