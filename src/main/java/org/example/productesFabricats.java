package org.example;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static org.example.Main.conexio;
import static org.example.Main.sc;

public class productesFabricats {
    public static void main(String[] args) throws SQLException {
        String sentenciaSQL;
        String sentenciaSQL2;
        Connection con = conexio();
        ;
        Statement stmt;

        System.out.println("digues: '1' per veure els productes amb la seva recepta." + "\n        '2' Previsió de fabricació." + "\n        '3' Estoc de productes a fabricar" + "\n"

        );
        switch (sc.nextLine()) {

            case ("1"):
                sentenciaSQL = ("SELECT * from ProductesFabricats");
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sentenciaSQL);


                while (rs.next()) {

                    int id = Integer.parseInt(rs.getString("idProducteFabricat"));

                    String nomFabric = rs.getString("nom");

                    String registre = MessageFormat.format("Producte id: {0}, nom: {1}.", id, nomFabric);
                    System.out.println(registre);
                    sentenciaSQL2 = ("SELECT * from llistatMaterials natural join productes where producteFabricat=" + id);
                    stmt = con.createStatement();
                    ResultSet rs2 = stmt.executeQuery(sentenciaSQL2);
                    while (rs2.next()) {
                        String idMaterial = rs2.getString("idMaterial");
                        String idProducte = rs2.getString("idProducte");
                        String producteFabricat = rs2.getString("producteFabricat");
                        String quantitat = rs2.getString("quantitat");
                        String nom = rs2.getString("nom");
                        String preu = rs2.getString("preu");

                        String registre2 = MessageFormat.format("Material idMaterial: {0}, idProducte: {1},producteFabricat: {2},quantitat: {3},nom: {4},preu: {5} .", idMaterial, idProducte, producteFabricat, quantitat, nom, preu);
                        System.out.println(registre2);
                    }

                }
                break;
            case ("2"):
                int producte;
                int q ;
                        System.out.println("digues la id del producte");
                while (true){
                try {
                    producte = Integer.parseInt(sc.nextLine());
                    break;
                }catch (Exception ex){
                    System.out.println("la quantitat es un nombre");
                }
                }

                System.out.println("digues la quantitat de productes");
                while (true){
                try {
                q=Integer.parseInt(sc.nextLine());
                break;
                }catch (Exception ex){
                    System.out.println("la id es un nombre");
                }
        }
                try {
                    while (true) {


                        sentenciaSQL = ("SELECT * from ProductesFabricats where idProducteFabricat=" + producte);
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(sentenciaSQL);
                        break;
                    }
                    while (rs.next()) {

                        int id = Integer.parseInt(rs.getString("idProducteFabricat"));

                        String nomFabric = rs.getString("nom");

                        String registre = MessageFormat.format("Producte id: {0}, nom: {1}.", id, nomFabric);
                        System.out.println(registre);
                        sentenciaSQL2 = ("SELECT * from llistatMaterials natural join productes where producteFabricat=" + id);
                        stmt = con.createStatement();
                        ResultSet rs2 = stmt.executeQuery(sentenciaSQL2);
                        while (rs2.next()) {
                            String idMaterial = rs2.getString("idMaterial");
                            String idProducte = rs2.getString("idProducte");
                            String producteFabricat = rs2.getString("producteFabricat");
                            float quantitat = Float.parseFloat(rs2.getString("quantitat")) * q;
                            String nom = rs2.getString("nom");
                            float preu = Float.parseFloat(rs2.getString("preu")) * q;

                            String registre2 = MessageFormat.format("Material idMaterial: {0}, idProducte: {1},producteFabricat: {2},quantitat: {3},nom: {4},preu: {5} .", idMaterial, idProducte, producteFabricat, quantitat, nom, preu);
                            System.out.println(registre2);
                        }

                    }
                } catch (Exception ex) {
                    System.out.println("digues be la id per favor");
                }


                break;

            case ("3"):

                sentenciaSQL = ("select * from ProductesFabricats");
                stmt = con.createStatement();
                rs = stmt.executeQuery(sentenciaSQL);


                    while (rs.next()) {
                        sentenciaSQL2 = ("SELECT * from llistatMaterials  where producteFabricat=(select idProducteFabricat from ProductesFabricats where idProducteFabricat='"+rs.getString("idProducteFabricat")+"');" );
                        System.out.println(rs.getString("nom"));
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(sentenciaSQL2);
                        ArrayList<Float>productesAfer=new ArrayList<Float>();
                        while (rs2.next()) {
                          String idProducte= rs2.getString("idProducte");
                            Float quantitat= Float.parseFloat(rs2.getString("quantitat"));
                            String sentenciaSQL3 = MessageFormat.format("SELECT * from movimentProductes where idProducte={0};", idProducte);
                            Statement stmt3 = con.createStatement();
                            ResultSet rs3 = stmt3.executeQuery(sentenciaSQL3);
                            int preuTotal = 0;
                            float estoc1 = 0;
                            float mitja=0;

                            while (rs3.next()) {
                                String idProducte2 = rs3.getString("idProducte");

                                estoc1 += Float.parseFloat(rs3.getString("quantitat"));
                                productesAfer.add(estoc1/quantitat);

                            }

                        }
                        sort(productesAfer);
                        System.out.println(productesAfer.get(0));


                    }




                break;

        }
    }
}
