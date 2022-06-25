package ch.egli.kitmoderator.dto;

import java.util.Date;

public class AbwesenheitCreateDto {
	public Date fromDate;
	public Date toDate;

	public String reason;
	public String comment;

	public String[] childrenIds;
}
