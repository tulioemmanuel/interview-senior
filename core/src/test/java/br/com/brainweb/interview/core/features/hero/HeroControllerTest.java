package br.com.brainweb.interview.core.features.hero;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {

    @Autowired
    private HeroController controller;

    @Autowired
    private MockMvc mvc;

    // @BeforeAll
    // void setup() throws Exception{
    //     JSONObject hero = new JSONObject();
    //     JSONObject stats = new JSONObject();
    //     stats.put("strength", 30);
    //     stats.put("agility", 50);
    //     stats.put("dexterity", 30);
    //     stats.put("intelligence", 70);

    //     hero.put("name", "Tulio3");
    //     hero.put("race", "HUMAN");
    //     hero.put("enabled", true);
    //     hero.put("powerStats", stats);

    //     this.mvc.perform(post("/api/v1/hero").contentType(MediaType.APPLICATION_JSON).content(hero.toJSONString()))
    //             .andDo(print()).andExpect(status().isCreated());
    // }

    @Test
    public void checkController() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getAllHeroes() throws Exception {
        this.mvc.perform(get("/api/v1/hero/all")).andExpect(status().isOk());
    }

    @Test
    public void createHero() throws Exception {

        JSONObject hero = new JSONObject();
        JSONObject stats = new JSONObject();
        stats.put("strength", 30);
        stats.put("agility", 50);
        stats.put("dexterity", 30);
        stats.put("intelligence", 70);

        hero.put("name", "Tulio3");
        hero.put("race", "HUMAN");
        hero.put("enabled", true);
        hero.put("powerStats", stats);

        this.mvc.perform(post("/api/v1/hero").contentType(MediaType.APPLICATION_JSON).content(hero.toJSONString()))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void getHeroById() throws Exception {
        String knowHeroId = "";
        this.mvc.perform(get("/api/v1/hero/" + knowHeroId)).andExpect(status().isFound());
    }

}
