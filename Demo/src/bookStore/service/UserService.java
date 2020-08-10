package bookStore.service;

import bookStore.DBConnect;
import bookStore.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    //
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = DBConnect.getPreparedStatement("INSERT `user` (`email`, `password`, name, `group`, active) VALUES (?, ?, ?, 1, 0)");
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserName());
        ps.executeUpdate();
    }

    public boolean checkUserEmailExist(String email) throws SQLException , ClassNotFoundException{
        PreparedStatement ps = DBConnect.getPreparedStatement("Select * from `user` where `email`=? and active=1 ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }

    //
    public void activeAccount(String userName) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = DBConnect.getPreparedStatement("UPDATE `user` SET active=1 WHERE `email`=?");
        ps.setString(1,userName);
        ps.executeUpdate();
    }

    //
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService u = new UserService();
        System.out.println(u.checkUserEmailExist("thuyphuong261299@gmail.com"));
    }
}