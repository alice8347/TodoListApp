package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TODOUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Todouser.findAll", query="SELECT t FROM Todouser t")
public class Todouser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	private String password;

	//bi-directional many-to-one association to Todolist
	@OneToMany(mappedBy="todouser")
	private List<Todolist> todolists;

	public Todouser() {
	}
	
	public Todouser(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todolist> getTodolists() {
		return this.todolists;
	}

	public void setTodolists(List<Todolist> todolists) {
		this.todolists = todolists;
	}

	public Todolist addTodolist(Todolist todolist) {
		getTodolists().add(todolist);
		todolist.setTodouser(this);

		return todolist;
	}

	public Todolist removeTodolist(Todolist todolist) {
		getTodolists().remove(todolist);
		todolist.setTodouser(null);

		return todolist;
	}

}