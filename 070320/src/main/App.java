package main;

import java.util.Scanner;

import providers.NasaDataProvider;

public class App {

	public static void main(String[] args) throws Exception {
	
		Scanner in = new Scanner(System.in);
		System.out.println("Enter start/end dates ");
		
		String start = in.next(), end = in.next();
		
		new NasaDataProvider().getNeoAsteroids(start, end);
		
	}

}
