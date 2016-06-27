package logical;

public class Project {

	private String name;
	private int cod;
	private int codUser;

	public Project(String name, int cod, int codUser) {

		this.name = name;
		this.cod = cod;
		this.codUser = codUser;

	}

	public Project(int cod, String name) {
		
		this.name = name;
		this.cod = cod;
	}

	public int getCodUser() {
		return codUser;
	}

	public void setCodUser(int codUser) {
		this.codUser = codUser;
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

}
