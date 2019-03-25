package traveler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Traveler {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		String name;
		System.out.println("Podaj nazwe pliku z wspolrzednymi miast");
		name = sc.nextLine();
		//name = "miasta.txt";
		File file = new File(name);
		Scanner fsc = new Scanner(file);
		int x, y, difference;
		double w, z;
		boolean details;
		
		while (fsc.hasNextInt()) {
			x = fsc.nextInt();
			y = fsc.nextInt();
			Map.addCity(new City(x, y));
		}
		fsc.close();
		System.out.println("Podaj wielkosc populacji");
		x = sc.nextInt();
		System.out.println("Podaj ilosc iteracji");
		y = sc.nextInt();
		System.out.println("Podaj wspolczynnik krzyzowania");
		w = sc.nextDouble();
		System.out.println("Podaj wspolczynnik mutacji");
		z = sc.nextDouble();

		//x = 50;
		//y = 100;
		//w = 0.8;
		//z = 0.1;

		Population pop = new Population(x, true);
		System.out.println("Dystans po inicjalizacji: " + pop.getBest().getDistance());
		System.out.println("Trasa:");
		System.out.println(pop.getBest());
		System.out.println();
		difference = pop.getBest().getDistance();
		for (int i = 0; i < y; i++) {
			pop = GeneticAlgoritm.evolve(pop, w, z);
			System.out.println("Dystans po " + (i + 1) + " iteracji: " + pop.getBest().getDistance());
			System.out.println("Trasa:");
			System.out.println(pop.getBest());
			System.out.println();
		}
		System.out.println("Roznica pomiedzy pierwsza i finalna trasa: "+ (difference-pop.getBest().getDistance()));
		sc.close();
	}
}
