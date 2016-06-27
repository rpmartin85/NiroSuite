package logical;

public class FileAprov {

	public final static String excel[] = { "xls", "xlsx", "xlsm", "xlsb", "xltx", "xltm", "xlt", "xml", "xlam", "xla",
			"xlw", "tsv" };
	public final static String vcf[] = { "vcf", "txt" };
	
	public final static String json[] = { "json", "txt" };

	public static boolean excel(String ext) {

		for (int i = 0; i < excel.length; i++) {
			if (ext.equals(excel[i]))
				return true;
		}
		return false;
	}

	public static boolean vcf(String ext) {
		for (int i = 0; i < vcf.length; i++) {
			if (ext.equals(vcf[i]))
				return true;
		}
		return false;
	}
	
	public static boolean json(String ext){
		
		for (int i = 0; i < json.length; i++) {
			if (ext.equals(json[i]))
				return true;
		}
		return false;
	}

}
