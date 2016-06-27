package logical;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteVCF {

	public Vcf v;
	private static ArrayList<LineVCF> l;
	
	
	public static void write(Vcf v, String path, String st) throws IOException{
		
		String data = v.getData();
		data = data.replace("\n", "");
		path = path + data + "_NIRO.vcf";
		
		FileWriter file = new FileWriter(path);
		PrintWriter write = new PrintWriter(file);

		write.print(v.getHeader());
		write.print(v.getHeader2());
		
		write.print(v.getAllLines());
		
		data = null;
		
		path = null;
			
		file.flush();
		
		file.close();
		
		write.flush();
		
		write.close();
		
		write = null;
		
		file = null;
	}
	
	
	public static void write(Vcf v, String path) throws IOException{
		
		l = v.getLine();
		String data = v.getData();
		data = data.replace("\n", "");
		path = path + data + "_NIRO.vcf";
		
		FileWriter file = new FileWriter(path);
		PrintWriter write = new PrintWriter(file);

		write.print(v.getHeader());
		write.print(v.getHeader2());

		for (int i = 0; i < l.size(); i++) {
			write.println(l.get(i).toString());
		}
		
		data = null;
		
		path = null;
		
		
		
		l = null;

		file.flush();
		
		file.close();
		
		write.flush();
		
		write.close();
		
		write = null;
		
		file = null;

		// v.printLine();

	}
		
				
		
		
	/*

	public static void main(String args[]) throws IOException {

		v = ReadVCF.read("/Users/renanmartin/Desktop/teste.vcf");

		l = v.getLine();

		String data = v.getData();
		data = data.replace("\n", "");

		path = "/Users/renanmartin/Desktop/" + data + "_NIRO.vcf";

		FileWriter file = new FileWriter(path);
		PrintWriter write = new PrintWriter(file);

		write.print(v.getHeader());
		write.print(v.getHeader2());

		for (int i = 0; i < l.size(); i++) {
			write.println(l.get(i).toString());
		}

		file.close();

		// v.printLine();

	}
	
	*/

}
