import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Todolist;

import org.junit.Test;

import tools.DBUtil;


public class ListDBTest {

	@Test
	public void test_completedtasks() {
		System.out.println("Test if selecting completed task works");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todolist t WHERE t.todouser.id = 1 AND t.todostatus.id = 1";
		TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
		List<Todolist> list = q.getResultList();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).getDescription().equals("Enroll in benefit programs"));
		assertTrue(list.get(1).getDescription().equals("To-do List Application"));
		em.close();
	}

}
