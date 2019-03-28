package cn.big.gvk.action;

import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

import java.awt.*;
import java.util.List;

/*********************************************
 * this is used to browse data detail information
 *
 *hello, this is a test
 */
public class BrowseAction extends ActionSupport {

    private IBaseService baseService;

    private int param;
    private String param1;
    private String nameStartsWith;


    private List<PublicationBean> publicationList;
    private List<StudyBean> studyList;
    private TermInformationBean termInformationBean;

    private List<GwasAssociationView> gwasAssociationViewList;
    private MapGeneBean mapGeneBean;
    private GenotypeBean genotypeBean;

    private List<ItemCount> itemCountList;

    private List<TraitBean> traitBeanList;

    private List<SearchWordBean> wordList;


    /***********************************
     * get publication information by traitid
     * @return
     */
    public String execBrowsePublicationByTraitFunc(){

        Map cmap = new HashMap();
        cmap.put("traitId", param) ;
        if(this.param1 != null && this.param1.equals("all") == false){
            cmap.put("species", param1) ;
        }

        this.publicationList = (List<PublicationBean>)baseService.findResultList("cn.big.gvk.dm.publication.selectPubByTraitId",cmap);
        if(this.publicationList  != null){
            for(PublicationBean pubbean: publicationList){
                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByPubid",pubbean.getPaperId()) ;
                if(gbean != null ){
                    pubbean.setGwasCount(gbean.getGwasCount());
                }
            }
        }

        //get the publication for each species
        List<OrganismBean> orglist =(List<OrganismBean> ) baseService.findResultList("cn.big.gvk.dm.organism.selectAllOrganism",null);
        if(orglist != null && orglist.size()>0){
            itemCountList = new ArrayList<ItemCount>();

            for(OrganismBean org : orglist){

                Map countmap = new HashMap();
                countmap.put("species",org.getOrgId());
                countmap.put("traitId",this.param);

                //publication count
                PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubCountByTraitId",countmap);
                if (publication != null && publication.getPublicationCount()>0){
                    ItemCount tb = new ItemCount();
                    tb.setId(org.getOrgId());
                    tb.setName(org.getCommonName());
                    tb.setCount(publication.getPublicationCount());
                    itemCountList.add(tb);

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


    /****************************************
     * this is used to get publication by genotype id
     * @return
     */
    public String execBrowsePublicationByGenotypeFunc(){
        Map cmap = new HashMap();
        cmap.put("genotypeId", param) ;
        if(this.param1 != null && this.param1.equals("all") == false){
            cmap.put("species", param1) ;
        }

        this.publicationList = (List<PublicationBean>)baseService.findResultList("cn.big.gvk.dm.publication.selectPubByGenotypeId",cmap);
        if(this.publicationList  != null){
            for(PublicationBean pubbean: publicationList){
                GwasAssociationBean gbean = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByPubid",pubbean.getPaperId()) ;
                if(gbean != null ){
                    pubbean.setGwasCount(gbean.getGwasCount());
                }
            }
        }

        //get the publication for each species
        List<OrganismBean> orglist =(List<OrganismBean> ) baseService.findResultList("cn.big.gvk.dm.organism.selectAllOrganism",null);
        if(orglist != null && orglist.size()>0){
            itemCountList = new ArrayList<ItemCount>();

            for(OrganismBean org : orglist){

                Map countmap = new HashMap();
                countmap.put("species",org.getOrgId());
                countmap.put("genotypeId",this.param);

                //publication count
                PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubCountByAnGenotypeId",countmap);
                if (publication != null && publication.getPublicationCount()>0){
                    ItemCount tb = new ItemCount();
                    tb.setId(org.getOrgId());
                    tb.setName(org.getCommonName());
                    tb.setCount(publication.getPublicationCount());
                    itemCountList.add(tb);

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

       Map cmap = new HashMap();
       cmap.put("traitId",param);
       if(this.param1 != null && this.param1.equals("all") == false){
           cmap.put("species",param1);
       }

       this.studyList = (List<StudyBean>) baseService.findResultList("cn.big.gvk.dm.study.selectStudyByTraitId",cmap);
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

       //here,need to return the study count for each species
        List<OrganismBean> orglist =(List<OrganismBean> ) baseService.findResultList("cn.big.gvk.dm.organism.selectAllOrganism",null);
        if(orglist != null && orglist.size()>0){
            itemCountList = new ArrayList<ItemCount>();

            for(OrganismBean org : orglist){

                Map countmap = new HashMap();
                countmap.put("species",org.getOrgId());
                countmap.put("traitId",this.param);

                GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByTraitid",countmap);
                if(tg_bean1 != null && tg_bean1.getGwasCount() > 0 ){
                    ItemCount tb = new ItemCount();
                    tb.setId(org.getOrgId());
                    tb.setName(org.getCommonName());
                    tb.setCount(tg_bean1.getGwasCount());
                    itemCountList.add(tb);
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

    /*****************************************
     * this is used to get study informaiton by genotype id
     * @return
     */
    public String execBrowseStudyByGenotypeFunc(){
        Map cmap = new HashMap();
        cmap.put("genotypeId",param);
        if(this.param1 != null && this.param1.equals("all") == false){
            cmap.put("species",param1);
        }

        this.studyList = (List<StudyBean>) baseService.findResultList("cn.big.gvk.dm.study.selectStudyByGenotypeId",cmap);


        if(this.studyList != null && this.studyList.size() >0 ){
            System.out.println("=============studycount=" +this.studyList.size() );


            for(StudyBean study: studyList){
                /////////////need to modify table gwas_genotype_tech
                List<GenotypeTechBean> glist = baseService.findResultList("cn.big.gvk.dm.study.selectGenotypeTchByGenotypeid",param);
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

        //here,need to return the study count for each species
        List<OrganismBean> orglist =(List<OrganismBean> ) baseService.findResultList("cn.big.gvk.dm.organism.selectAllOrganism",null);
        if(orglist != null && orglist.size()>0){
            itemCountList = new ArrayList<ItemCount>();

            for(OrganismBean org : orglist){

                Map countmap = new HashMap();
                countmap.put("species",org.getOrgId());
                countmap.put("genotypeId",this.param);

                GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByGenotypeid",countmap);
                if(tg_bean1 != null && tg_bean1.getGwasCount() > 0 ){
                    ItemCount tb = new ItemCount();
                    tb.setId(org.getOrgId());
                    tb.setName(org.getCommonName());
                    tb.setCount(tg_bean1.getGwasCount());
                    itemCountList.add(tb);
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

    /********************************************************************
     * this is used to get variant detail information
     * @return
     */
    public String execGetVariantDetailFunc(){
        Map cmap = new HashMap();
        cmap.put("genotypeid",param);
        if(this.param1 != null && this.param1.equals("all") == false){
            cmap.put("gwas","gwas") ;
            cmap.put("gwasend","gwasend") ;
            cmap.put("species",param1);
        }

        //get genotype detail information
        genotypeBean= (GenotypeBean)baseService.findObjectByObject("cn.big.gvk.dm.Genotype.selectGenotypeByPos",cmap);

        if(genotypeBean != null ){

            //process map gene
            List genotypelist = new ArrayList();
            genotypelist.add(genotypeBean.getGenotypeId()) ;


            List<GenotypeAnnotateGeneView> annotateview = baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeById",genotypeBean.getGenotypeId());
            if(annotateview != null && annotateview.size()>0 ){
                //here we need to filter the result
                Map filtermap = new HashMap();
                for(GenotypeAnnotateGeneView tview : annotateview){
                    String fkey = tview.getMapGeneId()+"_"+tview.getConseqtype();
                    System.out.println("============"+fkey);
                    if(filtermap.containsKey(fkey) == false){
                        filtermap.put(fkey,tview);
                    }
                }

                if(filtermap.size()>0){

                    List<GenotypeAnnotateGeneView>  alist = new ArrayList<GenotypeAnnotateGeneView>();
                    Iterator it = filtermap.entrySet().iterator();
                    while(it.hasNext()){
                        Map.Entry entry = (Map.Entry) it.next();
                        GenotypeAnnotateGeneView val = (GenotypeAnnotateGeneView) entry.getValue();
                        alist.add(val);
                    }

                    genotypeBean.setGenotypeAnnotateGeneView(alist);
                }





            }

            System.out.println("============genotypeBean="+genotypeBean.getGenotypeId());

            //get report trait
            List<GenotypeBean>  tmplst = (List <GenotypeBean> )  baseService.findResultList("cn.big.gvk.dm.Genotype.selectTraitNameByGenotype",genotypeBean.getGenotypeId());
            if(tmplst != null ){
                String tmps="";
                for(GenotypeBean tmp: tmplst){
                    tmps += tmp.getTrait()+"|";
                }
                if(tmps.length()>0){
                    tmps = tmps.substring(0,tmps.length()-1) ;
                }

                System.out.println("report reports===================="+tmps);
                genotypeBean.setTrait(tmps);
            }

            //get  trait (term)
              /*  List<TermInformationBean>  tmplst1 = (List <TermInformationBean> )  baseService.findResultList("cn.big.gvk.dm.Term.selectReportTraitByGenotype",genotypeBean.getGenotypeId());
                if(tmplst1 != null ){
                    String tmps="";
                    for(TermInformationBean tmp: tmplst1){
                        tmps += tmp.getTermName()+"|";
                    }
                    if(tmps.length()>0){
                        tmps = tmps.substring(0,tmps.length()-1) ;
                    }
                    genotypeBean.setReportTrait(tmps);
                }*/



            //find association count
            GwasAssociationBean gwas = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByGenotypeid",genotypeBean.getGenotypeId());
            if(gwas != null){
                genotypeBean.setTraitCount(gwas.getGwasCount());
            }

            //find studycount
            cmap.clear();
            cmap.put("genotypeId", genotypeBean.getGenotypeId());
            if(this.param1 != null && this.param1.equals("all") == false){
                cmap.put("species",param1);
            }

            GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByGenotypeid",cmap);
            if(tg_bean1 != null ){
                genotypeBean.setStudyCount(tg_bean1.getGwasCount());
            }

            //find publication count
            PublicationBean pubbean = (PublicationBean) baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubCountByGenotypeId",cmap);
            if(pubbean != null ){
                genotypeBean.setPublicationCount(pubbean.getPublicationCount());
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
            if(this.param1 != null && this.param1.equals("all") == false){ // orgid
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


    /***************************************
     *this is used to get association data by genotype id
     * @return
     */
    public String execGetGWASByGenotypeFunc(){
        Map t = new HashMap();
        t.put("genotypeId",this.param) ;

        this.gwasAssociationViewList = (List<GwasAssociationView>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selectGwasViewByVariant",t);
        if(this.gwasAssociationViewList != null && this.gwasAssociationViewList.size() > 0 ){
            for(GwasAssociationView gview : gwasAssociationViewList){

                //map gene
                List<GenotypeAnnotateGeneView>  gagvlist = (List<GenotypeAnnotateGeneView>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeById",this.param);

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
                    gview.setGenotypeAnnotateViewList(ttlist);

                }



                //report gene

                List<ReportGeneBean>  grglist = (List<ReportGeneBean>)baseService.findResultList("cn.big.gvk.dm.Genotype.selectReportGeneByGenotypeId",this.param);
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

    /***********************************
     * this is used to filter trait name
     * @return
     */
    public String execBrowseTraitNameByOrganism(){
        Map cmap = new HashMap();
        if(this.param1 != null && this.param1.equals("all") == false){
            cmap.put("species", param1) ;
        }

        this.traitBeanList = (List<TraitBean>)baseService.findResultList("cn.big.gvk.dm.ReportTrait.selectReportTraitByOrganism",cmap);
        return SUCCESS;
    }


    /*********************************************
     * this is used to get search word
     * @return
     */

    public String execGetSearchWordFunc(){

        Map map = new HashMap();
        map.put("term", this.nameStartsWith) ;
        this.wordList = (List<SearchWordBean>)baseService.findResultList("cn.big.gvk.dm.searchWord.selectSearchWordByOrganism",map);
        return SUCCESS;
    }

    public List<SearchWordBean> getWordList() {
        return wordList;
    }

    public void setWordList(List<SearchWordBean> wordList) {
        this.wordList = wordList;
    }

    public List<TraitBean> getTraitBeanList() {
        return traitBeanList;
    }

    public void setTraitBeanList(List<TraitBean> traitBeanList) {
        this.traitBeanList = traitBeanList;
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

    public GenotypeBean getGenotypeBean() {
        return genotypeBean;
    }

    public void setGenotypeBean(GenotypeBean genotypeBean) {
        this.genotypeBean = genotypeBean;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public void setNameStartsWith(String nameStartsWith) {
        this.nameStartsWith = nameStartsWith;
    }
}
