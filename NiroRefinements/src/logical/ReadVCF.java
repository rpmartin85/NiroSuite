package logical;

import java.io.*;
import java.util.ArrayList;

public class ReadVCF {

//	private String path;
	private static Vcf v;
	private static String header;
	private static String data;
	private static String header2;
	private static ArrayList<LineVCF> l;

	public static Vcf read(String path) {

		// The name of the file to open.
	//	path = "/Users/renanmartin/Desktop/teste.vcf";

		// This will reference one line at a time
		String line = null;
		header = "";
		v = new Vcf();
		l = new ArrayList<LineVCF>();

		try {

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(path);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			header2 = "";
			boolean flag = false;

			// find the line of Header
			while ((line = bufferedReader.readLine()) != null) {

				int pos = line.indexOf("#CHROM");

				// get the header of vcf
				if (pos == -1 && !flag) {

					header = header + line + "\n";
					v.setHeader(header);

				}

				// get the title columns line and data
				if (pos != -1) {

					header2 = line + "\n";
					flag = true;
					String field[] = header2.split("\t");
					data = field[field.length - 1];
					v.setHeader2(header2);
					v.setData(data);

				}

				// get each line of vcf
				if (flag && pos == -1) {

					LineVCF lvcf = new LineVCF();

					String field[] = line.split("\t");

					lvcf.setChrom(field[0]);
					lvcf.setPos(Integer.parseInt(field[1]));
					lvcf.setId(field[2]);
					lvcf.setRef(field[3]);
					lvcf.setAlt(field[4]);
					lvcf.setQual(Double.parseDouble(field[5]));
					lvcf.setFilter(field[6]);
					lvcf.setInfo(field[7]);
					lvcf.setFormat(field[8]);
					lvcf.setData(field[9]);
					

					// System.out.println(lvcf.toString());

					l.add(lvcf);

				}

			}
			// System.out.print(data + "\n\n\n" + header + header2);

			v.setLine(l);

			// System.out.println(l.size());

			// v.printLine();
			// System.out.println(header + "\n" + body);

			// Always close files.
			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + path + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + path + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return v;
	}

	public String getData() {
		return data;
	}

}
