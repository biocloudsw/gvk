package cn.big.gvk.po.tree;

public class GoGeneBean {
	private int ggid;
	private String geneName;
	private String geneAlias;
	private String chrom;
	private int chromStart;
	private int chromEnd;
	private String strand;
	private String goAcc;
	private String goName;
	private String goDefinition;
	private String goEcode;
	private String goDomain;
	
	private int rescount;
	private int gocount;
	
	
	
	
	
	public int getGocount() {
		return gocount;
	}
	public void setGocount(int gocount) {
		this.gocount = gocount;
	}
	public String getGoName() {
		return goName;
	}
	public void setGoName(String goName) {
		this.goName = goName;
	}
	public String getGeneAlias() {
		return geneAlias;
	}
	public void setGeneAlias(String geneAlias) {
		this.geneAlias = geneAlias;
	}

	public String getChrom() {
		return chrom;
	}
	public void setChrom(String chrom) {
		this.chrom = chrom;
	}
	public int getChromStart() {
		return chromStart;
	}
	public void setChromStart(int chromStart) {
		this.chromStart = chromStart;
	}
	public int getChromEnd() {
		return chromEnd;
	}
	public void setChromEnd(int chromEnd) {
		this.chromEnd = chromEnd;
	}
	public String getStrand() {
		return strand;
	}
	public void setStrand(String strand) {
		this.strand = strand;
	}
	public int getGgid() {
		return ggid;
	}
	public void setGgid(int ggid) {
		this.ggid = ggid;
	}
	public String getGeneName() {
		return geneName;
	}
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	public String getGoAcc() {
		return goAcc;
	}
	public void setGoAcc(String goAcc) {
		this.goAcc = goAcc;
	}
	public String getGoDefinition() {
		return goDefinition;
	}
	public void setGoDefinition(String goDefinition) {
		this.goDefinition = goDefinition;
	}
	public String getGoEcode() {
		return goEcode;
	}
	public void setGoEcode(String goEcode) {
		this.goEcode = goEcode;
	}
	public String getGoDomain() {
		return goDomain;
	}
	public void setGoDomain(String goDomain) {
		this.goDomain = goDomain;
	}
	public int getRescount() {
		return rescount;
	}
	public void setRescount(int rescount) {
		this.rescount = rescount;
	}
	
	
	
}
