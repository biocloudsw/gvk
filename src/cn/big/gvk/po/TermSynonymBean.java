package cn.big.gvk.po;

public class TermSynonymBean {
    private int tsId;
    private int termId;
    private String termSynonym;
    private String accSynonym;

    public int getTsId() {
        return tsId;
    }

    public void setTsId(int tsId) {
        this.tsId = tsId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermSynonym() {
        return termSynonym;
    }

    public void setTermSynonym(String termSynonym) {
        this.termSynonym = termSynonym;
    }

    public String getAccSynonym() {
        return accSynonym;
    }

    public void setAccSynonym(String accSynonym) {
        this.accSynonym = accSynonym;
    }
}
