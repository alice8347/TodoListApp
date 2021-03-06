import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Todolist;
import model.Todouser;

/**
 * Servlet implementation class addListServlet
 */
@WebServlet("/addListServlet")
public class addListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String addListMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("addListSub") != null) {
			Todolist newList = new Todolist();
			String description = request.getParameter("description");
			long status = Long.parseLong(request.getParameter("status"));
			String priority = request.getParameter("priority");
			newList.setDescription(description);
			newList.setTodostatus(StatusDB.selectById(status));
			newList.setListpriority(priority);
			newList.setTodouser((Todouser) session.getAttribute("user"));
			
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			
			try {
				Date dueDate = formatter.parse(request.getParameter("dueDate"));
				newList.setDuedate(dueDate);
				
				if (!request.getParameter("completionDate").isEmpty()) {
					Date dateCompleted = formatter.parse(request.getParameter("completionDate"));
					newList.setDatecompleted(dateCompleted);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ListDB.insert(newList);
			response.sendRedirect("/TodoListApp/todoListServlet");
		}
	}

}
