package e.commerce.productservice.serviceimpl;

import e.commerce.productservice.mapper.ProductMapper;
import e.commerce.productservice.repository.ProductRepository;
import e.commerce.productservice.rest.response.ProductResponse;
import e.commerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
