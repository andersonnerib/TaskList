package br.com.abneri.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Invalid title!")
	@Size(min = 2, max = 60, message="Lenght must be between 2 and 60!")
	@Column(nullable = false, length = 60)
	private String title;
	
	@NotBlank(message="Invalid description!")
	@Column(nullable = false)
	private String description;

	@NotBlank
	@Column(nullable = false)
	private String concluded;

	@ManyToOne
	@JoinColumn(name = "id_tasklist_fk")
	private TaskList tasklist;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getConcluded() {
		return concluded;
	}

	public void setConcluded(String concluded) {
		this.concluded = concluded;
	}
	
	public TaskList getTasklist() {
		return tasklist;
	}

	public void setTasklist(TaskList tasklist) {
		this.tasklist = tasklist;
	}
}
