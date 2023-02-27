package org.example;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.conexio;
import static org.example.Main.sc;

public class moviments {

    public static ArrayList<Moviment> recuperaMoviments() throws SQLException {
        Connection con;
        String sentenciaSQL = ("SELECT * from movimentProductes where quantitat");
        con = conexio();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sentenciaSQL);
        ArrayList<Moviment> moviments = new ArrayList<>();


        while (rs.next()) {
            Moviment mov =new Moviment( Integer.parseInt( rs.getString("idProducte")),rs.getString("data"),Float.parseFloat(rs.getString("quantitat")),Float.parseFloat(rs.getString("preu")));
            moviments.add(mov);

        }


        return moviments;
    }

    public static ArrayList<Moviment> recuperaMovimentsperIdproducte(String str) throws SQLException {


        Connection con;
        String sentenciaSQL = MessageFormat.format("SELECT * from movimentProductes INNER JOIN productes on movimentProductes.idProducte=productes.idProducte  where productes.idProducte={0};", str);
        con = conexio();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sentenciaSQL);
        ArrayList<Moviment> moviments = new ArrayList<>();


        while (rs.next()) {
            Moviment mov =new Moviment( Integer.parseInt( rs.getString("idProducte")),rs.getString("data"),Float.parseFloat(rs.getString("quantitat")),Float.parseFloat(rs.getString("preu")),rs.getString("nom"));
            moviments.add(mov);

        }


        return moviments;

    }
    public static void main(String[] args) throws SQLException {
        Float preu;
        String date;
        int idProducte;
        float quantitat;
        float quantitatVenuts =0;
        int idMoviment;
        float preuTotal=0;
        String sentenciaSQL;
        Connection con;
        Statement stmt;
        System.out.println("digues: '1' per comprar." +
                "\n        '2' per vendre." +
                "\n        '3' per Calcular l'estoc d'un producte i llistar per pantalla amb el seu preu mitjà." +
                "\n        '4' per Calcular l'estoc de tots els productes i llistar per pantalla amb el seu preu mitjà." +
                "\n        '5' Llistar els moviments que ha tingut un producte ordenats per data."

        );
        switch (sc.nextLine()) {

            case ("1"):
                System.out.println("digues la id del producte ");
                while (true) {
                    try {
                        idProducte = Integer.parseInt(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }
                }

                System.out.println("digues el preu del producte");
                while (true) {
                    try {
                        preu = Float.parseFloat(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }

                }
                System.out.println("digues la data del moviment en format yyy-mm-dd");
                while (true) {
                    try {
                        date = sc.nextLine();
                        if (date.split("-")[0].length() != 4 || date.split("-")[1].length() != 2 || date.split("-")[2].length() != 2) {
                            throw new Exception();
                        }
                        break;
                    } catch (Exception ex) {
                        System.out.println("per favor pose la data en format yyy-mm-dd");
                    }

                }
                System.out.println("digues la cuantitat de producte");
                while (true) {
                    try {
                        quantitat = Float.parseFloat(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }

                }

                con = null;
                stmt = null;
                sentenciaSQL = MessageFormat.format("INSERT INTO movimentProductes(idProducte,data,quantitat,preu) VALUES({0},\'\'{1}\'\',{2},{3})", idProducte, date, quantitat, preu);
                System.out.println(sentenciaSQL);
                try {

                    con = conexio();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sentenciaSQL);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        stmt.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("la ide no existeix no hem pogut efectuar la compra");
                    }
                }
            break;
/**--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------**/


            case ("2"):
                System.out.println("digues la id del producte ");
                while (true) {
                    try {
                        idProducte = Integer.parseInt(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }
                }

                System.out.println("digues el preu del producte");
                while (true) {
                    try {
                        preu = Float.parseFloat(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }

                }
                System.out.println("digues la data del moviment en format yyy-mm-dd");
                while (true) {
                    try {
                        date = sc.nextLine();
                        if (date.split("-")[0].length() != 4 || date.split("-")[1].length() != 2 || date.split("-")[2].length() != 2) {
                            throw new Exception();
                        }
                        break;
                    } catch (Exception ex) {
                        System.out.println("per favor pose la data en format yyy-mm-dd");
                    }

                }
                System.out.println("digues la cuantitat de producte");
                while (true) {
                    try {
                        quantitat = -Float.parseFloat(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }

                }

                con = null;
                stmt = null;
                sentenciaSQL = MessageFormat.format("INSERT INTO movimentProductes(idProducte,data,quantitat,preu) VALUES({0},\'\'{1}\'\',{2},{3})", idProducte, date, quantitat, preu);
                System.out.println(sentenciaSQL);
                try {

                    con = conexio();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sentenciaSQL);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        stmt.close();
                        con.close();
                    } catch (Exception e) {
                        System.out.println("la id no existeix no hem pogut efectuar la venta");
                    }
                }

                break;
            case ("3"):
                System.out.println("digues la id del producte ");
                while (true) {
                    try {
                        idProducte = Integer.parseInt(sc.nextLine());

                        break;
                    } catch (Exception ex) {

                    }
                }
                sentenciaSQL = MessageFormat.format("SELECT * from movimentProductes where idProducte={0};", idProducte);
                con = conexio();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sentenciaSQL);
                preuTotal = 0;
                float estoc1 = 0;
                float mitja=0;

                while (rs.next()) {
                    idProducte = Integer.parseInt(rs.getString("idProducte"));

                    estoc1 += Float.parseFloat(rs.getString("quantitat"));
                    preuTotal = 0;
                    preuTotal += Float.parseFloat(rs.getString("quantitat"));
                    mitja += preuTotal / estoc1;


                }
                String registre = MessageFormat.format("Producte id: {0}, estoc: {1}, preumitja: {2} euros.", idProducte,estoc1 ,mitja );
                System.out.println(registre);
                break;
            case ("4"):
                List<String> listaid = new ArrayList<>();
                ArrayList<Moviment> movimentstots= recuperaMoviments();


                for (int i = 0; i < movimentstots.size(); i++) {


                    idProducte = movimentstots.get(i).getIdProducte();
                    if (!listaid.contains(String.valueOf(idProducte))){
                    listaid.add(String.valueOf(idProducte));}



                }

                for (String str : listaid) {

                    ArrayList<Moviment> moviments= recuperaMovimentsperIdproducte(str);
                    String nom="";
                    estoc1 = 0;
                    mitja=0;
                    preuTotal = 0;
                    quantitatVenuts=0;

                    for (int i = 0; i < moviments.size() ; i++) {
                        nom=moviments.get(i).getNom();
                        idProducte = moviments.get(i).getIdProducte();

                        estoc1 += moviments.get(i).getQuantitat();

                        if(moviments.get(i).getQuantitat()>0){
                            preuTotal+=moviments.get(i).getQuantitat()*moviments.get(i).getPreu();
                            quantitatVenuts+=moviments.get(i).getQuantitat();
                        }





                    }
                    mitja=  preuTotal/quantitatVenuts;

                    registre = MessageFormat.format("Producte id: {0}, estoc: {1}, preumitja: {2} euros., nom: {3}", str,estoc1 ,mitja,nom );
                    System.out.println(registre);
                } break;
            case("5"):
                sentenciaSQL = "SELECT * from movimentProductes order by data;";
                con = conexio();
                stmt = con.createStatement();
                rs = stmt.executeQuery(sentenciaSQL);

                while(rs.next()){
                    date= rs.getString("data");
                    preu = Float.valueOf(rs.getString("preu"));
                    idMoviment= Integer.parseInt(rs.getString("idMoviment"));
                    quantitat=Float.valueOf(rs.getString("quantitat"));
                    idProducte = Integer.parseInt(rs.getString("idProducte"));
                     registre = MessageFormat.format("Producte id: {0}, moviment: {1}, preu: {2} euros , data: {3}, quantitat: {4} .", idProducte, idMoviment, preu,date,quantitat);
                    System.out.println(registre);
                }
                break;

        }


    }
}
