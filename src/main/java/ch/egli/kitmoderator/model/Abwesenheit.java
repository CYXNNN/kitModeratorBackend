package ch.egli.kitmoderator.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Abwesenheit extends Persistent{

	@Column
	private String reason;

	@Column
	private Date fromDate;

	@Column
	private Date toDate;

	@Column
	private String owner;

	@ManyToOne
	@JoinColumn(name="abwesenheit_id")
	@JsonIgnore
	private Abwesenheit abwesenheit;

}
