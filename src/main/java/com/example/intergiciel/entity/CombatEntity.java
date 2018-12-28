package com.example.intergiciel.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CombatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String adversaire1;
    private String adversaire2;
    private String vainqueur;

    public Long getId() {
        return id;
    }


    public String getAdversaire1() {
        return adversaire1;
    }

    public String getVainqueur() {
        return vainqueur;
    }

    public String getAdversaire2() {
        return adversaire2;
    }


    public void setAdversaire1(String adversaire1) {
        this.adversaire1 = adversaire1;
    }


    public void setAdversaire2(String adversaire2) {
        this.adversaire2 = adversaire2;
    }


    public void setVainqueur(String vainqueur) {
        this.vainqueur = vainqueur;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
