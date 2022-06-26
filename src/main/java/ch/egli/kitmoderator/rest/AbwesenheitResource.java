package ch.egli.kitmoderator.rest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ch.egli.kitmoderator.dto.AbwesenheitCreateDto;
import ch.egli.kitmoderator.model.Abwesenheit;
import ch.egli.kitmoderator.model.Child;
import ch.egli.kitmoderator.repo.AbwesenheitRepository;
import ch.egli.kitmoderator.repo.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/abwesenheit")
public class AbwesenheitResource {

	@Autowired
	AbwesenheitRepository repo;

	@Autowired
	ChildRepository childRepo;

	@PostMapping("/")
	@CrossOrigin
	public HttpEntity<Abwesenheit> createAbwesenheit(@RequestBody AbwesenheitCreateDto dto) {
		Abwesenheit abwesenheit = new Abwesenheit();
		abwesenheit.setId(UUID.randomUUID().toString());
		abwesenheit.setCreated(new Date());
		abwesenheit.setUpdated(new Date());

		abwesenheit.setComment(dto.comment);
		abwesenheit.setReason(dto.reason);
		abwesenheit.setFromDate(dto.fromDate);
		abwesenheit.setToDate(dto.toDate);

		List<Child> children = Arrays.stream(dto.children).map(id -> childRepo.findById(id).orElseThrow(NullPointerException::new))
				.collect(Collectors.toList());

		abwesenheit.setChildren(children);

		Abwesenheit saved = repo.save(abwesenheit);
		return new HttpEntity<>(saved);
	}

	@GetMapping("/all")
	@CrossOrigin
	public HttpEntity<List<Abwesenheit>> getAll() {
		List<Abwesenheit> result =
				StreamSupport.stream( repo.findAll().spliterator(), false)
						.collect(Collectors.toList());
		return new HttpEntity<>(result);
	}

}
