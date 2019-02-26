package cn.big.gvk.po;

public class TermDefinitionBean {
    private int tdId;
    private int termId;
    private String termDefinition;
    private String termComment;

    public int getTdId() {
        return tdId;
    }

    public void setTdId(int tdId) {
        this.tdId = tdId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermDefinition() {
        return termDefinition;
    }

    public void setTermDefinition(String termDefinition) {
        this.termDefinition = termDefinition;
    }

    public String getTermComment() {
        return termComment;
    }

    public void setTermComment(String termComment) {
        this.termComment = termComment;
    }
}
