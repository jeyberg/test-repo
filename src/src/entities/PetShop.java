package src.entities;

import java.sql.SQLException;
import java.util.Map;

import src.db.PetDAO;

public class PetShop {
	private static PetShop psSingleton = new PetShop();
	private Map<Integer, Pet> pets = null;
	private PetDAO petDao = new PetDAO();
	
	public static PetShop getInstance(){
		return psSingleton;
	}
	
	public Map<Integer, Pet> getPets() {
		return pets;
	}

	public void setPets(Map<Integer, Pet> pets) {
		this.pets = pets;
	}
	
	
	public PetShop(){
		petDao.connect();
	}
	
	
	public Pet[] getAllPets(){		
		try {
			pets = petDao.getAllPets();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pets.values().toArray(new Pet[0]);
	}
	
	public Pet getPetById(int id){
		Pet pet = null;
		try {
			pet = petDao.getPetById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}
	
	public void addPet(String species, String race, float price, int id){
		try {
			petDao.insertPet(species, race, price, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removePet(int id){
		try {
			petDao.deletePetById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		try{
			petDao.deleteAll();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
