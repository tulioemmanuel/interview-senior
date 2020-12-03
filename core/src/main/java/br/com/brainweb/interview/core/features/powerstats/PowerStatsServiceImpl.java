package br.com.brainweb.interview.core.features.powerstats;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.core.dto.PowerStatsDTO;
import br.com.brainweb.interview.model.PowerStats;

@Service
public class PowerStatsServiceImpl implements PowerStatsService {

    private final PowerStatsRepository powerStatusRepository;

    @Autowired
    public PowerStatsServiceImpl(PowerStatsRepository powerStatusRepository) {
        this.powerStatusRepository = powerStatusRepository;
    }

    @Override
    public PowerStats createNewPowerStats(PowerStatsDTO powerStatsDto) {
        return this.powerStatusRepository.save(powerStatsDto.toEntity());
    }

    @Override
    public Optional<PowerStats> findStatsById(UUID statsId) {
        return this.powerStatusRepository.findById(statsId);
    }

    @Override
    public void updatePowerStats(PowerStats powerStats) {
        this.powerStatusRepository.save(powerStats);
    }
}
