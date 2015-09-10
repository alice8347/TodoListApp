package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TODOLIST database table.
 * 
 */
@Entity
@Table(name="Todolist", schema="TESTDB")
@NamedQuery(name="Todolist.findAll", query="SELECT t FROM Todolist t")
public class Todolist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	private Date datecompleted;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date duedate;

	private String listpriority;

	//bi-directional many-to-one association to Todostatus
	@ManyToOne
	@JoinColumn(name="STATUSID")
	private Todostatus todostatus;

	//bi-directional many-to-one association to Todouser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Todouser todouser;

	public Todolist() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatecompleted() {
		return this.datecompleted;
	}

	public void setDatecompleted(Date datecompleted) {
		this.datecompleted = datecompleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getListpriority() {
		return this.listpriority;
	}

	public void setListpriority(String listpriority) {
		this.listpriority = listpriority;
	}

	public Todostatus getTodostatus() {
		return this.todostatus;
	}

	public void setTodostatus(Todostatus todostatus) {
		this.todostatus = todostatus;
	}

	public Todouser getTodouser() {
		return this.todouser;
	}

	public void setTodouser(Todouser todouser) {
		this.todouser = todouser;
	}

}