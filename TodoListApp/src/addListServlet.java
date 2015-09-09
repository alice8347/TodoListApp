import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Todolist;

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
		if (request.getParameter("addListSub") != null) {
			Todolist newList = new Todolist();
			String description = request.getParameter("description");
			String status = request.getParameter("status");
			String priority = request.getParameter("priority");
			newList.setDescription(description);
			newList.setTodostatus(StatusDB.selectByName(status));
			newList.setListpriority(priority);
			
			DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
			
			try {
				Date dueDate = format.parse(request.getParameter("dueDate"));
				newList.setDuedate(dueDate);
				if (request.getParameter("completionDate") != null) {
					Date dateCompleted = format.parse(request.getParameter("completionDate"));
					newList.setDatecompleted(dateCompleted);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ListDB.insert(newList);
			response.sendRedirect("/todoListServlet");
		}
	}

}
