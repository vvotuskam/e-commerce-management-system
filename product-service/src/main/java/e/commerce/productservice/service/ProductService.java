package e.commerce.productservice.service;

import e.commerce.productservice.rest.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProducts();
}
