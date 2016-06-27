package logical;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetJSON {
	
	public static String results(String folder){
		
		JSONObject jO = new JSONObject();
		JSONParser jP = new JSONParser();
		
		try {
			jO = (JSONObject) jP.parse(new FileReader(folder));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jO.toJSONString();
		
	}
	
	public static JSONObject json(String json){
		
		JSONObject jO = new JSONObject();

		
		try {
			jO = (JSONObject) new JSONParser().parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jO;
		
	}

}
