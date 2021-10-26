package com.company;

import com.company.model.Albums;
import com.company.model.Artist;
import com.company.model.Datasource;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Cant open");
        }

        List<Artist> artists = datasource.queryArtist(Datasource.ORDER_BY_ASC);

        for(Artist artist:artists){
           System.out.println("Id: "+artist.getId()+"  Name: "+artist.getName());
        }
        List<Albums> albums = datasource.queryAlbums();

//        for(Albums album : albums){
//            System.out.println("Id: "+album.getId()+" Name: "+album.getName()+" Artist: "+album.getArtist());
//        }


        datasource.close();

    }
}
