package logical;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigNiro {
	
	
	private static String path = "src/file/niroSettings.json";
	private static String pathDefault = "src/file/niroSettingsDefault.json";
	private static JSONObject jO;
	
	public static void onStart() throws IOException, ParseException{
		
			
		
		try {
			
			JSONParser jP = new JSONParser();
			jO = (JSONObject) jP.parse(new FileReader(path));
			
		} catch (FileNotFoundException e) {
			
		
			JSONParser jPD = new JSONParser();
			JSONObject jOD = (JSONObject) jPD.parse(new FileReader(pathDefault));
			
			setJson(jOD);
			
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static JSONObject getJson(){
		
		try {
			jO = new JSONObject();
			JSONParser jP = new JSONParser();
			jO = (JSONObject) jP.parse(new FileReader(path));
			
			return jO;
			
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
		
		return null;
		
	}
	
public static JSONObject getJsonDefault(){
		
		try {
			jO = new JSONObject();
			JSONParser jP = new JSONParser();
			jO = (JSONObject) jP.parse(new FileReader(pathDefault));
			
			return jO;
			
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
		
		return null;
		
	}
	
	
	
	
	
	
	public static void setJson(JSONObject jO){
		
		
		FileWriter file;
		try {
			file = new FileWriter(path);
			PrintWriter write = new PrintWriter(file);

			String json = jO.toJSONString();
			
			write.print(json);
			write.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
