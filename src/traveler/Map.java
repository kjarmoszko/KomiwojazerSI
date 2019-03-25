package traveler;

import java.util.ArrayList;

public class Map {

	private static ArrayList cities = new ArrayList<City>(); // lista miast

	public static void addCity(City city) { // dodanie miasta do listy
		cities.add(city);
	}

	public static City getCity(int index) { // zwrocenie itego miasta
		return (City) cities.get(index);
	}

	public static int numberOfCities() { // zwrocenie ilosci miast
		return cities.size();
	}
}