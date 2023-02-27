package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;


import static org.example.Main.conexio;


public class Moviment {
    private int idProducte;
    private String data;
    private float quantitat;

    private float preu;

    private String nom;

    public Moviment(int idProducte, String data, float quantitat, float preu) {
        this.idProducte = idProducte;
        this.data = data;
        this.quantitat = quantitat;
        this.preu = preu;

    }

    public Moviment(int idProducte, String data, float quantitat, float preu, String nom) {
        this.idProducte = idProducte;
        this.data = data;
        this.quantitat = quantitat;
        this.preu = preu;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(float quantitat) {
        this.quantitat = quantitat;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }


}
