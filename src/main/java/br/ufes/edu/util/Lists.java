package br.ufes.edu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lists {

    public static Map<String, String> getStatesMap() {
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

    public static List<String> getStateList() {
        List<String> stateList = new ArrayList<>();
        stateList.add("AC");
        stateList.add("AL");
        stateList.add("AP");
        stateList.add("AM");
        stateList.add("BA");
        stateList.add("CE");
        stateList.add("DF");
        stateList.add("ES");
        stateList.add("GO");
        stateList.add("MA");
        stateList.add("MT");
        stateList.add("MS");
        stateList.add("MG");
        stateList.add("PA");
        stateList.add("PB");
        stateList.add("PR");
        stateList.add("PE");
        stateList.add("PI");
        stateList.add("RJ");
        stateList.add("RN");
        stateList.add("RS");
        stateList.add("RO");
        stateList.add("RR");
        stateList.add("SC");
        stateList.add("SP");
        stateList.add("SE");
        stateList.add("TO");
        return stateList;

    }

    public static List<String> getEscolaridadeList() {
        List<String> escolaridadeList = new ArrayList<>();
        escolaridadeList.add("Não Alfabetizado");
        escolaridadeList.add("Ensino Fundamental Incompleto");
        escolaridadeList.add("Ensino Fundamental Completo");
        escolaridadeList.add("Ensino Médio Incompleto");
        escolaridadeList.add("Ensino Médio Completo");
        escolaridadeList.add("Ensino Superior Incompleto");
        escolaridadeList.add("Ensino Superior Completo");
        escolaridadeList.add("Pós-Graduação");
        return escolaridadeList;
    }

    public static List<String> getEstadoCivil() {
        List<String> estadoCivilList = new ArrayList<>();
        estadoCivilList.add("Solteiro(a)");
        estadoCivilList.add("Casado(a)");
        estadoCivilList.add("Divorciado(a)");
        estadoCivilList.add("Viúvo(a)");
        estadoCivilList.add("Separado(a)");
        return estadoCivilList;
    }

    public static List<String> getSexList() {
        List<String> sexoList = new ArrayList<>();
        sexoList.add("Masculino");
        sexoList.add("Feminino");
        return sexoList;
    }

    public static List<String> getEtniaList() {
        List<String> racaList = new ArrayList<>();
        racaList.add("Branca");
        racaList.add("Preta");
        racaList.add("Amarela");
        racaList.add("Parda");
        racaList.add("Indígena");
        racaList.add("Não declarada");
        return racaList;
    }

}
