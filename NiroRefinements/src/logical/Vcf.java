package logical;

import java.util.ArrayList;

public class Vcf {

	private String header;
	private String data;
	private String header2;
	private ArrayList<LineVCF> line;
	private int totalAmount;
	private int refinedAmount;
	private String allLines;

	public String getAllLines() {
		return allLines;
	}

	public void setAllLines(String allLines) {
		this.allLines = allLines;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getRefinedAmount() {
		return refinedAmount;
	}

	public void setRefinedAmount(int refinedAmount) {
		this.refinedAmount = refinedAmount;
	}
	
	
	
	

	public Vcf(String header, String data, String header2, ArrayList<LineVCF> line) {
		this.header = header;
		this.data = data;
		this.header2 = header2;
		this.line = line;
	}
	
	
public Vcf(String header, String allLines){
		
		this.header = header.substring(0, header.indexOf("#CHROM"));
		header2 = header.substring(header.indexOf("#CHROM"), header.length());
		String field[] = header2.split("\t");
		data = field[field.length - 1];
		this.allLines = allLines;
		
		
		
	}
	
	
public Vcf(String header, ArrayList<LineVCF> line){
		
		this.header = header.substring(0, header.indexOf("#CHROM"));
		header2 = header.substring(header.indexOf("#CHROM"), header.length());
		String field[] = header2.split("\t");
		data = field[field.length - 1];
		this.line = line;
		
		
		
	}

	public Vcf() {
		line = new ArrayList<LineVCF>();
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHeader2() {
		return header2;
	}

	public void setHeader2(String header2) {
		this.header2 = header2;
	}

	public ArrayList<LineVCF> getLine() {
		return line;
	}

	public void setLine(ArrayList<LineVCF> line) {
		this.line = line;
	}

	public void printLine() {

		for (int i = 0; i < line.size(); i++) {
			System.out.println(line.get(i).toString());
		}

	}

	/*
	 * 
	 * public static void main (String args[]){
	 * 
	 * line = new ArrayList<LineVCF>(); LineVCF l = new LineVCF();
	 * l.setChrom("chr1"); l.setPos(865694); l.setId("."); l.setRef("C");
	 * l.setAlt("T"); l.setQual(791.53); l.setFilter("PASS"); l.setInfo(
	 * "AF=0.586466;AO=156;DP=266;FAO=156;FDP=266;FR=.;FRO=110;FSAF=81;FSAR=75;FSRF=60;FSRR=50;FWDB=0.00349401;FXX=0;HRUN=1;LEN=1;MLLD=71.717;QD=11.9027;RBI=0.00358675;REFB=0.0266303;REVB=-0.000810338;RO=110;SAF=81;SAR=75;SRF=60;SRR=50;SSEN=0;SSEP=0;SSSB=-0.0200794;STB=0.510868;STBP=0.68;TYPE=snp;VARB=-0.0193993;OID=.;OPOS=865694;OREF=C;OALT=T;OMAPALT=T"
	 * ); l.setFormat(
	 * "GT:GQ:DP:FDP:RO:FRO:AO:FAO:AF:SAR:SAF:SRF:SRR:FSAR:FSAF:FSRF:FSRR");
	 * l.setData(
	 * "0/1:99:266:266:110:110:156:156:0.586466:75:81:60:50:75:81:60:50");
	 * 
	 * line.add(l);
	 * 
	 * data = "PauloHenriqueBS_SN";
	 * 
	 * header2 = header2 + data + "\n";
	 * 
	 * System.out.print(header); System.out.print(header2);
	 * System.out.print(line.get(0).toString());
	 * 
	 * 
	 * }
	 */

}
