package logical;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetBarcode {
	
	
	
	

	public static Barcode[] barcode(String resultsCoverAna) {

		JSONParser jPRCA = new JSONParser();

		JSONObject jORCA = new JSONObject();
		try {
			jORCA = (JSONObject) jPRCA.parse(resultsCoverAna);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get the json object barcode from the upper node

		JSONObject barcode = (JSONObject) jORCA.get("barcodes");

		barcode.size();

		Barcode bcJO[] = new Barcode[barcode.size()];

		String start = "{\"";

		String div = "\":";

		String divObj = "},\"";

		String subS = barcode.toJSONString();

		for (int i = 0; i < barcode.size(); i++) {

			bcJO[i] = new Barcode();

			if (i == 0) {

				int startObjKey = barcode.toJSONString().indexOf(start) + start.length();

				int endObjKey = barcode.toJSONString().indexOf(div);

				bcJO[i].setName(barcode.toJSONString().substring(startObjKey, endObjKey));

				int divObjKey = barcode.toJSONString().indexOf(divObj) + divObj.length();

				int lSubS = subS.length();

				subS = subS.substring(divObjKey, lSubS);

			}

			else {

				int lSubS = subS.length();

				int endObjKey = subS.indexOf(div);

				bcJO[i].setName(subS.substring(0, endObjKey));

				int divObjKey = subS.indexOf(divObj) + divObj.length();

				subS = subS.substring(divObjKey, lSubS);

			}

			JSONObject temp = (JSONObject) barcode.get(bcJO[i].getName());

			String x20 = "";

			x20 = (String) temp.get("Target base coverage at 20x");

			x20 = x20.substring(0, x20.length() - 1);
			
			bcJO[i].setCover20x(Double.parseDouble(x20));
			
			String acro = "";
			
			acro = (String) temp.get("Sample Name");
			
			bcJO[i].setAcro(acro);
						
		}

		return bcJO;

	}

}
