import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Todouser;


/**
 * Servlet implementation class User
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginErr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("logout").equals("true")) {
			session.removeAttribute("user");
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loginErr = "";
		HttpSession session = request.getSession();
		if (request.getParameter("LoginSub") != null) {
			Todouser user = UserDB.selectByName(request.getParameter("name"));
			if (user == null) {
				loginErr += "<div class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> User does not exist. Please Sign up.</div></div>";
				request.setAttribute("loginErr", loginErr);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				if (request.getParameter("password").equals(user.getPassword())) {
					session.setAttribute("user", user);
					response.sendRedirect("/TodoListApp/todoListServlet");
				} else {
					loginErr += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Password is incorrect.</div></div>";
					request.setAttribute("loginErr", loginErr);
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		}
		
		if (request.getParameter("SignupSub") != null) {
			Todouser user = UserDB.selectByName(request.getParameter("name"));
			if (user != null) {
				loginErr += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> User already exists.</div></div>";
				request.setAttribute("loginErr", loginErr);
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				Todouser newUser = new Todouser(request.getParameter("name"), request.getParameter("password"));
				UserDB.insert(newUser);
				session.setAttribute("user", newUser);
				response.sendRedirect("/TodoListApp/todoListServlet");
			}
		}
	}
	
}