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

/**
 * Servlet implementation class editListServlet
 */
@WebServlet("/editListServlet")
public class editListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dueDate;
	private String completionDate;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dueDate = "";
		completionDate = "";
		HttpSession session = request.getSession();
		
		if (request.getParameter("listId") != null) {
			Todolist list = ListDB.selectByListId(Long.parseLong(request.getParameter("listId")));
			session.setAttribute("list", list); 
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			
			dueDate = formatter.format(list.getDuedate());
			
			if (list.getDatecompleted() != null) {
				completionDate = formatter.format(list.getDatecompleted());
			}
			
			request.setAttribute("dueDate", dueDate);
			request.setAttribute("completionDate", completionDate);
		}
		
		getServletContext().getRequestDispatcher("/editList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("editListSub") != null) {
			Todolist list = (Todolist) session.getAttribute("list");
			String description = request.getParameter("description");
			long status = Long.parseLong(request.getParameter("status"));
			String priority = request.getParameter("priority");
			list.setDescription(description);
			list.setTodostatus(StatusDB.selectById(status));
			list.setListpriority(priority);
			
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			
			try {
				Date dueDate = formatter.parse(request.getParameter("dueDate"));
				list.setDuedate(dueDate);
				
				if (request.getParameter("completionDate") != null) {
					Date dateCompleted = formatter.parse(request.getParameter("completionDate"));
					list.setDatecompleted(dateCompleted);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ListDB.update(list);
			response.sendRedirect("/TodoListApp/todoListServlet");
		}
	}

}
