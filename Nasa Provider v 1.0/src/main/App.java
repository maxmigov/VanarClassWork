package src.main;

import java.io.IOException;
import java.util.Scanner;

import src.providers.Asteroids;
import src.providers.NasaDataProvider;

public class App {

    public static void main(String[] args) throws Exception {

        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Expected format (yyyy-mm-dd) - The Feed date limit is only 7 Days");
            System.out.println("Enter start/end dates ");

            String start = in.next(), end = in.next();

            new NasaDataProvider().getNeoAsteroids(start, end);



        }
        catch (IOException e){
            System.out.println("400 Bad Request");
            System.out.println("The Feed date limit is only 7 Days");
        }

    }

}
