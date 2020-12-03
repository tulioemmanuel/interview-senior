package br.com.brainweb.interview.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hero {

    @Id
    @GeneratedValue
    @Getter
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String race;
    @NotNull
    private UUID power_stats_id;
    @NotNull
    private boolean enabled;

}
