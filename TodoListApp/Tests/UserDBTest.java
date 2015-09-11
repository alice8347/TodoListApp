import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Todouser;

import org.junit.Test;

import tools.DBUtil;


public class UserDBTest {

	@Test
	public void test_selectuser() {
		System.out.println("Test if the correct user is selected using user name.");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todouser t WHERE t.name = 'Test'";
		TypedQuery<Todouser> q = em.createQuery(query, Todouser.class);
		Todouser user = q.getSingleResult();
		assertTrue(user.getId() == 1);
		assertTrue(user.getPassword().equals("test"));
		em.close();
	}

}
