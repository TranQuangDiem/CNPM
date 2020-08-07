package bookStore.model;

import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String image;
    private Date dob;
    private String address;
    private int gentle;
    private int group;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
