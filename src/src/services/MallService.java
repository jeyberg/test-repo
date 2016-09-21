package src.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import src.entities.Pet;
import src.entities.PetShop;

@Path("/dog")
public class MallService extends ShopService{
	//private PetShop shop = PetShop.getInstance();

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Pet[] getPetsArray(){
		return super.getPetsArray();
	}
	
}
