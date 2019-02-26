package cn.big.gvk.po;

import java.util.List;

public class TermInformationBean {

    private int termId;
    private String traitName;
    private int traitId;
    private String acc;
    private String termName;
    private String termType;
    private String termDefinition;
    private String termComment;
    private String  termSynonym;
    private String btoId;
    private String mapTerms;


    private int traitCount;
    private int studyCount;
    private int publicationCount;


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

    public String getBtoId() {
        return btoId;
    }

    public void setBtoId(String btoId) {
        this.btoId = btoId;
    }

    public String getMapTerms() {
        return mapTerms;
    }

    public void setMapTerms(String mapTerms) {
        this.mapTerms = mapTerms;
    }

    public int getTraitCount() {
        return traitCount;
    }

    public void setTraitCount(int traitCount) {
        this.traitCount = traitCount;
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

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

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

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
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

    public String getTermSynonym() {
        return termSynonym;
    }

    public void setTermSynonym(String termSynonym) {
        this.termSynonym = termSynonym;
    }
}
