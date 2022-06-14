package ch.egli.kitmoderator.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ch.egli.kitmoderator.model.Child;
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
@RequestMapping("/api/v1/child")
public class ChildResource {

	@Autowired
	ChildRepository repo;

	@PostMapping("/")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<Child> createChild(@RequestBody Child child) {
		child.setId(UUID.randomUUID().toString());
		child.setCreated(new Date());
		child.setUpdated(new Date());
		var saved = repo.save(child);
		return new HttpEntity<>(saved);
	}

	@GetMapping("/all")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<List<Child>> getAll() {
		var res = repo.findAll();
		var result =
				StreamSupport.stream(res.spliterator(), false)
						.collect(Collectors.toList());
		return new HttpEntity<>(result);
	}

	@GetMapping("/{identifier}")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<Child> get(@PathVariable("identifier") String id) {
		var child = repo.findById(id).orElseThrow(() -> new NullPointerException("child not found"));
		return new HttpEntity<>(child);
	}

}
