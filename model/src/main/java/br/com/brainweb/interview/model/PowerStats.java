package br.com.brainweb.interview.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PowerStats {
 
    @Id
    @GeneratedValue
    private UUID id;
    private Short strength; 
    private Short agility; 
    private Short dexterity; 
    private  Short intelligence; 

}
