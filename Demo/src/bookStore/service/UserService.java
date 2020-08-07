package bookStore.service;

import bookStore.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface UserService {
    public void addUser(User user) throws SQLException, ClassNotFoundException;
    boolean checkUserEmailExist(String email) throws SQLException, ClassNotFoundException;
}
