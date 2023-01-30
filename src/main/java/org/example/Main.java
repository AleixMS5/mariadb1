package org.example;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Scanner;
public class Main {

    static Scanner sc = new Scanner(System.in);
    public static Connection conexio() throws SQLException {
        Connection con = null;
        String sURL = "jdbc:mariadb://localhost:3306/mp03uf6";
        con = DriverManager.getConnection(sURL, "estudiant", "Admin1234");
        return con;

    }

    public static void main(String[] args) throws SQLException {

        Locale.setDefault(new Locale("en", "US"));
        System.out.println("digues 1 per crear");
        System.out.println("digues 2 per modificar ");
        System.out.println("digues 3 per veurels tots");
        System.out.println("digues 4 per esborrar ");
        System.out.println("digues 5 per buscarne 1 ");
        switch(sc.nextLine()) {
            case "1":
                System.out.println("digues el nom del producte");
                String nom = sc.nextLine();
                System.out.println("digues el preu del producte");
                Float preu;
                while (true) {
                    try {
                        preu = Float.parseFloat(sc.nextLine());

                        break;
                    }catch (Exception ex){

                    }

                }
                Connection con = null;
                Statement stmt = null;
                String sentenciaSQL = MessageFormat.format("INSERT INTO productes(nom,preu) VALUES(\'\'{0}\'\',{1})", nom, preu);
                System.out.println(sentenciaSQL);
                try {

                    con = conexio();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sentenciaSQL);
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    try {
                        stmt.close();
                        con.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case "2":

                sentenciaSQL = "SELECT * from productes;";
                 con = conexio();
                 stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sentenciaSQL);

                while(rs.next()){
                    String id = rs.getString("idProducte");
                    String nomme = rs.getString("nom");
                    String preume = rs.getString("preu");
                    String registre = MessageFormat.format("Producte id: {0}, nom: {1}, preu: {2} euros.", id, nomme, preume);
                    System.out.println(registre);
                }
                con = null;
                stmt = null;
                int ide;
                while (true) {
                    try {
                        System.out.println("digues el id a modificar");
                        ide = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (Exception ex) {

                    }
                }
                System.out.println("digues el nou nom");
                String nome = sc.nextLine();
                Float preue;
                while (true) {
                    try {
                        System.out.println("digues el nou preu");
                        preue = Float.parseFloat(sc.nextLine());
                        break;
                    }catch (Exception ex){

                    }

                }
                sentenciaSQL = MessageFormat.format( "UPDATE productes SET nom =\'\'{0}\'\', preu = {1} WHERE idProducte = {2}",nome,preue,ide);
                try {

                    con = conexio();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sentenciaSQL);
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    try {
                        stmt.close();
                        con.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case "3":
                try {
                    System.out.println("digues 1 per ordenar per nom 2 per preu 3 per buscar per string o cualsevol cosa per id");
                    switch (sc.nextLine()){
                        case "1":
                            sentenciaSQL = "SELECT * from productes order by nom;";
                            break;
                        case "2":
                            sentenciaSQL = "SELECT * from productes order by preu;";
                            break;
                        case "3":
                            System.out.println("digues una cadena de caracters per buscar");
                            sentenciaSQL = "SELECT * from productes WHERE nom LIKE '%"+sc.nextLine()+"%'";
                            break;
                        default:
                            sentenciaSQL = "SELECT * from productes;";
                            break;

                    }

                     con = conexio();
                    stmt = con.createStatement();
                     rs = stmt.executeQuery(sentenciaSQL);

                    while(rs.next()){
                        String id = rs.getString("idProducte");
                        String nomm = rs.getString("nom");
                        String preum = rs.getString("preu");
                        String registre = MessageFormat.format("Producte id: {0}, nom: {1}, preu: {2} euros.", id, nomm, preum);
                        System.out.println(registre);
                    }

                    rs.close();
                    stmt.close();
                    con.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "4":
                sentenciaSQL = "SELECT * from productes;";
                con = conexio();
                stmt = con.createStatement();
                rs = stmt.executeQuery(sentenciaSQL);

                while(rs.next()){
                    String id = rs.getString("idProducte");
                    String nomme = rs.getString("nom");
                    String preume = rs.getString("preu");
                    String registre = MessageFormat.format("Producte id: {0}, nom: {1}, preu: {2} euros.", id, nomme, preume);
                    System.out.println(registre);
                }

                con = null;
                stmt = null;
                while (true) {
                    try {
                        System.out.println("digues el id a modificar");
                        ide= Integer.parseInt(sc.nextLine());
                        break;
                    } catch (Exception ex) {

                    }
                }
                sentenciaSQL = "DELETE FROM productes WHERE idProducte="+ide+";";
                try {

                    con = conexio();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sentenciaSQL);
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    try {
                        stmt.close();
                        con.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;

            case "5":
                try {
                    while (true) {
                        try {
                            System.out.println("digues el id a buscar");
                            ide = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception ex) {

                        }
                    }
                     sentenciaSQL = "SELECT * from productes where idProducte="+ide+";";
                     con = conexio();
                     stmt = con.createStatement();
                     rs = stmt.executeQuery(sentenciaSQL);

                    while(rs.next()){
                        String id = rs.getString("idProducte");
                        String nomm = rs.getString("nom");
                        String preum = rs.getString("preu");
                        String registre = MessageFormat.format("Producte id: {0}, nom: {1}, preu: {2} euros.", id, nomm, preum);
                        System.out.println(registre);
                    }

                    rs.close();
                    stmt.close();
                    con.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}