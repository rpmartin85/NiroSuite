package logical;

public class Variation {

	private int cod;
	private String chrom;
	private long pos;
	private String refAlelle;
	private String altAlelle;
	private String info;
	private long quality[];
	private String format[];
	private String data[];
	private String genotype[];
	private Barcode b[];
	private Annovar a;

	public Variation(int cod, String chrom, long pos, String refAlelle, String altAlelle, String info, long[] quality,
			String[] format, String[] data, String[] genotype, Barcode[] b, Annovar a) {
		this.cod = cod;
		this.chrom = chrom;
		this.pos = pos;
		this.refAlelle = refAlelle;
		this.altAlelle = altAlelle;
		this.info = info;
		this.quality = quality;
		this.format = format;
		this.data = data;
		this.genotype = genotype;
		this.b = b;
		this.a = a;
	}
	
	public Variation(int cod, String chrom, long pos, String refAlelle, String altAlelle, String info) {
		this.cod = cod;
		this.chrom = chrom;
		this.pos = pos;
		this.refAlelle = refAlelle;
		this.altAlelle = altAlelle;
		this.info = info;
		
	}

	public Variation(int cod, String chrom, long pos, String refAlelle, String altAlelle, String info, long[] quality,
			String[] format, String[] data, String[] genotype, Barcode[] b) {

		this.cod = cod;
		this.chrom = chrom;
		this.pos = pos;
		this.refAlelle = refAlelle;
		this.altAlelle = altAlelle;
		this.info = info;
		this.quality = quality;
		this.format = format;
		this.data = data;
		this.genotype = genotype;
		this.b = b;
		a = null;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getChrom() {
		return chrom;
	}

	public void setChrom(String chrom) {
		this.chrom = chrom;
	}

	public long getPos() {
		return pos;
	}

	public void setPos(long pos) {
		this.pos = pos;
	}

	public String getRefAlelle() {
		return refAlelle;
	}

	public void setRefAlelle(String refAlelle) {
		this.refAlelle = refAlelle;
	}

	public String getAltAlelle() {
		return altAlelle;
	}

	public void setAltAlelle(String altAlelle) {
		this.altAlelle = altAlelle;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long[] getQuality() {
		return quality;
	}

	public void setQuality(long[] quality) {
		this.quality = quality;
	}

	public String[] getFormat() {
		return format;
	}

	public void setFormat(String[] format) {
		this.format = format;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public String[] getGenotype() {
		return genotype;
	}

	public void setGenotype(String[] genotype) {
		this.genotype = genotype;
	}

	public Barcode[] getB() {
		return b;
	}

	public void setB(Barcode[] b) {
		this.b = b;
	}

	public Annovar getA() {
		return a;
	}

	public void setA(Annovar a) {
		this.a = a;
	}

}
