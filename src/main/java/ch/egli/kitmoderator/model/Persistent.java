package ch.egli.kitmoderator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Persistent {

	@Id
	@Column(length = 100)
	private String id;

	@Column
	private Date created;

	@Column
	private Date updated;

}
