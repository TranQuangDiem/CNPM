package bookStore.control;

import bookStore.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/activeAccount")
public class ActiveAccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        UserService userService = new UserService();
        try {
		//11.active tài khoản
            userService.activeAccount(userName);
		// chuyển về trang đăng nhập
            response.sendRedirect("login");
        } catch (SQLException | ClassNotFoundException ignore){
        }
    }
}
