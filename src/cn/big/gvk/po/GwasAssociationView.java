package cn.big.gvk.po;

import java.util.List;

public class GwasAssociationView {
    private int gaId;
    private int traitId;
    private String traitName;
    private int genotypeId;
    private String varId;
    private String chrom;
    private int startPos;
    private int endPos;
    private String rval;
    private String pvalue;
    private String pmid;
    private String studyAcc;
    private String speciesCommonName;
    private String refversion;

    private List<GenotypeAnnotateGeneView>  genotypeAnnotateViewList;

    private String gwasReportGene;

    private int traitCount;


    public int getTraitCount() {
        return traitCount;
    }

    public void setTraitCount(int traitCount) {
        this.traitCount = traitCount;
    }

    public String getGwasReportGene() {
        return gwasReportGene;
    }

    public void setGwasReportGene(String gwasReportGene) {
        this.gwasReportGene = gwasReportGene;
    }

    public List<GenotypeAnnotateGeneView> getGenotypeAnnotateViewList() {
        return genotypeAnnotateViewList;
    }

    public void setGenotypeAnnotateViewList(List<GenotypeAnnotateGeneView> genotypeAnnotateViewList) {
        this.genotypeAnnotateViewList = genotypeAnnotateViewList;
    }

    public int getGenotypeId() {
        return genotypeId;
    }

    public void setGenotypeId(int genotypeId) {
        this.genotypeId = genotypeId;
    }

    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getSpeciesCommonName() {
        return speciesCommonName;
    }

    public void setSpeciesCommonName(String speciesCommonName) {
        this.speciesCommonName = speciesCommonName;
    }

    public String getRefversion() {
        return refversion;
    }

    public void setRefversion(String refversion) {
        this.refversion = refversion;
    }

    public int getGaId() {
        return gaId;
    }

    public void setGaId(int gaId) {
        this.gaId = gaId;
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

    public String getRval() {
        return rval;
    }

    public void setRval(String rval) {
        this.rval = rval;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getStudyAcc() {
        return studyAcc;
    }

    public void setStudyAcc(String studyAcc) {
        this.studyAcc = studyAcc;
    }
}
