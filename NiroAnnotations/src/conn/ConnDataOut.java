package conn;

import org.json.simple.JSONObject;
import logical.*;

public class ConnDataOut {

	private String user;
	private String password;

	public ConnDataOut() {
		
		JSONObject jO = new JSONObject();
		jO = ConfigDBNiro.getJson();
		user = jO.get("user").toString();
		password = jO.get("password").toString();		
		
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
