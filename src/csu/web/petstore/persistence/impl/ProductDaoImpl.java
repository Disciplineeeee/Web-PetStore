package csu.web.petstore.persistence.impl;

import csu.web.petstore.domain.Product;
import csu.web.petstore.persistence.ProductDao;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        return List.of();
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        return List.of();
    }
}
