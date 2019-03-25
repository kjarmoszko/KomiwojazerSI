package traveler;

import java.util.ArrayList;
import java.util.Collections;

public class Route {
	private ArrayList route = new ArrayList<City>(); // trasa po wszystkich miastach
	private int distance = 0; // dystans trasy

	public Route() { // konstruktor domyślny
		for (int i = 0; i < Map.numberOfCities(); i++) {
			route.add(null); // tworzenie pustych miast
		}
	}

	public Route(ArrayList route) { // konstruktor
		this.route = route;
	}

	public void createRoute() { // tworzenie nowej losowej trasy
		for (int i = 0; i < Map.numberOfCities(); i++) { // petla po wszystkich miastach
			setCity(i, Map.getCity(i)); // dodaj miasto do trasy
		}
		Collections.shuffle(route); // przetasuj miasta
	}

	public City getCity(int i) { // zwrocenie itego miasta na trasie
		return (City) route.get(i);
	}

	public void setCity(int i, City city) { // ustawienie miasta na itej pozycji
		route.set(i, city);
		distance = 0; // jezeli dodano nowe miasto dystans trzeba podliczyc na nowo
	}

	public int getDistance() { // zwroc dystans trasy
		if (distance == 0) { // jezeli dystans nie byl jeszcze wyliczony
			int dist = 0;
			for (int i = 0; i < routeSize(); i++) { // petla po wszystkich miastach
				City fromCity = getCity(i); // miasto poczatkowe
				City destCity; // miassto docelowe
				if (i + 1 < routeSize()) // jezeli jest jeszcze nastepne miasto
					destCity = getCity(i + 1); // miasto docelowe to nastepne miasto
				else {
					destCity = getCity(0); // miasto docelowe to miasto poczatkowe
				}
				dist += fromCity.distanceTo(destCity); // sumowanie odleglosci miedzy miastami
			}
			distance = dist;
		}
		return distance;
	}

	public int routeSize() { // ilosc miast w trasie
		return route.size();
	}

	public boolean containsCity(City city) { // sprawdzenie czy miasto znajduje sie w trasie
		return route.contains(city);
	}

	@Override
	public String toString() { // wypisanie wspolrzednych miasta
		String st = "";
		for (int i = 0; i < routeSize(); i++) {
			st += "[" + getCity(i) + "] ";
		}
		return st;
	}
}