package src.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import src.entities.Pet;
import src.entities.PetShop;

@Path("shop")
public class PetService {
	private PetShop shop = PetShop.getInstance();
	
	@GET
	@Path("browse/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pet getPetById(@PathParam("id")int id){
		return shop.getPetById(id);
	}
	
	@DELETE
	@Path("delete/{id}")
	public void deletePetById(@PathParam("id")int id){
		shop.removePet(id);
	}
	
}
