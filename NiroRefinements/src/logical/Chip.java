package logical;


public class Chip {

	private int cod;
	private String folder;
	private Patient p[];
	private String jO;
	private String type;
	
	public Chip(int cod){
		this.cod = cod;
	}

	public Chip(int cod, String folder, Patient[] p, String jO, String type) {

		this.cod = cod;
		this.folder = folder;
		this.p = p;
		this.jO = jO;
		this.type = type;
	}
	
	
	public Chip(int cod, String folder, String type){
		this.cod = cod;
		this.folder = folder;
		this.type = type;

		
	}
	
	public Chip(int cod, String folder, String type, String jO){
		this.cod = cod;
		this.folder = folder;
		this.type = type;
		this.jO = jO;
		
	}
	
	/*
	
	public Chip(int cod, String folder, String jO) {

		this.cod = cod;
		this.folder = folder;
		this.jO = jO;
	}
	
	*/
	
	public Chip(int cod, String folder) {

		this.cod = cod;
		this.folder = folder;
		jO = GetJSON.results(folder);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getjO() {
		return jO;
	}

	public void setjO(String jO) {
		this.jO = jO;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Patient[] getP() {
		return p;
	}

	public void setP(Patient[] p) {
		this.p = p;
	}

}
