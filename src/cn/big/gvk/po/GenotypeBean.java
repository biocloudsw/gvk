package cn.big.gvk.po;

import java.util.List;

public class GenotypeBean {
    private int genotypeId;
    private String varId;
    private String chrom;
    private int startPos;
    private int endPos;
    private int genotypeCount;
    private int studyCount;
    private int traitCount; // association count
    private int publicationCount; //publication count

    private List<GenotypeAnnotateGeneView>  genotypeAnnotateGeneView;
    private String trait;
    private String reportTrait;


    //add 0326
    private String allele;
    private String maf;
    private String minorAllele;

    public String getAllele() {
        return allele;
    }

    public void setAllele(String allele) {
        this.allele = allele;
    }

    public String getMaf() {
        return maf;
    }

    public void setMaf(String maf) {
        this.maf = maf;
    }

    public String getMinorAllele() {
        return minorAllele;
    }

    public void setMinorAllele(String minorAllele) {
        this.minorAllele = minorAllele;
    }

    public int getPublicationCount() {
        return publicationCount;
    }

    public void setPublicationCount(int publicationCount) {
        this.publicationCount = publicationCount;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getReportTrait() {
        return reportTrait;
    }

    public void setReportTrait(String reportTrait) {
        this.reportTrait = reportTrait;
    }

    //genotype detail
    private List<TraitBean> traitBeanList; //trait and reported trait

    public List<TraitBean> getTraitBeanList() {
        return traitBeanList;
    }

    public void setTraitBeanList(List<TraitBean> traitBeanList) {
        this.traitBeanList = traitBeanList;
    }

    public int getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(int studyCount) {
        this.studyCount = studyCount;
    }

    public int getTraitCount() {
        return traitCount;
    }

    public void setTraitCount(int traitCount) {
        this.traitCount = traitCount;
    }

    public List<GenotypeAnnotateGeneView> getGenotypeAnnotateGeneView() {
        return genotypeAnnotateGeneView;
    }

    public void setGenotypeAnnotateGeneView(List<GenotypeAnnotateGeneView> genotypeAnnotateGeneView) {
        this.genotypeAnnotateGeneView = genotypeAnnotateGeneView;
    }

    public int getGenotypeCount() {
        return genotypeCount;
    }

    public void setGenotypeCount(int genotypeCount) {
        this.genotypeCount = genotypeCount;
    }

    public int getGenotypeId() {
        return genotypeId;
    }

    public void setGenotypeId(int genotypeId) {
        this.genotypeId = genotypeId;
    }

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getChrom() {
        return chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }
}
