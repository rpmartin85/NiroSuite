package logical;

public class LineVCF {

	private String chrom;
	private long pos;
	private String id;
	private String ref;
	private String alt;
	private double qual;
	private String filter;
	private String info;
	private String format;
	private String data;


	public LineVCF(String chrom, long pos, String id, String ref, String alt, double qual, String filter, String info,
			String format, String data) {
		this.chrom = chrom;
		this.pos = pos;
		this.id = id;
		this.ref = ref;
		this.alt = alt;
		this.qual = qual;
		this.filter = filter;
		this.info = info;
		this.format = format;
		this.data = data;

	}

	

	public LineVCF() {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public double getQual() {
		return qual;
	}

	public void setQual(double qual) {
		this.qual = qual;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return chrom + "\t" + pos + "\t" + id + "\t" + ref + "\t" + alt + "\t" + qual + "\t" + filter + "\t" + info
				+ "\t" + format + "\t" + data;
	}

}
