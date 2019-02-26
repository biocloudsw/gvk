package cn.big.gvk.action;

import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.awt.*;
import java.util.Map;

/*********************************************
 * this is used to browse data detail information
 */
public class BrowseAction extends ActionSupport {

    private IBaseService baseService;

    private int param;
    private String param1;


    private List<PublicationBean> publicationList;
    private List<StudyBean> studyList;
    private TermInformationBean termInformationBean;

    private List<GwasAssociationView> gwasAssociationViewList;
    private MapGeneBean mapGeneBean;

    private List<ItemCount> itemCountList;

    /***********************************
     * get publication information by traitid
     * @return
     */
    public String execBrowsePublicationByTraitFunc(){
        this.publicationList = (List<PublicationBean>)baseService.findResultList("cn.big.gvk.dm.publication.selectPubByTraitId",param);
        if(this.publicationList  != null){
            for(PublicationBean pubbean: publicationList){
                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByPubid",pubbean.getPaperId()) ;
                if(gbean != null ){
                    pubbean.setGwasCount(gbean.getGwasCount());
                }
            }
        }
        return SUCCESS;
    }

    /***********************************
     * get publication information by map gene id
     * @return
     */
    public String execBrowsePublicationByMapGeneFunc(){

        Map t = new HashMap();
        t.put("gId", this.param) ;

        this.publicationList = (List<PublicationBean>)baseService.findResultList("cn.big.gvk.dm.publication.selectPubByGeneId",t);
        if(this.publicationList  != null){
            for(PublicationBean pubbean: publicationList){
                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByPubid",pubbean.getPaperId()) ;
                if(gbean != null ){
                    pubbean.setGwasCount(gbean.getGwasCount());
                }
            }
        }
        return SUCCESS;
    }

    /******************************************
     * execute study information by trait id
     * @return
     */
    public String execBrowseStudyByTraitFunc(){

       this.studyList = (List<StudyBean>) baseService.findResultList("cn.big.gvk.dm.study.selectStudyByTraitId",param);
       if(this.studyList != null && this.studyList.size() >0 ){
            for(StudyBean study: studyList){
                /////////////need to modify table gwas_genotype_tech
                List<GenotypeTechBean> glist = baseService.findResultList("cn.big.gvk.dm.study.selectGenotypeTchByGwasid",param);
                String gtype = "";
                if(glist != null && glist.size() >0 ){
                    for(GenotypeTechBean gbean : glist){
                        gtype += gbean.getTechName()+"," ;
                    }
                    gtype = gtype.substring(0, gtype.length() -1 );
                }
                study.setTechName(gtype);

                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByStudyid",study.getStudyId()) ;
                if(gbean != null ){
                    study.setGwasCount(gbean.getGwasCount());
                }
            }
       }
        return SUCCESS;
    }


    /******************************************
     * execute study information by trait id
     * @return
     */
    public String execBrowseStudyByMapGeneFunc(){

        Map t = new HashMap();
        t.put("gId",this.param) ;

        this.studyList = (List<StudyBean>) baseService.findResultList("cn.big.gvk.dm.study.selectStudyByMapGeneId",t);
        if(this.studyList != null && this.studyList.size() >0 ){
            for(StudyBean study: studyList){
                /////////////need to modify table gwas_genotype_tech
                List<GenotypeTechBean> glist = baseService.findResultList("cn.big.gvk.dm.study.selectGenotypeTchByGwasid",param);
                String gtype = "";
                if(glist != null && glist.size() >0 ){
                    for(GenotypeTechBean gbean : glist){
                        gtype += gbean.getTechName()+"," ;
                    }
                    gtype = gtype.substring(0, gtype.length() -1 );
                }
                study.setTechName(gtype);

                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByStudyid",study.getStudyId()) ;
                if(gbean != null ){
                    study.setGwasCount(gbean.getGwasCount());
                }
            }
        }
        return SUCCESS;
    }

    //get trait detail information
    /*****************************************************
     * this is used to execute search function from home (fuzzy match)
     * @return
     */
    public String execGetTraitFunc(){
            Map map = new HashMap();
            List traitlist = new ArrayList();
            traitlist.add(param);
            map.put("termlist",traitlist);

            int iflag = 0 ;
            String mapterms = "";

            TermInformationBean anobean = null;

             List<TermInformationBean>  termInfoList = (List<TermInformationBean>)baseService.findResultList("cn.big.gvk.dm.Term.selectTermInfo",map);
            if( termInfoList != null && termInfoList.size() > 0 ){
                for( TermInformationBean tbean:termInfoList ) {

                    if (tbean.getAcc().startsWith("BTO") == true) {
                        termInformationBean = tbean;
                        iflag = 1;

                    } else {
                        anobean = tbean;
                        mapterms += tbean.getAcc()+";" ;
                    }

                }

                if(anobean != null  ){
                    termInformationBean = anobean;
                }

                if(termInformationBean != null ){
                    if(mapterms.length() > 0 ){
                        mapterms = mapterms.substring(0, mapterms.length() -1 ) ;
                        termInformationBean.setMapTerms(mapterms);
                    }

                    List<TermSynonymBean> termSynonymList = (List<TermSynonymBean>) baseService.findResultList("cn.big.gvk.dm.Term.selectTermSynonym",termInformationBean.getTermId());
                    String terms = "";
                    if(termSynonymList != null ){
                        for(TermSynonymBean sbean: termSynonymList){
                            terms += sbean.getTermSynonym()+";";
                        }

                        if(terms.length() > 0){
                            terms = terms.substring(0,terms.length() -1 );
                        }
                        termInformationBean.setTermSynonym(terms);
                    }

                    Map pmap = new HashMap();
                    pmap.put( "traitId", this.param ) ;
                    if(this.param1 != null && this.param1.equals("all")==false){
                        pmap.put("species",param1);
                    }


                    //trait count
                    GwasAssociationBean tg_bean = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByTraitId",pmap);
                    if(tg_bean != null ){
                        termInformationBean.setTraitCount(tg_bean.getGwasCount());
                    }

                    //study count
                    GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByTraitid",pmap);
                    if(tg_bean1 != null ){
                        termInformationBean.setStudyCount(tg_bean1.getGwasCount());
                    }

                    //publication count
                    PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubCountByTraitId",pmap);
                    if (publication != null ){
                        termInformationBean.setPublicationCount(publication.getPublicationCount());
                    }

                }

            }
        return SUCCESS;
    }

    /**************************************************************
     * get map gene information
     * @return
     */
    public String execGetMapGeneFunc(){
        mapGeneBean =(MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGenebyGeneid",param) ;

        if(mapGeneBean != null ){
            Map t = new HashMap();
            t.put("gId",this.param) ;
            t.put("count","count");

            //trait count
            GwasAssociationView gwas = (GwasAssociationView) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectGwasViewByGeneInfo",t);
            if(gwas != null){
                mapGeneBean.setTraitCount(gwas.getTraitCount());
            }

            //study count
            StudyBean study = (StudyBean) baseService.findObjectByObject("cn.big.gvk.dm.study.selectStudyByMapGeneId",t);
            if(study != null ){
                mapGeneBean.setStudyCount(study.getStudyCount());
            }

            //publication count
            PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubByGeneId",t);
            if (publication != null ){
                mapGeneBean.setPublicationCount(publication.getPublicationCount());
            }

        }

        return SUCCESS;
    }


    /*********************************************************
     * this is uesd to search gwas association information
     * we need to add organism
     * @return
     */
    public String execGetGWASByTraitIDFunc(){


            List traitlist = new ArrayList();
             traitlist.add(param);
            Map t = new HashMap();
            t.put("traitlist",traitlist);
            if(this.param1 != null ){ // orgid
                t.put("species",this.param1) ;

            }
            this.gwasAssociationViewList = (List<GwasAssociationView>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selectGwasViewInfo",t);

            if(this.gwasAssociationViewList  != null ){
                for(GwasAssociationView gview : gwasAssociationViewList){
                    List<GenotypeAnnotateGeneView>  gagvlist = (List<GenotypeAnnotateGeneView>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeById",gview.getGenotypeId());

                    List<GenotypeAnnotateGeneView> ttlist = new ArrayList<GenotypeAnnotateGeneView>();
                    if(gagvlist != null ){
                        Map gmap = new HashMap();

                        for(GenotypeAnnotateGeneView gagv: gagvlist){
                            String key = gagv.getMapGeneId()+","+gagv.getConseqtype();
                            if(gmap.get(key) == null ){
                                ttlist.add(gagv);
                                gmap.put(key,key);
                            }
                        }


                    }

                    gview.setGenotypeAnnotateViewList(ttlist);


                    List<ReportGeneBean>  grglist = (List<ReportGeneBean>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectReportGeneById",gview.getGaId());
                    if(grglist != null ){
                        String s = "";
                        for(ReportGeneBean grg: grglist){
                            if(grg.getReportGeneId() != null ){
                                s += grg.getReportGeneId();
                            }
                            if(grg.getEntrezGeneSymbol() != null ){
                                s+= "( "+grg.getEntrezGeneSymbol()+" );";
                            }
                        }
                        if(s.length()>0){
                            s= s.substring(0,s.length()-1);
                        }
                        gview.setGwasReportGene(s);
                    }
                }
            }


            //here, get association count in each species, only count>0 shown
            List<OrganismBean> orglist =(List<OrganismBean> ) baseService.findResultList("cn.big.gvk.dm.organism.selectAllOrganism",null);
            if(orglist != null && orglist.size()>0){
                itemCountList = new ArrayList<ItemCount>();

                for(OrganismBean org : orglist){

                    Map countmap = new HashMap();
                    countmap.put("species",org.getOrgId());
                    countmap.put("traitId",this.param);
                    GwasAssociationBean tg_bean = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByTraitId",countmap);
                    if(tg_bean != null && tg_bean.getGwasCount() >0 ){
                        ItemCount tb = new ItemCount();
                        tb.setId(org.getOrgId());
                        tb.setName(org.getCommonName());
                        tb.setCount(tg_bean.getGwasCount());
                        itemCountList.add(tb);
                    }
                }
            }

        return SUCCESS;
    }


    /*********************************************************
     * this is uesd to get  gwas association information by Gene
     * @return
     */
    public String execGetGWASByMapGeneFunc(){

        Map t = new HashMap();
        t.put("gId",this.param) ;

        this.gwasAssociationViewList = (List<GwasAssociationView>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selectGwasViewByGeneInfo",t);

        if(this.gwasAssociationViewList  != null ){
            for(GwasAssociationView gview : gwasAssociationViewList){
                List<GenotypeAnnotateGeneView>  gagvlist = (List<GenotypeAnnotateGeneView>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeById",gview.getGenotypeId());

                List<GenotypeAnnotateGeneView> ttlist = new ArrayList<GenotypeAnnotateGeneView>();
                if(gagvlist != null ){
                    Map gmap = new HashMap();

                    for(GenotypeAnnotateGeneView gagv: gagvlist){
                        String key = gagv.getMapGeneId()+","+gagv.getConseqtype();
                        if(gmap.get(key) == null ){
                            ttlist.add(gagv);
                            gmap.put(key,key);
                        }
                    }


                }

                gview.setGenotypeAnnotateViewList(ttlist);


                List<ReportGeneBean>  grglist = (List<ReportGeneBean>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectReportGeneById",gview.getGaId());
                if(grglist != null ){
                    String s = "";
                    for(ReportGeneBean grg: grglist){
                        if(grg.getReportGeneId() != null ){
                            s += grg.getReportGeneId();
                        }
                        if(grg.getEntrezGeneSymbol() != null ){
                            s+= "( "+grg.getEntrezGeneSymbol()+" );";
                        }
                    }
                    if(s.length()>0){
                        s= s.substring(0,s.length()-1);
                    }
                    gview.setGwasReportGene(s);
                }
            }
        }

        return SUCCESS;
    }



    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public List<PublicationBean> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<PublicationBean> publicationList) {
        this.publicationList = publicationList;
    }

    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public List<StudyBean> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<StudyBean> studyList) {
        this.studyList = studyList;
    }

    public TermInformationBean getTermInformationBean() {
        return termInformationBean;
    }

    public void setTermInformationBean(TermInformationBean termInformationBean) {
        this.termInformationBean = termInformationBean;
    }

    public List<GwasAssociationView> getGwasAssociationViewList() {
        return gwasAssociationViewList;
    }

    public void setGwasAssociationViewList(List<GwasAssociationView> gwasAssociationViewList) {
        this.gwasAssociationViewList = gwasAssociationViewList;
    }

    public MapGeneBean getMapGeneBean() {
        return mapGeneBean;
    }

    public void setMapGeneBean(MapGeneBean mapGeneBean) {
        this.mapGeneBean = mapGeneBean;
    }

    public List<ItemCount> getItemCountList() {
        return itemCountList;
    }

    public void setItemCountList(List<ItemCount> itemCountList) {
        this.itemCountList = itemCountList;
    }
}
