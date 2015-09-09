import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Todouser;
import tools.DBUtil;


public class UserDB {
	public static Todouser select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todouser t";
		TypedQuery<Todouser> q = em.createQuery(query, Todouser.class);
		try {
			Todouser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Todouser selectByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todouser t WHERE t.name = '" + name + "'";
		TypedQuery<Todouser> q = em.createQuery(query, Todouser.class);
		try {
			Todouser user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Todouser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
}