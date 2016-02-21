package database;
import java.sql.*;
public class Something {

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false","root","password");
			Statement st = con.createStatement();
			String sql = "INSERT INTO mydb.product ( name, price, quantity)"
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
