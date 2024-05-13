package model.service;

import model.bean.User;
import model.dao.UserDAO;

import java.util.List;

public class UserService {
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    public User getUserById(String id) {
        return UserDAO.getUserById(id);
    }
}
