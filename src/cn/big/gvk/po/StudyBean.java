package cn.big.gvk.po;

public class StudyBean {
    private int studyId;
    private String studyAcc;
    private String environment;
    private String sampleSpot;
    private String sampleYear;
    private String sampleCondition;
    private String species;
    private String population;
    private String sampleSize;
    private String tissue;
    private String modelName;
    private int gaId;
    private String techName;
    private String pmid;
    private int gwasCount;
    private int studyCount;

    public int getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(int studyCount) {
        this.studyCount = studyCount;
    }

    public int getGwasCount() {
        return gwasCount;
    }

    public void setGwasCount(int gwasCount) {
        this.gwasCount = gwasCount;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public int getGaId() {
        return gaId;
    }

    public void setGaId(int gaId) {
        this.gaId = gaId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public String getStudyAcc() {
        return studyAcc;
    }

    public void setStudyAcc(String studyAcc) {
        this.studyAcc = studyAcc;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSampleSpot() {
        return sampleSpot;
    }

    public void setSampleSpot(String sampleSpot) {
        this.sampleSpot = sampleSpot;
    }

    public String getSampleYear() {
        return sampleYear;
    }

    public void setSampleYear(String sampleYear) {
        this.sampleYear = sampleYear;
    }

    public String getSampleCondition() {
        return sampleCondition;
    }

    public void setSampleCondition(String sampleCondition) {
        this.sampleCondition = sampleCondition;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(String sampleSize) {
        this.sampleSize = sampleSize;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }
}
