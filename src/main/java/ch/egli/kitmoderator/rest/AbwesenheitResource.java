package ch.egli.kitmoderator.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ch.egli.kitmoderator.model.Abwesenheit;
import ch.egli.kitmoderator.model.Child;
import ch.egli.kitmoderator.repo.AbwesenheitRepository;
import ch.egli.kitmoderator.repo.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/abwesenheit")
public class AbwesenheitResource {

	@Autowired
	AbwesenheitRepository repo;

	@PostMapping("/")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<Abwesenheit> createAbwesenheit(@RequestBody Abwesenheit abwesenheit) {
		abwesenheit.setId(UUID.randomUUID().toString());
		abwesenheit.setCreated(new Date());
		abwesenheit.setUpdated(new Date());
		var saved = repo.save(abwesenheit);
		return new HttpEntity<>(saved);
	}

	@GetMapping("/all")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<List<Abwesenheit>> getAll() {
		var res = repo.findAll();
		var result =
				StreamSupport.stream(res.spliterator(), false)
						.collect(Collectors.toList());
		return new HttpEntity<>(result);
	}

}
