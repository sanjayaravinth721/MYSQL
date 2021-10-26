package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static String contactName ="name";
    private static String contactEmail = "email";
    private static String contactPhone = "phone";

    private static String TABLE_CONTACTS = "contacts";
    private static String DB_NAME = "testjava2.db";
    private static String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\"+DB_NAME;

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args)  {
	// write your code here
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjav2.db");
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " (" + contactName + " TEXT," +
                    contactPhone + " INTEGER," +
                    contactEmail + " TEXT" +
                    ")");
            insert(statement,"Sanjay",999,"sanjay56@gmail.com");
            insert(statement,"floki",777,"floki654@gmail.com");
            insert(statement,"Ragnor",987,"ragnor53@gmao.com");
            insert(statement,"Rollo",998,"Rollo432@gmail.com");

            updatePhone(statement);

            print(statement);


        }
        catch(SQLException e){
            System.out.println("Something went wrong "+e.getMessage());
            e.printStackTrace();
        }

    }
    private static void insert(Statement statement,String name,int phone,String email) throws SQLException{
        statement.execute("INSERT INTO "+TABLE_CONTACTS+" ("+contactName+","+contactPhone+","+contactEmail+")"
                            +"VALUES('"+name+"',"+phone+",'"+email+"')");
    }
    private static void updatePhone(Statement statement) throws SQLException{
        System.out.println("Enter the name to update his/her phone number");
        String u = s.nextLine();
        System.out.println("Enter the new phone number");
        int a =  s.nextInt();

        statement.execute("UPDATE "+TABLE_CONTACTS+" SET "+contactPhone +"= "+a + " WHERE "+contactName+"='u'");
    }
    private static void print(Statement statement) throws SQLException{
        statement.execute("SELECT * FROM "+TABLE_CONTACTS);
        ResultSet result = statement.getResultSet();
        while(result.next()){
            System.out.println(result.getString(contactName)+" "+result.getInt(contactPhone)+" "+result.getString(contactEmail));
        }

    }

}
