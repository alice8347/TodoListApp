import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Todolist;
import model.Todostatus;

import org.junit.Test;


public class ListTest {

	@Test
	public void test_displayList() {
		System.out.println("Test if the correct message is shown.");
		String todoListMsg = "";
		Todolist todolist = new Todolist();
		todolist.setId(1);
		todolist.setDescription("Test");
		todolist.setDuedate(new Date());
		Todostatus status = new Todostatus();
		status.setId(3);
		status.setName("Not Started Yet");
		todolist.setTodostatus(status);
		todolist.setListpriority("Medium");
		
		String answer = "<table class=\"table table-striped\"><thead><tr><th>#</th><th>Description</th><th>Date Completed</th><th>Due Date</th><th>Status</th><th>Priority</th><th></th><th></th></tr></thead><tbody>"
						+ "<tr><td>1</td><td>Test</td><td></td><td>09-11-2015</td><td>Not Started Yet</td><td>Medium</td><td><a href=\"editListServlet?listId=1\">Edit</a></td><td><a href=\"todoListServlet?delId=1\">Delete</a></td></tr>"
						+ "</tbody></table>";
		List<Todolist> list = new ArrayList<Todolist>();
		list.add(todolist);
		
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
		
		assertTrue(todoListMsg.equals(answer));
	}

}
