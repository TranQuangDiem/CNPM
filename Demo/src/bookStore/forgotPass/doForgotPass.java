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
import java.sql.Time;
import java.util.regex.Pattern;


@WebServlet({"/BookStoredoForgotPass"})
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
            //4.lấy email người dùng nhập
            String email = request.getParameter("email") == null ? "" : request.getParameter("email");
            //tạo mã xác thực
            int code = (int) Math.floor(((Math.random() * 899999) + 10000000));

            String errmail = "";
            String regexMail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern paMail = Pattern.compile(regexMail);
            boolean checkMail = false;
            //kiểm tra email đã được đăng ký chưa
            String sqlcheckExitsMail = "SELECT `user`.email FROM `user` WHERE email=?";
            PreparedStatement psMail = DBConnect.getPreparedStatement(sqlcheckExitsMail);
            psMail.setString(1, email);
            ResultSet rsMail = psMail.executeQuery();
            boolean existEmail = rsMail.next();
            psMail.setString(1, email);
            //người dùng nhập email đúng cú pháp không
            if (paMail.matcher(email).matches() == false) {
                errmail = "Email của bạn không hợp lê";
            } else if (existEmail == false) {//email người dùng nhập không được đăng ký trong hệ thống
                errmail = "Email của bạn không tồn tại trong hệ thống chúng tôi";
            } else {
                errmail = "Chúng tôi đã gửi mail cho bạn";
                checkMail = true;
            }
            if (checkMail == true) {//Nếu email đã được đăng ký trong tài khoản
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                java.sql.Time time = new Time(millis);
                //update lại thông tin email đó
                String sqlUpdateforgot = "UPDATE test.user SET user.maxacthuc=?, user.ngaytao=? ,user.gio =? WHERE user.email=?";
                PreparedStatement psUpdateforgot = DBConnect.getPreparedStatement(sqlUpdateforgot);
                psUpdateforgot.setInt(1, code);
                psUpdateforgot.setDate(2, date);
                psUpdateforgot.setTime(3, time);
                psUpdateforgot.setString(4,email);
                psUpdateforgot.executeUpdate();
                String mess = "BookStore/changePass.jsp" + System.lineSeparator() + "Mã xác thực của bạn là    " + code;
                //gửi mã xác thực về email người dùng nhập
                SendMail.sendEmail(host, port, user, pass, email, mess);
          
                response.sendRedirect("BookStore/changePass.jsp");

            }else {//người dùng nhập sai email trả về lỗi
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
