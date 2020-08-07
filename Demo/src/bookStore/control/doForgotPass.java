package bookStore.forgotPass;



import bookStore.DBConnect;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;


@WebServlet("/BookStoredoForgotPass")
public class doForgotPass extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            user = context.getInitParameter("user");
            pass = context.getInitParameter("pass");

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //4.hệ thống lấy email người dùng nhập vào
            String email = request.getParameter("email") == null ? "" : request.getParameter("email");
            //tạo mã xác thực
            int code = (int) Math.floor(((Math.random() * 899999) + 10000000));

            String errmail = "";
            String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern paMail = Pattern.compile(regexMail);
            boolean checkMail = false;
            if (paMail.matcher(email).matches() == true) {
                String sqlcheckExitsMail = "SELECT `user`.email FROM `user` WHERE email=?";
                //5. hệ thống truy xuất đến database user
                PreparedStatement psMail = DBConnect.getPreparedStatement(sqlcheckExitsMail);
                psMail.setString(1, email);
                //6. database user trả về kết quả
                ResultSet rsMail = psMail.executeQuery();
                boolean existEmail = rsMail.next();
                psMail.setString(1, email);
                //7.1 Hệ thống kiểm tra email không hợp lệ
                //người dùng nhập email đúng cú pháp không
                if (paMail.matcher(email).matches() == false) {
                    errmail = "Email của bạn không hợp lê";
                }else if (email.length()>50){
                    errmail = "Email nhập vào quá dài";
                }
                else if (existEmail == false) {//email người dùng nhập không được đăng ký trong hệ thống
                    errmail = "Email của bạn không tồn tại trong hệ thống chúng tôi";
                }
                else {
                    checkMail = true;
                }
            }else {
                errmail="email của bạn không hợp lệ";
            }
            //7. Hệ thống kiểm tra email hợp lệ
            if (checkMail == true) {//Nếu email đã được đăng ký trong tài khoản
                //lưu lại mã xác thưc ,ngày, giờ của email xuống database
                String sqlUpdateforgot = "UPDATE test.user SET user.maxacthuc=?, user.ngaytao=LOCALTIMESTAMP WHERE user.email=?";
                PreparedStatement psUpdateforgot = DBConnect.getPreparedStatement(sqlUpdateforgot);
                psUpdateforgot.setInt(1, code);
                psUpdateforgot.setString(2,email);
                psUpdateforgot.executeUpdate();
		String sub = "You have forgotten your password ? ";
                String mess = "access link http://localhost:8080/Demo/BookStore/changePass.jsp" + System.lineSeparator() +"Your authentication code is: " + code + System.lineSeparator() + "Validation codes are valid for 30 minutes from now";
                //8. Hệ thống gửi mã xác thực về email người dùng nhập
                SendMail.sendEmail(host, port, user, pass, email, mess, sub);
                //10.Hệ thống hiển thị trang changePass
                response.sendRedirect("BookStore/changePass.jsp");

            }else {//8.1. Hệ thống hiển thị lỗi ở trang forgotPassword.
                request.setAttribute("email", email);
                request.setAttribute("errmail", errmail);
                request.getRequestDispatcher("BookStore/ForgotPassword.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
