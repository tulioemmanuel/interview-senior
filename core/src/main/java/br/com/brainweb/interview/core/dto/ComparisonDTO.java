package br.com.brainweb.interview.core.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComparisonDTO {
    private UUID heroAId;    
    private UUID heroBId;    
    private Short strength; 
    private Short agility; 
    private Short dexterity; 
    private  Short intelligence; 
}
