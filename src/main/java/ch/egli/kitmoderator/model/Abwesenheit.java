package ch.egli.kitmoderator.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Abwesenheit extends Persistent{

	@Column
	private String reason;

	@Column
	private String comment;

	@Column
	private LocalDateTime fromDate;

	@Column
	private LocalDateTime toDate;

	@Column
	@Enumerated(EnumType.STRING)
	private AbwesenheitStatus status;

	@Column
	private String owner = "parental-identifier";

	@ManyToMany
	@JoinTable(
			name = "abwesenheit_children",
			joinColumns = @JoinColumn(name = "abwesenheit_id"),
			inverseJoinColumns = @JoinColumn(name = "child_id"))
	private List<Child> children;

}
