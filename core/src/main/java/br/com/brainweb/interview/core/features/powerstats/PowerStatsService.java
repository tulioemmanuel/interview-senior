package br.com.brainweb.interview.core.features.powerstats;

import java.util.Optional;
import java.util.UUID;

import br.com.brainweb.interview.core.dto.PowerStatsDTO;
import br.com.brainweb.interview.model.PowerStats;

public interface PowerStatsService {
    public PowerStats createNewPowerStats(PowerStatsDTO powerStatsDto);
    public Optional<PowerStats> findStatsById(UUID statsId);
    public void updatePowerStats(PowerStats powerStats);
}
