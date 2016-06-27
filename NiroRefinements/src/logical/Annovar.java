package logical;

public class Annovar {

	private int cod;
	private String func;
	private String gene;
	private String geneDetail;
	private String exonicFunc;
	private String aaChange;
	private String numDBSP;
	private String numPMID;
	private double sift;
	private double polyphen;
	private double mutTaster;
	private String pathogenicity;

	public Annovar(int cod, String func, String gene, String geneDetail, String exonicFunc, String aaChange,
			String numDBSP, String numPMID, double sift, double polyphen, double mutTaster, String pathogenicity) {
		this.cod = cod;
		this.func = func;
		this.gene = gene;
		this.geneDetail = geneDetail;
		this.exonicFunc = exonicFunc;
		this.aaChange = aaChange;
		this.numDBSP = numDBSP;
		this.numPMID = numPMID;
		this.sift = sift;
		this.polyphen = polyphen;
		this.mutTaster = mutTaster;
		this.pathogenicity = pathogenicity;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getGene() {
		return gene;
	}

	public void setGene(String gene) {
		this.gene = gene;
	}

	public String getGeneDetail() {
		return geneDetail;
	}

	public void setGeneDetail(String geneDetail) {
		this.geneDetail = geneDetail;
	}

	public String getExonicFunc() {
		return exonicFunc;
	}

	public void setExonicFunc(String exonicFunc) {
		this.exonicFunc = exonicFunc;
	}

	public String getAaChange() {
		return aaChange;
	}

	public void setAaChange(String aaChange) {
		this.aaChange = aaChange;
	}

	public String getNumDBSP() {
		return numDBSP;
	}

	public void setNumDBSP(String numDBSP) {
		this.numDBSP = numDBSP;
	}

	public String getNumPMID() {
		return numPMID;
	}

	public void setNumPMID(String numPMID) {
		this.numPMID = numPMID;
	}

	public double getSift() {
		return sift;
	}

	public void setSift(double sift) {
		this.sift = sift;
	}

	public double getPolyphen() {
		return polyphen;
	}

	public void setPolyphen(double polyphen) {
		this.polyphen = polyphen;
	}

	public double getMutTaster() {
		return mutTaster;
	}

	public void setMutTaster(double mutTaster) {
		this.mutTaster = mutTaster;
	}

	public String getPathogenicity() {
		return pathogenicity;
	}

	public void setPathogenicity(String pathogenicity) {
		this.pathogenicity = pathogenicity;
	}

}
