package ch.egli.kitmoderator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Child extends Persistent{

	@Column
	private String name;

	@Column
	private String lastname;

	@Column
	private Date birthdate;

	@Column
	private String street;

	@Column
	private String city;

	@Column
	private String zip;

	@Column
	private String owner;
}
