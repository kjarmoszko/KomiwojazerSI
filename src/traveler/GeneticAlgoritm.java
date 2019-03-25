package traveler;

import java.io.FileNotFoundException;

public class GeneticAlgoritm {

	private static final double potencialParents = 0.25;

	public static Population evolve(Population pop, double crossRate, double mutationRate)
			throws FileNotFoundException { // ewolucja populacji
		Population newPopulation = new Population(pop.populationSize(), false); // tworzenie nowej populacji

		newPopulation.saveRoute(0, pop.getBest()); // zachowaj najlepsze rozwiazanie poprzedniego pokolenia

		for (int i = 1; i < newPopulation.populationSize(); i++) { // pozostala poluacja bedzie skladala sie z nowych
																	// czlonkow
			if (Math.random() < crossRate) { // losuj mozliwosc krzyzowania
				Route parent1 = chooseParents(pop); // wybor najlepszych rodzicow do krzyzowania
				Route parent2 = chooseParents(pop);
				Route child = crossing(parent1, parent2); // krzyzowanie rodzicow
				newPopulation.saveRoute(i, child); // dodanie dziecka do nowej populacji
			} else {
				newPopulation.saveRoute(i, pop.getRoute(i)); // jeżeli brak krzyżowania do nowej populacji przechodzi
																// stary potomek
			}
		}

		for (int i = 1; i < newPopulation.populationSize(); i++) // mutowanie dzieci nowej populacji
			mutate(newPopulation.getRoute(i), mutationRate);

		return newPopulation;
	}

	public static Route crossing(Route parent1, Route parent2) { // krzyzowanie rodzicow
		Route child = new Route(); // stworzenie nowego dziecka

		int begin = (int) (Math.random() * parent1.routeSize()); // losowy wybor poczatkowego miasta 1 rodzica
		int end = (int) (Math.random() * parent1.routeSize()); // losowy wybor koncowego miasta 1 rodzica
		if (begin > end) { // jezeli poczatek jest wiekszy od konca
			int tmp = begin; // zamien poczatek z koncem
			begin = end;
			end = tmp;
		}

		for (int i = 0; i < child.routeSize(); i++) {
			if (i > begin && i < end) // poczatek i koniec mieszcza sie w zakresie dziedziczenia
				child.setCity(i, parent1.getCity(i)); // odziedzicz miasto po 1 rodzicu

		}

		for (int i = 0; i < parent2.routeSize(); i++) {
			if (!child.containsCity(parent2.getCity(i))) { // jezeli dziecko nie posiada miasta 2 rodzica
				for (int j = 0; j < child.routeSize(); j++) { // znajdz miejsce wstawienia 2 miasta
					if (child.getCity(j) == null) {
						child.setCity(j, parent2.getCity(i)); // odziedzicz miasto po 2 rodzicu
						break;
					}
				}
			}
		}
		return child;
	}

	private static void mutate(Route route, double mutationRate) { // mutowanie osobnika

		for (int i = 0; i < route.routeSize(); i++) { // przejdz po wszystkich miastach
			if (Math.random() < mutationRate) { // losuj mozliwosc mutacji
				int j = (int) (route.routeSize() * Math.random()); // wylosuj pozycje drugiego miasta
				City city1 = route.getCity(i);
				City city2 = route.getCity(j);
				route.setCity(j, city1); // zamien miasta pozycjami
				route.setCity(i, city2);
			}
		}

	}

	private static Route chooseParents(Population pop) { // wybor najlepszego rodzica do krzozowania
		int parents = (int) Math.round(pop.populationSize() * potencialParents); // wybor ilosci rodzicow
		if (parents < 2) // gdyby rodzicow bylo mniej niz 2
			parents = 2; // wybierz 2 rodzicow
		Population population = new Population(parents, false); // stworz nowa populacje rodzicow
		for (int i = 0; i < parents; i++) { // petla po wszystkich rodzicach
			int j = (int) (Math.random() * pop.populationSize()); // wylosuj rodzica
			population.saveRoute(i, pop.getRoute(j)); // dodaj rodzica do populacji rodzicow
		}
		Route best = population.getBest(); // wybierz najlepszego rodzica
		return best;
	}
}