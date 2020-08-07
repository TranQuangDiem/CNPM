package bookStore.service;

import bookStore.DBConnect;
import bookStore.model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = DBConnect.getPreparedStatement("INSERT `user` (userName, `password`, name,image, `group`, active) VALUES (?, ?,?,'BookStore/img/avatar.png',1,0)");
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserName());
        ps.executeUpdate();
    }

    @Override
    public boolean checkUserEmailExist(String email) throws SQLException , ClassNotFoundException{
        PreparedStatement ps = DBConnect.getPreparedStatement("Select * from `user` where userName=? ");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.last();
        if (rs.getRow()>=1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl u = new UserServiceImpl();
        System.out.println(u.checkUserEmailExist("thuyphuong26"));
    }
}