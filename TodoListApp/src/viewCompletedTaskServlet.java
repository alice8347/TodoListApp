import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class viewCompletedTaskServlet
 */
@WebServlet("/viewCompletedTaskServlet")
public class viewCompletedTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String viewCompletedTaskMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewCompletedTaskServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		viewCompletedTaskMsg = "";
		HttpSession session = request.getSession();
		Todouser user = (Todouser) session.getAttribute("user");
		List<Todolist> completedList = ListDB.selectByUserIdStatus(user.getId(), 1);
		
		if ((completedList != null) && (!completedList.isEmpty())) {
			viewCompletedTaskMsg += "<div class=\"container\"><table class=\"table table-striped\"><thead><tr><th>#</th><th>Description</th><th>Date Completed</th><th>Due Date</th><th>Status</th><th>Priority</th></tr></thead><tbody>";
			
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			for (int i = 0; i < completedList.size(); i++) {
				viewCompletedTaskMsg += "<tr><td>" + (i + 1) + "</td><td>" + completedList.get(i).getDescription() + "</td><td>" + formatter.format(completedList.get(i).getDatecompleted()) + "</td><td>" + formatter.format(completedList.get(i).getDuedate()) + "</td><td>" + completedList.get(i).getTodostatus().getName() + "</td><td>" + completedList.get(i).getListpriority() + "</td></tr>";
			}
			
			viewCompletedTaskMsg += "</div></tbody></table>";
		} else {
			viewCompletedTaskMsg += "<div class=\"container\"><div class=\"alert alert-info\"><strong>Info!</strong> Your do not have any completed task.</div></div>";
		}
		
		request.setAttribute("viewCompletedTaskMsg", viewCompletedTaskMsg);
		getServletContext().getRequestDispatcher("/viewCompletedTask.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
