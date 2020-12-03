package br.com.brainweb.interview.core.features.hero;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.brainweb.interview.model.Hero;

@ActiveProfiles("it")
@SpringBootTest
@AutoConfigureMockMvc
public class HeroServiceIT {

    @Autowired
    HeroService heroService;

    @Test
    public void findByName(){
        String name = "Tulio";
        List<Hero> heroes = this.heroService.getHeroesByName(name);
        boolean isBiggerThanZero = heroes.size() > 0;
        assertTrue(isBiggerThanZero);
    }

}
