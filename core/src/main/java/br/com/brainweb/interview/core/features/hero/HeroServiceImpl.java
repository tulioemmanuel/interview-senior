package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.core.dto.ComparisonDTO;
import br.com.brainweb.interview.core.dto.HeroDTO;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.core.features.hero.HeroRepository;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, PowerStatsService powerStatsService) {
        this.heroRepository = heroRepository;
        this.powerStatsService = powerStatsService;

    }

    @Override
    public Hero createHero(HeroDTO newHero) {
        PowerStats createdPowerStats = this.powerStatsService.createNewPowerStats(newHero.getPowerStats());
        Hero createdHero = Hero.builder().name(newHero.getName()).race(newHero.getRace())
                .power_stats_id(createdPowerStats.getId()).enabled(newHero.isEnabled()).build();

        return this.heroRepository.save(createdHero);
    }

    @Override
    public Optional<Hero> getHeroByID(UUID heroId) {
        return this.heroRepository.findById(heroId);
    }

    @Override
    public List<Hero> getHeroesByName(String heroName) {
        List<Hero> lista = this.heroRepository.findByName(heroName);
        return lista;
    }

    @Override
    public List<Hero> getAllHeroes() {
        return this.heroRepository.findAll();
    }

    @Override
    public Hero updateHero(UUID heroId, HeroDTO hero) {
        Optional<Hero> foundHero = this.heroRepository.findById(heroId);

        if (!foundHero.isPresent())
            return null;

        Hero updatedHero = foundHero.get();

        Optional<PowerStats> foundStatus = this.powerStatsService.findStatsById(updatedHero.getPower_stats_id());
        if (!foundStatus.isPresent())
            return null;

        PowerStats updatedStats = foundStatus.get();

        updatedHero.setEnabled(hero.isEnabled());
        updatedHero.setName(hero.getName());
        updatedHero.setRace(hero.getRace());

        updatedStats.setStrength(hero.getPowerStats().getStrength());
        updatedStats.setAgility(hero.getPowerStats().getAgility());
        updatedStats.setDexterity(hero.getPowerStats().getDexterity());
        updatedStats.setIntelligence(hero.getPowerStats().getIntelligence());

        this.powerStatsService.updatePowerStats(updatedStats);

        return this.heroRepository.save(updatedHero);
    }

    public void deleteHero(UUID heroId) {
        this.heroRepository.deleteById(heroId);
    }

    @Override
    public ComparisonDTO compareHeroes(Hero heroA, Hero heroB) {
        Optional<PowerStats> heroAStats = this.powerStatsService.findStatsById(heroA.getPower_stats_id());
        Optional<PowerStats> heroBStats = this.powerStatsService.findStatsById(heroB.getPower_stats_id());

        return ComparisonDTO.builder()
        .heroAId(heroA.getId())
        .heroBId(heroB.getId())
        .agility( (short)(heroAStats.get().getAgility() - heroBStats.get().getAgility()))
        .dexterity((short)(heroAStats.get().getDexterity() - heroBStats.get().getDexterity()))
        .intelligence((short)(heroAStats.get().getIntelligence() - heroBStats.get().getIntelligence()))
        .strength((short)(heroAStats.get().getStrength() - heroBStats.get().getStrength()))
        .build();
    }

}
