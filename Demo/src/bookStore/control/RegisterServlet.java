package bookStore.control;

import bookStore.service.SendMail;
import bookStore.model.User;
import bookStore.service.UserService;
import bookStore.service.UserServiceImpl;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("BookStore/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String userName = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        response.setCharacterEncoding("UTF-8");

        String email = getParameter(request, "email");
        String password = getParameter(request, "pass");
        String retypePassword = getParameter(request, "retypePass");
        String checkbox = getParameter(request,"checkbox");

        // Thông tin để người dùng active tài khoản
        String subject = "";
        String message = "";

        UserService userService = new UserServiceImpl();
        try {
            //
            if (checkEmail(email) && checkPassword(password, retypePassword) && checkCheckBox(checkbox)) {
                //
                User user = new User(email, password);
                try {
                    //
                    SendMail.sendEmail(host, port, userName, pass, email, message, subject);
                    userService.addUser(user);
                    request.setAttribute("userName", email);
                    response.sendRedirect("register?info=Please check your email!");
                } catch (Exception  e) {
                    //
                    response.sendRedirect("register?error=Can't send mail.");
                }
            } else {
                //
                response.sendRedirect("register?error=Email or password is wrong");
            }
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//
    private boolean checkCheckBox(String checkbox) {
        return checkbox.equals("on");
    }

//
    private boolean checkPassword(String password, String retypePassword) {
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") && password.equals(retypePassword);
    }

//
    boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();
        if (email.matches("^[A-Za-z0-9.-_]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") && !userService.checkUserEmailExist(email)){
            return true;
        } else
        return false;
    }

    private String getParameter(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName) == null ? "" : request.getParameter(parameterName).trim();
    }
}
