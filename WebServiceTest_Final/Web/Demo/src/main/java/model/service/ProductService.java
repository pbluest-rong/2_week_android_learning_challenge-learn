package model.service;

import model.bean.Product;
import model.dao.ProductDAO;

import java.util.List;

public class ProductService {
    public static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) instance = new ProductService();
        return instance;
    }

    public List<Product> getAll() {
        return ProductDAO.getAll();
    }

    public Product getProductById(String id) {
        return ProductDAO.getProductById(id);
    }

    public int getNumberRateStarsByUser(int productId, int userId) {
        return ProductDAO.getNumberRateStarsByUser(productId, userId);
    }
}
