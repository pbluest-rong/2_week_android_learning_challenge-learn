package model.dao;

import model.bean.User;
import vn.fit.nlu.gk.db.JDBIConnector;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAO {
    public static User getUserById(final String id) {
        Optional<User> user = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("select * from user where id= :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .stream()
                        .findFirst()
        );
        return user.isEmpty() ? null : user.get();
    }
}
