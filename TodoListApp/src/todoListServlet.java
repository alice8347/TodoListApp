import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Todolist;
import model.Todouser;

/**
 * Servlet implementation class todoListServlet
 */
@WebServlet("/todoListServlet")
public class todoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String todoListMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public todoListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		todoListMsg = "";
		HttpSession session = request.getSession();
		if (request.getParameter("delId") != null) {
			ListDB.delete(ListDB.selectByListId(Long.parseLong(request.getParameter("delId"))));
		}
		todoListMsg += displayList(session);
		request.setAttribute("todoListMsg", todoListMsg);
		getServletContext().getRequestDispatcher("/todoList.jsp").forward(request, response);
	}

	private static String displayList(HttpSession session) {
		String todoListMsg = "";
		if (session.getAttribute("user") != null) {
			Todouser user = (Todouser) session.getAttribute("user");
			List<Todolist> list = ListDB.selectByUserId(user.getId());
			todoListMsg += "<div class=\"container\">";

			if ((list != null) && (!list.isEmpty())) {
				todoListMsg += "<table class=\"table table-striped\"><thead><tr><th>#</th><th>Description</th><th>Date Completed</th><th>Due Date</th><th>Status</th><th>Priority</th><th></th><th></th></tr></thead><tbody>";
				
				DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getDatecompleted() == null) {
						todoListMsg += "<tr><td>" + (i + 1) + "</td><td>" + list.get(i).getDescription() + "</td><td></td><td>" + formatter.format(list.get(i).getDuedate()) + "</td><td>" + list.get(i).getTodostatus().getName() + "</td><td>" + list.get(i).getListpriority() + "</td><td><a href=\"editListServlet?listId=" + list.get(i).getId() + "\">Edit</a></td><td><a href=\"todoListServlet?delId=" + list.get(i).getId() + "\">Delete</a></td></tr>";
					} else {
						todoListMsg += "<tr><td>" + (i + 1) + "</td><td>" + list.get(i).getDescription() + "</td><td>" + formatter.format(list.get(i).getDatecompleted()) + "</td><td>" + formatter.format(list.get(i).getDuedate()) + "</td><td>" + list.get(i).getTodostatus().getName() + "</td><td>" + list.get(i).getListpriority() + "</td><td><a href=\"editListServlet?listId=" + list.get(i).getId() + "\">Edit</a></td><td><a href=\"todoListServlet?delId=" + list.get(i).getId() + "\">Delete</a></td></tr>";
					}
				}
				
				todoListMsg += "</tbody></table>";
			} else {
				todoListMsg += "<div class=\"alert alert-info\"><strong>Info!</strong> Your to-do list is empty.</div>";
			}
			
			todoListMsg += "</div>";
		} else {
			todoListMsg += "<div class=container><div class=\"alert alert-info\"><strong>Reminder!</strong> Please log in to use the application.</div></div>";
		}
		
		return todoListMsg;
	}

}
