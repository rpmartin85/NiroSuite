package logical;

public class Omim {
	
	private int cod;
	private int number;
	private String gene;
	private String geneName;
	private String comments;
	private String phenotype;
	
	
	
	
	
	public Omim(String comments, String phenotype) {
		this.comments = comments;
		this.phenotype = phenotype;
	}
	public Omim(int cod, int number, String gene, String geneName, String comments, String phenotype) {
		this.cod = cod;
		this.number = number;
		this.gene = gene;
		this.geneName = geneName;
		this.comments = comments;
		this.phenotype = phenotype;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getGene() {
		return gene;
	}
	public void setGene(String gene) {
		this.gene = gene;
	}
	public String getGeneName() {
		return geneName;
	}
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}
	
	
	

}
