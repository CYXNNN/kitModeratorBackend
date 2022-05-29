package ch.egli.kitmoderator.rest;

import ch.egli.kitmoderator.model.Kita;
import ch.egli.kitmoderator.repo.KitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/kita")
public class KitaResource {

	@Autowired
	private KitaRepository repo;

	@GetMapping("/{identifier}")
	public HttpEntity<Kita> get(@PathVariable("identifier") String id) {
		var kita = repo.findById(id).orElseThrow(() -> new NullPointerException("kita not found"));
		return new HttpEntity<>(kita);
	}

}
