package cn.big.gvk.po.tree;
/**************************************
 * this is used to store go term
 * @author lenovo
 *
 */
public class GoTermBean {
	private int gotermid;
	private String goterm;
	private String gotermtype;
	private String goacc;
	private int goisobsolete;
	private int goisroot;
	private int goisrelation;
	private String gorelation;
	private int hasChildren;
	private String geneCount;
	private String geneId;
	private String childNode;

	private String goParentTerm;
	private int associationCount;
	private int studyCount;
	private int paperCount;
	private int traitid;

	public int getTraitid() {
		return traitid;
	}

	public void setTraitid(int traitid) {
		this.traitid = traitid;
	}

	public int getAssociationCount() {
		return associationCount;
	}

	public void setAssociationCount(int associationCount) {
		this.associationCount = associationCount;
	}

	public int getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(int studyCount) {
		this.studyCount = studyCount;
	}

	public int getPaperCount() {
		return paperCount;
	}

	public void setPaperCount(int paperCount) {
		this.paperCount = paperCount;
	}

	public String getGoParentTerm() {
		return goParentTerm;
	}

	public void setGoParentTerm(String goParentTerm) {
		this.goParentTerm = goParentTerm;
	}

	public String getGeneId() {
		return geneId;
	}
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}
	public int getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(int hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
	public String getGeneCount() {
		return geneCount;
	}
	public void setGeneCount(String geneCount) {
		this.geneCount = geneCount;
	}
	public String getGorelation() {
		return gorelation;
	}
	public void setGorelation(String gorelation) {
		this.gorelation = gorelation;
	}
	public int getGoisobsolete() {
		return goisobsolete;
	}
	public void setGoisobsolete(int goisobsolete) {
		this.goisobsolete = goisobsolete;
	}
	public int getGotermid() {
		return gotermid;
	}
	public void setGotermid(int gotermid) {
		this.gotermid = gotermid;
	}
	public String getGoterm() {
		return goterm;
	}
	public void setGoterm(String goterm) {
		this.goterm = goterm;
	}
	public String getGotermtype() {
		return gotermtype;
	}
	public void setGotermtype(String gotermtype) {
		this.gotermtype = gotermtype;
	}
	public String getGoacc() {
		return goacc;
	}
	public void setGoacc(String goacc) {
		this.goacc = goacc;
	}
	public int getGoisroot() {
		return goisroot;
	}
	public void setGoisroot(int goisroot) {
		this.goisroot = goisroot;
	}
	public int getGoisrelation() {
		return goisrelation;
	}
	public void setGoisrelation(int goisrelation) {
		this.goisrelation = goisrelation;
	}
	
	
}
