package src.services;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.*;

import src.entities.Pet;
import src.entities.PetShop;

@Path("shop")
public class ShopService {
	private PetShop shop = PetShop.getInstance();
	
	@GET
	@Path("browse")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"employer","employee"})
	public Pet[] getPetsArray(){
		return shop.getAllPets();
	}
	
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@RolesAllowed("employer")
	public void addPet(@FormParam("species") String species, @FormParam("race") String race, @FormParam("price") float price, @FormParam("id") int id){
		shop.addPet(species, race, price, id);
	}
	
	
	@DELETE
	@Path("delete/all")
	@RolesAllowed("employer")
	public void deletePets(){
		shop.deleteAll();
	}
	
	
	
	
	
}
