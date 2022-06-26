package ch.egli.kitmoderator.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

import ch.egli.kitmoderator.model.Abwesenheit;
import ch.egli.kitmoderator.model.Persistent;

public class AbwesenheitCreateDto {

	public AbwesenheitCreateDto(){

	}

	public AbwesenheitCreateDto(Abwesenheit a) {
		this.id = a.getId();
		this.fromDate = a.getFromDate();
		this.toDate = a.getToDate();
		this.reason = a.getReason();
		this.comment = a.getComment();

		this.created = a.getCreated();
		this.updated = a.getUpdated();

		this.children = a.getChildren().stream()
				.map(Persistent::getId)
				.toArray(String[]::new);
	}

	public String id;
	public Date created;
	public Date updated;

	public LocalDateTime fromDate;
	public LocalDateTime toDate;

	public String reason;
	public String comment;

	public String[] children;
}
