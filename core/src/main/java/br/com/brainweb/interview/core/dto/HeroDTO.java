package br.com.brainweb.interview.core.dto;

import java.util.UUID;

import br.com.brainweb.interview.model.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeroDTO {
    private String name;
    private String race;
    private PowerStatsDTO powerStats;
    private boolean enabled;
}
