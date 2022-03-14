package br.com.compasso.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class States {

    private String name;
    @Id
    private String cod;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCod() { return cod; }
    public void setCod(String cod) { this.cod = cod; }
}
