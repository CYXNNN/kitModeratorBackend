package ch.egli.kitmoderator.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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

}
