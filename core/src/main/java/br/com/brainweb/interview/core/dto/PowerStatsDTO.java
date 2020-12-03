package br.com.brainweb.interview.core.dto;

import java.util.UUID;

import br.com.brainweb.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PowerStatsDTO {
    private Short strength;
    private Short agility;
    private Short dexterity;
    private Short intelligence;

    public PowerStats toEntity() {
        return new PowerStats(UUID.randomUUID(),this.strength,this.agility,this.dexterity,this.intelligence);
    }
}
