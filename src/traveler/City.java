package traveler;

public class City {
	int x; // wspolrzedne x miasta
	int y; // wspolrzedne y miasta

	public City() { // konstruktor domyslny
		this.x = 0;
		this.y = 0;
	}

	public City(int x, int y) { // konstruktor
		this.x = x;
		this.y = y;
	}

	public int getX() { // zwroc wspolrzedne x miasta
		return this.x;
	}

	public int getY() { // zwroc wspolrzedne y miasta
		return this.y;
	}

	public double distanceTo(City city) { // zwroc odleglosc do podanego miasta
		int x = Math.abs(getX() - city.getX()); // roznica wspolrzednych x
		int y = Math.abs(getY() - city.getY()); // roznica wspolrzednych y
		double distance = Math.sqrt((x * x) + (y * y)); // dystans to pierwiastek z sum kwadratow odleglosci
														// wspolrzednych

		return distance; // zwroc dystans
	}

	@Override
	public String toString() { // wypisanie wspolrzednych miasta
		return getX() + ", " + getY();
	}
}