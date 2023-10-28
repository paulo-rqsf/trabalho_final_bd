package br.ufes.edu.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class States {

    public static Map<String, String> returnStates() {
        Map<String, String> states = new HashMap<>();
        states.put("AC", "Acre");
        states.put("AL", "Alagoas");
        states.put("AP", "Amapá");
        states.put("AM", "Amazonas");
        states.put("BA", "Bahia");
        states.put("CE", "Ceará");
        states.put("DF", "Distrito Federal");
        states.put("ES", "Espírito Santo");
        states.put("GO", "Goiás");
        states.put("MA", "Maranhão");
        states.put("MT", "Mato Grosso");
        states.put("MS", "Mato Grosso do Sul");
        states.put("MG", "Minas Gerais");
        states.put("PA", "Pará");
        states.put("PB", "Paraíba");
        states.put("PR", "Paraná");
        states.put("PE", "Pernambuco");
        states.put("PI", "Piauí");
        states.put("RJ", "Rio de Janeiro");
        states.put("RN", "Rio Grande do Norte");
        states.put("RS", "Rio Grande do Sul");
        states.put("RO", "Rondônia");
        states.put("RR", "Roraima");
        states.put("SC", "Santa Catarina");
        states.put("SP", "São Paulo");
        states.put("SE", "Sergipe");
        states.put("TO", "Tocantins");
        return states;
    }

}
