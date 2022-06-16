package ch.egli.kitmoderator.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ch.egli.kitmoderator.model.Child;
import ch.egli.kitmoderator.model.Kita;
import ch.egli.kitmoderator.repo.ChildRepository;
import ch.egli.kitmoderator.repo.KitaRepository;
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

	@Autowired
	KitaRepository kitaRepo;

	@PostMapping("/")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<Child> createChild(@RequestBody Child child) {
		child.setId(UUID.randomUUID().toString());
		child.setCreated(new Date());
		child.setUpdated(new Date());
		Child saved = repo.save(child);
		return new HttpEntity<>(saved);
	}

	@GetMapping("/all")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<List<Child>> getAll() {
		List<Child> result =
				StreamSupport.stream(repo.findAll().spliterator(), false)
						.collect(Collectors.toList());
		return new HttpEntity<>(result);
	}

	@GetMapping("/{identifier}")
	@CrossOrigin("http://localhost:8100")
	public HttpEntity<Child> get(@PathVariable("identifier") String id) {
		Child child = repo.findById(id).orElseThrow(() -> new NullPointerException("child not found"));
		return new HttpEntity<>(child);
	}

	@GetMapping("/samples")
	@CrossOrigin("http://localhost:8100")
	public void samples() {

		Kita kita = new Kita();

		kita.setId(UUID.randomUUID().toString());
		kita.setCreated(new Date());
		kita.setUpdated(new Date());
		kita.setCity("Bern");
		kita.setStreet("Fliederweg 87");
		kita.setZip("3000");
		kita.setName("Any Kita");

		kitaRepo.save(kita);

		Child child = new Child();
		child.setId(UUID.randomUUID().toString());
		child.setCreated(new Date());
		child.setUpdated(new Date());
		child.setBirthdate(new Date());
		child.setCity("Bern");
		child.setKita(kita);
		child.setLastname("Egli");
		child.setName("Johannes");
		child.setOwner("parental-identifier");
		child.setStreet("Lustigweg 4");
		child.setZip("3000");

		repo.save(child);


		Child child2 = new Child();
		child2.setId(UUID.randomUUID().toString());
		child2.setCreated(new Date());
		child2.setUpdated(new Date());
		child2.setBirthdate(new Date());
		child2.setCity("Bern");
		child2.setKita(kita);
		child2.setLastname("Egli-Spahr");
		child2.setName("Hansli");
		child2.setOwner("parental-identifier");
		child2.setStreet("Lustigweg 4");
		child2.setZip("3000");

		repo.save(child2);




	}

}
