package database;
import java.sql.*;
public class Something {
	//URL
	static final String DB_URL = "jdbc:mysql://localhost:3306/pizzeria?autoReconnect=true&useSSL=false";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement st = con.createStatement();
			String sql = "INSERT INTO pizzeria.product ( name, price, quantity)"
					+ "VALUES ('margarita', 9.00, 1)" ;
			st.execute(sql);
			System.out.println("Insert complete!");
			ResultSet myRs = st.executeQuery("select * from product");
			System.out.println("Here");
			while(myRs.next()){
				System.out.println(myRs.getString("name") + " " + myRs.getString("price")+ " " + myRs.getString("quantity"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
