import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Todostatus;
import tools.DBUtil;


public class StatusDB {
	public static Todostatus select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todostatus t";
		TypedQuery<Todostatus> q = em.createQuery(query, Todostatus.class);
		try {
			Todostatus user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Todostatus selectById(long id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todostatus t WHERE t.id = " + id;
		TypedQuery<Todostatus> q = em.createQuery(query, Todostatus.class);
		try {
			Todostatus user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Todostatus selectByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todostatus t WHERE t.name = '" + name + "'";
		TypedQuery<Todostatus> q = em.createQuery(query, Todostatus.class);
		try {
			Todostatus user = q.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
}
