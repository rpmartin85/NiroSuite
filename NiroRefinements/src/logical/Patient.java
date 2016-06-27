package logical;

public class Patient {

	private String name;
	private String acro;
	private int cod;
	private Family f;
	private Project p[];
	private Chip c[];

	public Patient(String name, String acro, int cod, Family f, Project[] p) {

		this.name = name;
		this.acro = acro;
		this.cod = cod;
		this.f = f;
		this.p = p;
	}
	
	public Patient(int cod, String name, String acro, Family f){
		this.name = name;
		this.acro = acro;
		this.cod = cod;
		this.f = f;

	}
	
	public Patient(String name, String acro, int cod, Family f, Project[] p, Chip[] c){
		
		this.name = name;
		this.acro = acro;
		this.cod = cod;
		this.f = f;
		this.p = p;
		this.c = c;
		
		
		
	}
	
	
	public Chip[] getC() {
		return c;
	}

	public void setC(Chip[] c) {
		this.c = c;
	}

	public Patient (String name, int cod){
		
		this.name = name;
		this.cod = cod;
		
	}
	
	
	
	public Patient(int cod){
		this.cod = cod;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Family getF() {
		return f;
	}

	public void setF(Family f) {
		this.f = f;
	}

	public Project[] getP() {
		return p;
	}

	public void setP(Project[] p) {
		this.p = p;
	}

}
