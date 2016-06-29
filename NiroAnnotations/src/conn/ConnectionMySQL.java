package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONObject;
import logical.*;


public class ConnectionMySQL {

	private Connection c;
	private String serverName;
	private String dataBase;
	private String port;
	private String url;
	int totalAmount;
	int refinedAmount;


	public ConnectionMySQL() {
		openConnection();
	}
	
	
	public Connection getConn(){
		return c;
	}

	

	public String getURL() {

		JSONObject jO = new JSONObject();
		jO = ConfigDBNiro.getJson();
		serverName = jO.get("serverName").toString();
		dataBase = jO.get("dataBase").toString();
		port = jO.get("port").toString();
		url = "jdbc:mysql://" + serverName + ":" + port + "/" + dataBase + "?useSSL=false";
		return url;
	}

	public void close() {
		try {

			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	private void openConnection() {
		try {
			ConnDataOut cDO = new ConnDataOut();
			c = DriverManager.getConnection(getURL(), cDO.getUser(), cDO.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ResultSet select(String sql) {
		try {

			Statement stm = c.createStatement();
			ResultSet result = stm.executeQuery(sql);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*

	private boolean connect(String sql) {
		try {

			Statement stm = c.createStatement();
			stm.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	*/
	
	public Omim getOmimPhenotype(String gene){
		
		return new Omim(getComments(gene), getPhenotype(gene));
		
		
	}
	
	public String getPhenotype(String gene){
		
		try{
			
			String sql = "SELECT phenotype_omim FROM OMIM WHERE gene_omim = '" + gene + "';";
			
			ResultSet rs = select(sql);
			
			rs.absolute(1);
			
			return rs.getObject(1).toString();
	
			
		} catch (Exception e) {
		//	e.printStackTrace();
			
			return "";
		}
		

	}
	
	
	public String getComments(String gene){
		
		try{
			
			String sql = "SELECT comments_omim FROM OMIM WHERE gene_omim = '" + gene + "';";
			
			ResultSet rs = select(sql);
			
			rs.absolute(1);
			
			return rs.getObject(1).toString();
			
			
		} catch (Exception e) {
		//	e.printStackTrace();
			
			return "";
		}
		

	}
	
	
	
	
}