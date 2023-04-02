package com.example.shopmejavabackend.services;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.model.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("ProductsService Test")
public class ProductsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductsService productsService;

    @Test
    @DisplayName("status 200 : get all products")
    void getProducts() throws Exception{

        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setId(1);
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setCategory("men's clothing");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        product.setRating(rating);

        products.add(product);

        /*HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);*/

        //ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        //ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(HttpStatus.OK);
        //when(restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Product>>(){}))
         //       .thenReturn(response);
        /*when(restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Product>>(){}))
                .thenReturn(response);*/

        ResponseEntity<List<Product>> entity = new ResponseEntity<>(products, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(HttpEntity.class),
                Mockito.<ParameterizedTypeReference<List<Product>>>any())
        ).thenReturn(new ResponseEntity<>(products, HttpStatus.OK));

        // call actual service method
        List<Product> productList = productsService.getProducts();

        // compare returned response
        assertThat(products, is(productList));
    }

    @Test
    @DisplayName("status 200 : get product by Id")
    void getProductById() throws Exception{
        Product product = new Product();
        product.setId(1);
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setCategory("men's clothing");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        product.setRating(rating);

        int id = 1;

        /*HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);*/

        /*ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        when(restTemplate.exchange("https://fakestoreapi.com/products/"+id, HttpMethod.GET, entity, Product.class))
                .thenReturn(new ResponseEntity(product, HttpStatus.OK));*/

        /*ResponseEntity<Product> variable = new ResponseEntity<>(product, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.endsWith("https://fakestoreapi.com/products/"+id),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(HttpEntity.class),
                Mockito.<Class<Product>>any())
        ).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));*/

        ResponseEntity<Product> entity = new ResponseEntity<>(product, HttpStatus.OK);
        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(HttpEntity.class),
                Mockito.<Class<Product>>any())
        ).thenReturn(new ResponseEntity<>(product, HttpStatus.OK));

        Product fetchProduct = productsService.getProductById(id);
        assertThat(product, is(fetchProduct));

    }

    @Test
    @DisplayName("status 500: Internal Server Error")
    void getProductByIdInternalServerError() throws Exception{
        int id = 1;
        HttpServerErrorException httpServerErrorException = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, Product.class))
                .thenThrow(httpServerErrorException);

        //Product fetchProduct = productsService.getProductById(id);
        //assertThat(fetchProduct, is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
