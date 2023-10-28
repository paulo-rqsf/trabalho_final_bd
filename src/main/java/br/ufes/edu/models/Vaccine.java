package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Vaccine {
    private Long vaccineId;
    private String name, description, batch;
}
