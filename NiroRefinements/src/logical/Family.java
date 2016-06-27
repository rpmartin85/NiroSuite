package logical;

public class Family {

	private String name;
	private int cod;

	public Family(String name, int cod) {

		this.name = name;
		this.cod = cod;
	}
	
	public Family(int cod){
		this.cod = cod;
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
