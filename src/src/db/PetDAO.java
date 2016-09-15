package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import src.entities.Pet;

public class PetDAO {

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resSet = null;

	// Connection data
	private final String DBURL = "jdbc:postgresql://localhost:5432/petshop";
	private final String USER = "postgres";
	private final String PW = "basel1234!";

	// SQL statements
	private final String SELECTALL = "select * from public.pet";
	private final String GETBYID = "select * from public.pet where idpet=";
	private final String INSERT = "insert into public.pet (`idpet`,`species`,`race`,`price`)values";
	private final String DELETEBYID = "delete from public.pet where idpet=";
	private final String DELETEALL = "delete from public.pet";

	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DBURL, USER, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAll() {
		try {
			if (resSet != null) {
				resSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, Pet> getAllPets() throws SQLException {
		Map<Integer, Pet> pets = null;
		try {
			statement = conn.createStatement();
			resSet = statement.executeQuery(SELECTALL);
			pets = toMap(resSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resSet.close();
			statement.close();
		}

		return pets;
	}

	public Pet getPetById(int id) throws SQLException {
		Pet pet = null;
		try {
			statement = conn.createStatement();
			resSet = statement.executeQuery(GETBYID + id);
			pet = toPet(resSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resSet.close();
			statement.close();
		}

		return pet;
	}

	public void insertPet(String species, String race, float price, int id) throws SQLException {
		try {
			statement = conn.createStatement();
			statement.executeUpdate(INSERT + "( " + id + ",'" + species + "','" + race + "'," + price + ")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
		}
	}

	public void deletePetById(int id) throws SQLException {
		try {
			statement = conn.createStatement();
			statement.executeUpdate(DELETEBYID + id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
		}
	}

	public void deleteAll() throws SQLException {
		try {
			statement = conn.createStatement();
			statement.executeUpdate(DELETEALL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
		}
	}

	private Map<Integer, Pet> toMap(ResultSet resSet) {
		Map<Integer, Pet> pets = new HashMap<Integer, Pet>();

		try {
			while (resSet.next()) {
				String species = resSet.getString("species");
				String race = resSet.getString("race");
				float price = resSet.getFloat("price");
				int id = resSet.getInt("idpet");
				pets.put(id, new Pet(species, race, price, id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pets;
	}

	private Pet toPet(ResultSet resSet) {
		Pet pet = null;
		try {
			if (resSet.next()) {
				String species = resSet.getString("species");
				String race = resSet.getString("race");
				float price = resSet.getFloat("price");
				int id = resSet.getInt("idpet");
				pet = new Pet(species, race, price, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pet;
	}
}
