package ch.egli.kitmoderator.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kita extends Persistent {

	@Column
	private String name;

	@Column
	private String street;

	@Column
	private String city;

	@Column
	private String zip;

	@OneToMany(mappedBy="kita")
	List<Child> children;


}
