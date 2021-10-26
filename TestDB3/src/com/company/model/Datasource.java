package com.company.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\" + DB_NAME;
    // public static final String CONNECTION_STRING = "jdbc:sqlite:/Volumes/Production/Courses/Programs/JavaPrograms/Music/" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    public static final int ORDER_BY_NONE =1;
    public static final int ORDER_BY_ASC =2;
    public static final int ORDER_BY_DSC =3;

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Sorry something wrong open " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();

            }
        } catch (SQLException e) {
            System.out.println("Closing problem " + e.getMessage());

        }
    }

    public List<Artist> queryArtist(int order){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM "+TABLE_ARTISTS);


        if(order!=ORDER_BY_NONE){
            sb.append(" ORDER BY "+COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if(order==ORDER_BY_ASC){
                sb.append("ASC");
            }
            else{
                sb.append("DESC");
            }
        }
        System.out.println(sb);
        try( Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sb.toString())){

           List<Artist> artists = new ArrayList<>();

           while(result.next()){
               Artist artist = new Artist();
               artist.setId(result.getInt(COLUMN_ARTIST_ID));
               artist.setName(result.getString(COLUMN_ARTIST_NAME));
               artists.add(artist);
           }
           return artists;
        }
        catch (SQLException e){
            System.out.println("Statement wrong "+e.getMessage());
            return null;
        }

    }

    public List<Albums> queryAlbums(){
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM "+TABLE_ALBUMS)){

            List<Albums> albums = new ArrayList<>();

            while(result.next()){
                Albums a = new Albums();
                a.setId(result.getInt(COLUMN_ALBUM_ID));
                a.setName(result.getString(COLUMN_ALBUM_NAME));
                a.setArtist(result.getInt(COLUMN_ALBUM_ARTIST));

                albums.add(a);
            }
            return albums;
        }
        catch(SQLException e){
            System.out.println("Sorry Bro");
            return null;
        }
    }






}
