package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.brainweb.interview.core.dto.ComparisonDTO;
import br.com.brainweb.interview.core.dto.HeroDTO;
import br.com.brainweb.interview.model.Hero;

public interface HeroService {
    public Hero createHero(HeroDTO newHero);
    public Optional<Hero> getHeroByID(UUID heroId);
    public Hero updateHero(UUID heroId , HeroDTO updatedHero);
    public void deleteHero(UUID heroId);
    public List<Hero> getHeroesByName(String heroName);
    public List<Hero> getAllHeroes();
    public ComparisonDTO compareHeroes(Hero heroA,Hero heroB);
}
