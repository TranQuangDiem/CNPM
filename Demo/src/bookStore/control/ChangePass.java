package bookStore.control;

import bookStore.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/BookStoreChangePass")
public class ChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            //12.Hệ thống lấy thông tin nhập vào: email,pass,nhập lại pass,mã xác thực.
            String email = request.getParameter("email") == null ? "" : request.getParameter("email").trim();
            String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass").trim();
            String repass = request.getParameter("repass") == null ? "" : request.getParameter("repass").trim();
            String maxacthuc = request.getParameter("maxacthuc") == null ? "" : request.getParameter("maxacthuc").trim();

            String errmatkhau = "";
            String errmatkhaucon = "";
            String errmail = "";
            String errmaxacthuc = "";

            boolean checkMatKhau = false;
            boolean checkMatKhauCon = false;
            boolean checkMail = false;
            boolean checkMaxacnhan = false;
            String regexPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
            String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern paPass = Pattern.compile(regexPass);
            Pattern paMail = Pattern.compile(regexMail);
            paPass.matcher(pass).matches();
            paMail.matcher(email).matches();
            String sql = "SELECT * FROM forgotpass WHERE maxacthuc=? and LOCALTIMESTAMP-ngaytao<1800";
            //13.Hệ thống truy xuất xuông  database
            PreparedStatement s = DBConnect.getPreparedStatement(sql);
            s.setString(1,maxacthuc);
            //14. database trả về kết quả
            ResultSet rs = s.executeQuery();
            boolean exist = rs.next();

            //kiểm tra new password có hợp lệ không
            if (pass.length() < 8|| pass.length()>25) {
                errmatkhau = "Mật khẩu phải hơn 8 và nhỏ hơn 25 ký tự";
            } else if (paPass.matcher(pass).matches() == false) {
                errmatkhau = "Phải có ít nhật 1 ký tự thường,hoa,số,ký tự đặc biệt";
            } else {
                errmatkhau = "";
                checkMatKhau = true;
            }
            //kiểm tra new password có trùng khớp với enter a new password không
            if (!pass.equals(repass)) {
                errmatkhaucon = "Mật khẩu không trùng khớp";
            } else {
                errmatkhaucon = "";
                checkMatKhauCon = true;
            }
            //kiểm tra email có hợp lệ không
            if (paMail.matcher(email).matches() == false) {
                errmail = "Email của bạn không hợp lê";
            }
            //kiểm tra mã xác thực có đúng không
            if (exist==false){
                errmaxacthuc="Mã xác thực đã hết hạn";
            }else {
                if(!email.equals(rs.getString("email"))) {
                    errmail ="Bạn nhập sai Email";
                }else {
                    checkMail = true;
                }
                if(!maxacthuc.equals(rs.getString("maxacthuc"))){
                    errmaxacthuc="Mã xác thực sai";
                }else {
                    checkMaxacnhan = true;
                }
            }



            request.setAttribute("errmatkhau", errmatkhau);
            request.setAttribute("errmatkhaucon", errmatkhaucon);
            request.setAttribute("errmail", errmail);
            request.setAttribute("errmaxacnhan", errmaxacthuc);
            request.setAttribute("email",email);
            //15. Hệ thống kiểm tra email, new password,enter a new password , verification code hợp lệ
            if (checkMail==true&&checkMatKhau==true&&checkMatKhauCon==true&&checkMaxacnhan==true){

                //16.Hệ thống update lại pass mới vào trong database
                String query = "UPDATE `user` SET `user`.password=? WHERE `user`.email=?";
                PreparedStatement ps = DBConnect.getPreparedStatement(query);
                ps.setString(1,pass);
                ps.setString(2,email);
                ps.executeUpdate();
                query = "DELETE FROM forgotpass WHERE email=?";
                PreparedStatement ps1 = DBConnect.getPreparedStatement(query);
                ps1.setString(1,email);
                ps1.executeUpdate();
                //17. Hệ thống chuyển sang trang login
                response.sendRedirect("BookStore/login.jsp");
            }else {//15.1. Hệ thống kiểm tra email, new password,enter a new password, verification code không hợp lệ
                //16.1 Hệ thống hiển thị  lỗi ở trang changePass.
                request.getRequestDispatcher("BookStore/changePass.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
