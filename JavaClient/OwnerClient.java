package src.client;

import java.util.Scanner;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OwnerClient {
	private final String BASEURL = "http://localhost:8080/TestShop/rest/shop";
	Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OwnerClient owner = new OwnerClient();
		System.out.println("Welcome to the TestShopClient. Just type a command. Type 'help' to see available commands");
		owner.menue();
	}

	private void browsePets() {
		String pets = client.target(BASEURL).path("browse").request().get(String.class);
		System.out.println(pets);
	}

	private void browsePetById(int id) {
		String pet = client.target(BASEURL).path("browse/{id}").resolveTemplate("id", id).request().get(String.class);
		System.out.println(pet);
	}

	private void addPet(String species, String race, float price, int id) {
		String pet = "species=" + species + "&race=" + race + "&price=" + price + "&id=" + id;
		Response resp = client.target(BASEURL).path("add").request()
				.post(Entity.entity(pet, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

		if (resp.getStatus() == 204) {
			System.out.println("Pet added successfully");
		} else {
			System.out.println("Something went wrong\n" + resp.getStatus());
		}
	}

	private void deleteAllPets() {
		Response resp = client.target(BASEURL).path("/delete/all").request().delete();
		if (resp.getStatus() == 204) {
			System.out.println("All Pets have been removed from the list");
		}
		browsePets();
	}

	private void deletePetById(int id) {
		Response resp = client.target(BASEURL).path("delete/{id}").resolveTemplate("id", id).request().delete();

		if (resp.getStatus() == 204) {
			System.out.println("Pet " + id + " deleted successfully");
		} else {
			System.out.println("Something went wrong\n" + resp.getStatus());
		}
		browsePets();
	}

	private void menue() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String command = scanner.nextLine();
			try {
				switch (command) {
				case "browse":
					browsePets();
					break;
				case "browseid":
					System.out.println("Please input the id of the pet to browse: ");
					int id1 = Integer.parseInt(scanner.nextLine());
					browsePetById(id1);
					break;
				case "add":
					System.out.println("Please input parameters: species, race, price, id");
					String species = scanner.nextLine();
					String race = scanner.nextLine();
					float price = Float.parseFloat(scanner.nextLine());
					int id2 = Integer.parseInt(scanner.nextLine());
					addPet(species, race, price, id2);
					break;
				case "deleteall":
					System.out.println("Delete all pets? y/n");
					String ans = scanner.nextLine();
					if (ans.equals("y")) {
						deleteAllPets();
					} else {
						System.out.println("Command canceled");
					}
					break;
				case "deleteid":
					System.out.println("Please input the id of the pet to delete: ");
					int id3 = Integer.parseInt(scanner.nextLine());
					System.out.println("Delete pet " + id3 + "? y/n");
					String ans2 = scanner.nextLine();
					if (ans2.equals("y")) {
						deletePetById(id3);
					} else {
						System.out.println("Command canceled");
					}
					break;
				case "help":
					System.out.println(
							"Available commands:\n" + "'browse' => show all pets\n" + "'browseid' => show pet by id\n"
									+ "'add' => add new pet\n" + "'deleteall' => delete all pets\n"
									+ "'deleteid' => delete pet by id\n" + "'quit' => close the program\n");
					break;
				case "quit":
					System.out.println("Closing programm");
					scanner.close();
					return;
				default:
					System.out.println("No such command: "+command);
				}
			} catch (Exception e) {
				System.err.println("ERROR - COMMAND CANCELED");
			}

		}
	}

}
