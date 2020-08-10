package bookStore.control;

import bookStore.service.SendMail;
import bookStore.model.User;
import bookStore.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    //2.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //2.1.
            request.getRequestDispatcher("BookStore/register.jsp").forward(request, response);
        } else {
            //2.2.
            request.getRequestDispatcher("").forward(request, response);
        }
    }

    //5.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // từ web.xml để lấy các thông tin để gửi mail
        ServletContext context = getServletContext();
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String userName = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        response.setCharacterEncoding("UTF-8");

        // các thông tin lấy từ form người dùng nhập
        String email = getParameter(request, "email");
        String password = getParameter(request, "pass");

        // Thông tin để người dùng active tài khoản
        String message = "http://localhost:8080/BookStore/activeAccount?userName="+email;
        String subject = "[Best store] Active account";

        UserService userService = new UserService();
        try {
            //5.1 kiểm tra email đã được sử dụng hay chưa
            if (!userService.checkUserEmailExist(email)) {
                try {
                    //6. gửi mail đường link để người dùng active account
                    SendMail.sendEmail(host, port, userName, pass, email, message, subject);
                    //7. tạo đối tượng người dùng
                    User user = new User(email, password);
                    //8. add vào database
                    userService.addUser(user);
                    //9. thông báo người dùng vào check mail
                    request.setAttribute("userName", email);
                    response.sendRedirect("register?info=Please check your email!");
                } catch (Exception  e) {
                    //6.1. báo không thể gửi mail
                    response.sendRedirect("register?error=Can't send mail.");
                }
            } else {
                //5.2. Báo email đã được sử dụng
                response.sendRedirect("register?error=Your email is already in used");
            }
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getParameter(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName) == null ? "" : request.getParameter(parameterName).trim();
    }
}
