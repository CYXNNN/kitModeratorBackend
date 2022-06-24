package ch.egli.kitmoderator.rest;

import java.util.Date;
import java.util.UUID;

import ch.egli.kitmoderator.model.Kita;
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
@RequestMapping("/api/v1/kita")
public class KitaResource {

	@Autowired
	private KitaRepository repo;

	@PostMapping("/")
	@CrossOrigin
	public HttpEntity<Kita> createKita(@RequestBody Kita kita) {
		kita.setId(UUID.randomUUID().toString());
		kita.setCreated(new Date());
		kita.setUpdated(new Date());
		Kita saved = repo.save(kita);
		return new HttpEntity<>(saved);
	}

	@GetMapping("/{identifier}")
	@CrossOrigin
	public HttpEntity<Kita> get(@PathVariable("identifier") String id) {
		Kita kita = repo.findById(id).orElseThrow(() -> new NullPointerException("kita not found"));
		return new HttpEntity<>(kita);
	}

}
