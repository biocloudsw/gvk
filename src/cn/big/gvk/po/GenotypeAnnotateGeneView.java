package cn.big.gvk.po;

public class GenotypeAnnotateGeneView {
    private int conseqId;
    private int gId;
    private String mapGeneId;
    private String mapGeneChrom;
    private int mapGeneStart;
    private int mapGeneEnd;
    private int gentoypeId;
    private String conseqtype;
    private String effect;
    private String traitName;
    private String pmid;
    private String commonName;
    private String varId;

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getMapGeneChrom() {
        return mapGeneChrom;
    }

    public void setMapGeneChrom(String mapGeneChrom) {
        this.mapGeneChrom = mapGeneChrom;
    }

    public int getMapGeneStart() {
        return mapGeneStart;
    }

    public void setMapGeneStart(int mapGeneStart) {
        this.mapGeneStart = mapGeneStart;
    }

    public int getMapGeneEnd() {
        return mapGeneEnd;
    }

    public void setMapGeneEnd(int mapGeneEnd) {
        this.mapGeneEnd = mapGeneEnd;
    }

    public int getConseqId() {
        return conseqId;
    }

    public void setConseqId(int conseqId) {
        this.conseqId = conseqId;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getMapGeneId() {
        return mapGeneId;
    }

    public void setMapGeneId(String mapGeneId) {
        this.mapGeneId = mapGeneId;
    }

    public int getGentoypeId() {
        return gentoypeId;
    }

    public void setGentoypeId(int gentoypeId) {
        this.gentoypeId = gentoypeId;
    }

    public String getConseqtype() {
        return conseqtype;
    }

    public void setConseqtype(String conseqtype) {
        this.conseqtype = conseqtype;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

}
