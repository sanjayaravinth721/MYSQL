package com.company;

import java.sql.*;

import static java.lang.Class.forName;

public class Main {

    public static void main(String[] args) {
        // 2nd way:
//        try( Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjava.db");
//         Statement statement = conn.createStatement()){
//
//            statement.execute("CREATE TABLE contact(name TEXT,phone INTEGER,email TEXT)");
//
//        }
        //1way
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjava.db");
            Statement statement = conn.createStatement();
            conn.setAutoCommit(false);
            statement.execute("CREATE TABLE IF NOT EXISTS contact "+"(name TEXT,phone INTEGER,email TEXT)");

            statement.execute("INSERT INTO contact(name,phone,email) "+
                    "VALUES('sanjay',7904349337,'sanjayaravinth26@gmail.com')");
            statement.execute("INSERT INTO contact(name,phone,email) "+
                    "VALUES('Ragnor',7804349337,'ragnorLothbrok28@gmail.com')");
            statement.execute("INSERT INTO contact(name,phone,email) "+
                    "VALUES('Bjorn',7304349337,'bjornIronside67@gmail.com')");
            statement.execute("INSERT INTO contact(name,phone,email) "+
                    "VALUES('Floki',7504349337,'flokiBoatbuilder22@gmail.com')");
            statement.execute("UPDATE contact SET phone=9843020528 "+"WHERE name='Bjorn'");
            statement.execute("DELETE from contact where name='Bjorn'");

            //1st method to execute the tables
//            statement.execute("SELECT DISTINCT * FROM contact");
//            ResultSet results = statement.getResultSet();

            //2nd method to execute the tables
            ResultSet results = statement.executeQuery("SELECT DISTINCT * FROM contact");
            while(results.next()){
                System.out.println(results.getString("name")+" "+
                                   results.getString("phone")+" "+
                                   results.getString("email"));
            }
            results.close();
            statement.close();
            conn.close();
        }

        catch(SQLException e){
            System.out.println("something went wrong "+e.getMessage());
        }
    }
}
