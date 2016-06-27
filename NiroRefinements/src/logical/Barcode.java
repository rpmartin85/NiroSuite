package logical;

public class Barcode {

	private int cod;
	private String name;
	private double cover20x;
	private Chip c;
	private String header;
	private Patient pa;
	
	//used only to find the corrected barcode
	private String acro;

	public Barcode(int cod, String name, double cover20x, Chip c, String header, Patient pa) {

		this.cod = cod;
		this.name = name;
		this.cover20x = cover20x;
		this.c = c;
		this.header = header;
		this.pa = pa;
	}
	
	public Patient getPa() {
		return pa;
	}

	public void setPa(Patient pa) {
		this.pa = pa;
	}

	public Barcode(){

		
		
	}

	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCover20x() {
		return cover20x;
	}

	public void setCover20x(double cover20x) {
		this.cover20x = cover20x;
	}


	
	public Chip getC() {
		return c;
	}

	public void setC(Chip c) {
		this.c = c;
	}
	
	

	
	
}
