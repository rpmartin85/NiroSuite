package logical;

public class Gene {

	private int cod;
	private String chr;
	private String name;
	private int min;
	private int max;
	private char strand;

	public Gene(int cod, String chr, String name, int min, int max, char strand) {

		this.cod = cod;
		this.chr = chr;
		this.name = name;
		this.min = min;
		this.max = max;
		this.strand = strand;
	}

	public Gene(int cod, String name) {

		this.cod = cod;
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getChr() {
		return chr;
	}

	public void setChr(String chr) {
		this.chr = chr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public char getStrand() {
		return strand;
	}

	public void setStrand(char strand) {
		this.strand = strand;
	}

}
