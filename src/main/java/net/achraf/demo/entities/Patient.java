package net.achraf.demo.entities;

import java.util.List;

public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private List<Consultation> consultations;

    public Patient(Long id, String name, String prenom, String tel, String email) {
        this.id = id;
        this.nom = name;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return nom + ' '+ prenom ;
    }
}
