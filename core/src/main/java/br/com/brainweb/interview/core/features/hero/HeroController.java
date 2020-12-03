package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.brainweb.interview.core.dto.ComparisonDTO;
import br.com.brainweb.interview.core.dto.HeroDTO;
import br.com.brainweb.interview.core.dto.HeroesDTO;
import br.com.brainweb.interview.model.Hero;

@RestController
@RequestMapping("/api/v1/hero")
@CrossOrigin("*")
public class HeroController {

    private final HeroService heroService;
    
    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping
    @CacheEvict(value = "heroes")
    public ResponseEntity createNewHero(@RequestBody HeroDTO newHero){
        HttpHeaders headers = new HttpHeaders();
        Hero createdHero = this.heroService.createHero(newHero);
        headers.add("Location", "/api1/v1/hero/" + createdHero.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Cacheable(value = "heroes")
    ResponseEntity<List<Hero>> getAllHeroes(){
        return new ResponseEntity<>(this.heroService.getAllHeroes(),HttpStatus.OK);
    }

    @GetMapping("")
    @Cacheable(value = "heroes")
    ResponseEntity<List<Hero>> getHeroesByName(@RequestParam("name") String heroName){
        return new ResponseEntity<>(this.heroService.getHeroesByName(heroName),HttpStatus.OK);
    }

    @GetMapping("/{heroId}")
    @Cacheable(value = "heroes")
    ResponseEntity<Hero> getHeroById(@PathVariable("heroId") UUID heroId){
        Optional<Hero> hero = this.heroService.getHeroByID(heroId);
        return !hero.isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(hero.get(),HttpStatus.FOUND);
    }   

    @PatchMapping("/{heroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "heroes")
    void getHeroById(@PathVariable("heroId") UUID heroId , @RequestBody HeroDTO updatedHero){
        this.heroService.updateHero(heroId,updatedHero);
    }   

    
    @DeleteMapping("/{heroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "heroes")
    public void deleteHero(@PathVariable("heroId") UUID heroId){
        this.heroService.deleteHero(heroId);
    }

    @PostMapping("/compare")
    @ResponseStatus(HttpStatus.OK)
    public ComparisonDTO compareHeroes(@RequestBody Map<String,Hero> heroes){
        return this.heroService.compareHeroes(heroes.get("heroA"), heroes.get("heroB"));
    }




}
