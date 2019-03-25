package traveler;

public class Population {

	Route[] routes; // tablica populacji tras

	public Population(int size, boolean first) { // konstruktor populacji
		routes = new Route[size];
		if (first) { // jezeli populacja tworzona jest po raz pierwszy
			for (int i = 0; i < populationSize(); i++) {
				Route route = new Route();
				route.createRoute(); // stworz losowa trase po wszystkich miastach
				saveRoute(i, route); // dodaj trase do tablicy tras
			}
		}
	}

	public void saveRoute(int index, Route route) { // zapisz trase do tablicy tras
		routes[index] = route;
	}

	public Route getRoute(int i) { // zwroc trase po podanym indeksie
		return routes[i];
	}

	public Route getBest() { // zwroc najlepsza trase w populacji
		Route best = routes[0]; // zainicjalizowanie najlepszej trasy jako pierwszej
		for (int i = 1; i < populationSize(); i++) { // petla po wszystkich trasach
			if (best.getDistance() > getRoute(i).getDistance()) { // jezeli znaleziono trase o mniejszym dystansie
				best = getRoute(i); // znaleziona trasa staje sie ta najlepsza
			}
		}
		return best; // zwroc najlepsza trase
	}

	public int populationSize() { // zwroc rozmiar populacji
		return routes.length;
	}
}