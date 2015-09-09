import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Todolist;
import tools.DBUtil;


public class ListDB {
	public static Todolist select() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todolist t";
		TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
		try {
			Todolist list = q.getSingleResult();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Todolist selectByListId(long listId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todolist t WHERE t.id = " + listId;
		TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
		try {
			Todolist list = q.getSingleResult();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static List<Todolist> selectByUserId(long userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String query = "SELECT t FROM Todolist t WHERE t.todouser.id = " + userId;
		TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
		try {
			List<Todolist> list = q.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void insert(Todolist list) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(list);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(Todolist list) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(list));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
