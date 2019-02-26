package cn.big.gvk.po;

public class MapGeneBean {
    private int gid;
    private String mapGeneId;
    private String mapGeneChrom;
    private int mapGeneStart;
    private int mapGeneEnd;
    private String mapGeneSymbol;
    private String description;
    private int geneCount;

    // record count value
    private int traitCount;
    private int studyCount;
    private int publicationCount;


    public int getTraitCount() {
        return traitCount;
    }

    public void setTraitCount(int traitCount) {
        this.traitCount = traitCount;
    }

    public int getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(int studyCount) {
        this.studyCount = studyCount;
    }

    public int getPublicationCount() {
        return publicationCount;
    }

    public void setPublicationCount(int publicationCount) {
        this.publicationCount = publicationCount;
    }

    public int getGeneCount() {
        return geneCount;
    }

    public void setGeneCount(int geneCount) {
        this.geneCount = geneCount;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getMapGeneId() {
        return mapGeneId;
    }

    public void setMapGeneId(String mapGeneId) {
        this.mapGeneId = mapGeneId;
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

    public String getMapGeneSymbol() {
        return mapGeneSymbol;
    }

    public void setMapGeneSymbol(String mapGeneSymbol) {
        this.mapGeneSymbol = mapGeneSymbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
