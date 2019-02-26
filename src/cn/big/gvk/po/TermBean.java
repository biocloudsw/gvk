package cn.big.gvk.po;

public class TermBean {
    private int termId;
    private String acc;
    private String name;
    private String termType;
    private int isObsolete;
    private int isRoot;
    private int hasChildren;
    private int gvktraitCount;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public int getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(int isObsolete) {
        this.isObsolete = isObsolete;
    }

    public int getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(int isRoot) {
        this.isRoot = isRoot;
    }

    public int getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(int hasChildren) {
        this.hasChildren = hasChildren;
    }

    public int getGvktraitCount() {
        return gvktraitCount;
    }

    public void setGvktraitCount(int gvktraitCount) {
        this.gvktraitCount = gvktraitCount;
    }
}
