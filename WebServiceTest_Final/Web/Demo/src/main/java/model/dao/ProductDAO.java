package model.dao;

import model.bean.Product;
import vn.fit.nlu.gk.db.JDBIConnector;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDAO {
    //Tất cả các sản phẩm
    public static List<Product> getAll() {
        List<Product> product = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("select * from product")
                        .mapToBean(Product.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        return product;
    }

    public static Product getProductById(final String id) {
        Optional<Product> product = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("select * from product where id= :id")
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .stream()
                        .findFirst()
        );
        return product.isEmpty() ? null : product.get();
    }

    public static int getNumberRateStarsByUser(int productId, int userId) {
        try {
            return JDBIConnector.get().withHandle(handle ->
                    handle.createQuery("select starRatings from rate where userId=? and productId=?")
                            .bind(0, userId)
                            .bind(1, productId)
                            .mapTo(Integer.class).one());
        } catch (Exception e) {
            return 0;
        }
    }
}

