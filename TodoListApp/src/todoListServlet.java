import java.io.IOException;
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
		if (session.getAttribute("user") != null) {
			Todouser user = (Todouser) session.getAttribute("user");
			List<Todolist> list = ListDB.selectByUserId(user.getId());
			todoListMsg += "<div class=\"container\"><a href=\"addList.jsp\">Add New Task</a>";

			if ((list != null) && (!list.isEmpty())) {
				todoListMsg += "<table class=\"table table-striped\"><thead><tr><th>#</th><th>Description</th><th>Date Completed</th><th>Due Date</th><th>Status</th><th>Priority</th><th></th></tr></thead><tbody>";
				
				for (int i = 0; i < list.size(); i++) {
					todoListMsg += "<tr><td>" + i + "</td><td>" + list.get(i).getDescription() + "</td><td>" + list.get(i).getDatecompleted() + "</td><td>" + list.get(i).getDuedate() + "</td><td>" + list.get(i).getTodostatus().getName() + "</td><td>" + list.get(i).getListpriority() + "</td><td><a href=\"editListServlet?listId=" + list.get(i).getId() + "\">Edit</a><a href=\"todoListServlet?delId=" + list.get(i).getId() + "\">Delete</a></td></tr>";
				}
				
				todoListMsg += "</tbody></table>";
			}
			
			todoListMsg += "</div>";
		} else {
			todoListMsg += "<div class=container><div class=\"alert alert-info\"><strong>Reminder!</strong> Please log in to use the application.</div></div>";
		}
		
		if (request.getParameter("delId") != null) {
			ListDB.delete(ListDB.selectByListId(Long.parseLong(request.getParameter("delId"))));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
