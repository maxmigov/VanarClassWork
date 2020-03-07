package main;

import providers.NasaDataProvider;

public class App {

	public static void main(String[] args) throws Exception {
		
		new NasaDataProvider().getNeoAsteroids();
		
	}

}
