package ru.lakeevda.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductRepository productRepository;
    private Product product1;
    private Product product2;
    private Product product3;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Продукт 1");
        product1.setPrice(BigInteger.valueOf(1000));
        product1.setListId(1);
        product1.setPurchased(false);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Продукт 2");
        product2.setPrice(BigInteger.valueOf(2000));
        product2.setListId(1);
        product2.setPurchased(false);

        product3 = new Product();
        product3.setId(3L);
        product3.setName("Продукт 3");
        product3.setPrice(BigInteger.valueOf(3000));
        product3.setListId(2);
        product3.setPurchased(false);
    }

    @Test
    void getProduct() throws Exception {
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/{id}", product1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getProducts() throws Exception {
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));
        when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
        when(productRepository.findById(product3.getId())).thenReturn(Optional.of(product3));

        mockMvc.perform(get("/{id}/products", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void addProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setId(4L);
        newProduct.setName("Продукт 4");
        newProduct.setPrice(BigInteger.valueOf(4000));
        newProduct.setListId(2);
        newProduct.setPurchased(false);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk());
    }
}
