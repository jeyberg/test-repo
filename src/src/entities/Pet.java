package src.entities;

public class Pet {
	private String species;
	private String race;
	private float price;
	private int id;

	public Pet(String species, String race, float price, int id) {
		setSpecies(species);
		setRace(race);
		setPrice(price);
		setId(id);
	}

	public String getSpecies() {
		return species;
	}

	public String getRace() {
		return race;
	}

	public float getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id != 0)
			this.id = id;
	}

	public void setSpecies(String species) {
		if (species != null && !species.equals(""))
			this.species = species;
	}

	public void setRace(String race) {
		if (race != null && !race.equals(""))
			this.race = race;
	}

	public void setPrice(float price) {
		if (price != 0)
			this.price = price;
	}

	@Override
	public String toString() {
		return "Pet [species=" + species + ", race=" + race + ", price=" + price + ", id=" + id + "]";
	}
}
