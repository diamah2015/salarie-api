package com.craftteam.salarie.controller;

import com.craftteam.salarie.model.Salarie;
import com.craftteam.salarie.service.SalarieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalarieController {

	  private final SalarieService salarieService;

	    public SalarieController(SalarieService salarieService) {
	        this.salarieService = salarieService;
	    }

	    // 1. Tous les salariés
	    @GetMapping
	    public List<Salarie> getAll() {
	        return salarieService.getAll();
	    }

	    // 2. Trié par nom
	    @GetMapping("/sorted/name")
	    public List<Salarie> getAllSortedByName() {
	        return salarieService.getAllSortedByName();
	    }

	    // 3. Trié par date de naissance
	    @GetMapping("/sorted/birthdate")
	    public List<Salarie> getAllSortedByBirthDate() {
	        return salarieService.getAllSortedByBirthDate();
	    }

	    // 4. Filtrer par genre
	    @GetMapping("/genre/{genre}")
	    public List<Salarie> getByGenre(@PathVariable String genre) {
	        return salarieService.getByGenre(genre);
	    }

	    // 5. Filtrer par intervalle de salaire
	    @GetMapping("/salaire")
	    public List<Salarie> getBySalaireRange(@RequestParam double min, @RequestParam double max) {
	        return salarieService.getBySalaireRange(min, max);
	    }
}
