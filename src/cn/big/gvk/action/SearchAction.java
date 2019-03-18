package cn.big.gvk.action;

import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

public class SearchAction extends ActionSupport {

    private IBaseService baseService;

    //input parameters
    private String searchParam;
    private String searchSpecies;


    //output parameters
    private List<GwasAssociationBean> gwasAssociationList;
    private List<TermInformationBean> termInfoList;
    private List<GwasAssociationView> gwasAssociationViewList;
    private List<GenotypeAnnotateGeneView> genotypeAnnotateGeneViewList;
    private List<MapGeneBean> mapGeneBeanList;

    private List<GenotypeBean> genotypeBeanList;


    private int mtraitCount;  //trait count
    private int mgeneCount; // gene count
    private int mgenotypeCount; // genotype count
    private int mmapGeneCount; // position range has map gene count


    private Page page;
    private String myUrl;
    private Integer pageSize;
    private Integer pageNo;
    private String totalCount;
    private int isFirstSearchFlag;


    /****************************************************
     * identify search words
     * @return
     */
    public String execFuzzySearchFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }

        map.put("count","count");

        // this used to search trait by key words
        GwasAssociationBean gwasbean = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);
        if(gwasbean!=null ){
            mtraitCount = gwasbean.getTraitCount();
        }

        //search gene
        MapGeneBean genebean = (MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCount",map);
        if(genebean!=null ){
            mgeneCount = genebean.getGeneCount();
        }

        //search genotype chr2:1-2345
        if(this.searchParam != null && this.searchParam.length()> 0){
            if(this.searchParam.contains(":")==true || this.searchParam.startsWith("chr") == true){
                String chrom = "";
                int pstart = -1;
                int pend = -1;
                if(this.searchParam.indexOf(":") > -1 ){
                        int idex = this.searchParam.indexOf(":");
                        chrom = this.searchParam.substring(0,idex -1 );
                        System.out.println("chrom="+chrom);
                        map.put("chrom",chrom);
                        if(this.searchParam.indexOf("-") > -1 ){
                            int idex1 = this.searchParam.indexOf("-");
                            pstart = Integer.parseInt(this.searchParam.substring(idex+1, idex1-1));
                            pend  = Integer.parseInt(this.searchParam.substring(idex1+1, this.searchParam.length()));
                            map.put("startpos",pstart);
                            map.put("endpos",pend);
                        }else {
                            pstart = Integer.parseInt(this.searchParam.substring(idex+1, this.searchParam.length()));
                            map.put("startpos",pstart);
                        }

                }else if(this.searchParam.startsWith("chr") == true){
                    map.put("chrom",this.searchParam);
                }


                /////// search genotype
                GenotypeBean genotype = (GenotypeBean) baseService.findObjectByObject("cn.big.gvk.dm.Genotype.selectGenotypeByPos",map);
                if( genotype != null ){
                    mgenotypeCount = genotype.getGenotypeCount();
                }

                /////////search genecount
                MapGeneBean mapgene = (MapGeneBean)   baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCountByPosition",map);
                if(mapgene != null ){
                    mmapGeneCount = mapgene.getGeneCount();
                }

            }
        }


        if(this.mtraitCount > 0 ){
            return "trait";
        }else if(this.mgeneCount>0){
            return "gene";
        }else if(this.mgenotypeCount > 0 ){

            return "variation" ;
        }


        return SUCCESS;
    }

    /*****************************************************
     * this is used to execute search function from home (fuzzy match)
     * @return
     */
    public String execFuzzySearchTraitFunc(){

        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println("=============search param"+this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
            System.out.println("species="+this.searchSpecies);
        }



        int offset = -1;
        int limit = -1;

        int pageno = 0;
        int pagesize = 0;
        int totalcount = -1;

        if( (this.totalCount!=null && this.totalCount.equals("-1")==false)){
            totalcount = Integer.parseInt(totalCount);
        }

        if(this.isFirstSearchFlag == 0){
            if(totalCount != null && totalCount.length()>0){
                totalcount = Integer.parseInt(totalCount);
            }
        }

        if(this.pageNo != null ){
            pageno = (int)this.pageNo;
        }

        if(this.pageSize != null ){
            pagesize = (int) this.pageSize;
        }


        if(totalcount != -1){
            page = new Page(totalcount, pageno, pagesize, 0);

            if (this.page.getPageSize() > totalcount){
                offset = this.page.getRowFrom() -1 ;
                limit = totalcount ;
            }else {
                offset = this.page.getRowFrom() -1;
                limit = this.page.getPageSize() ;
            }
        }else{
            //first compute count
            offset = 0 ;
            limit =10;

            map.put("count","count");
            GwasAssociationBean gwasbean = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);
            if(gwasbean!=null ){
                System.out.println("count="+gwasbean.getTraitCount());
                totalcount = gwasbean.getTraitCount();
            }
            map.remove("count");
        }

        map.put("p1",offset);
        map.put("p2",limit);



        gwasAssociationList =(List<GwasAssociationBean>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);

        if(gwasAssociationList != null && gwasAssociationList.size()>0){

            for(GwasAssociationBean gwas: gwasAssociationList){

                System.out.println("=====================gwas trait name="+gwas.getTraitName());

                //trait count
                Map cmap = new HashMap();
                cmap.put("traitId",gwas.getTraitId()) ;
                if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
                    cmap.put("species",this.searchSpecies);

                }
                GwasAssociationBean tg_bean = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByTraitId",cmap);
                if(tg_bean != null ){
                   gwas.setTraitCount(tg_bean.getGwasCount());
                }

                List<TermInformationBean> tilist = (List<TermInformationBean>)baseService.findResultList("cn.big.gvk.dm.Term.selectTermDefinition",gwas.getTraitId());
                String s="";
                if(tilist != null && tilist.size()>0 ){
                    for(TermInformationBean tb:tilist ){
                        s+= tb.getTermDefinition() +";";
                    }
                }
                if(s.length()>0){
                    s= s.substring(0, s.length()-1) ;
                }
                gwas.setTermDefinition(s);

                //study count
                GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByTraitid",cmap);
                if(tg_bean1 != null ){
                    gwas.setGwasCount(tg_bean1.getGwasCount());
                }

            }


        }




        return SUCCESS;
    }


    /*********************************************************
     * this is uesd to search gwas association information
     * @return
     */
    public String execFuzzySearchGWASFunc(){

        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }



        int offset = -1;
        int limit = -1;

        int pageno = 0;
        int pagesize = 0;
        int totalcount = -1;

        if( (this.totalCount!=null && this.totalCount.equals("-1")==false)){
            totalcount = Integer.parseInt(totalCount);
        }

        if(this.isFirstSearchFlag == 0){
            if(totalCount != null && totalCount.length()>0){
                totalcount = Integer.parseInt(totalCount);
            }
        }

        if(this.pageNo != null ){
            pageno = (int)this.pageNo;
        }

        if(this.pageSize != null ){
            pagesize = (int) this.pageSize;
        }


        if(totalcount != -1){
            page = new Page(totalcount, pageno, pagesize, 0);

            if (this.page.getPageSize() > totalcount){
                offset = this.page.getRowFrom() -1 ;
                limit = totalcount ;
            }else {
                offset = this.page.getRowFrom() -1;
                limit = this.page.getPageSize() ;
            }
        }else{
            //first compute count
            offset = 0 ;
            limit =10;

            map.put("count","count");
            GwasAssociationBean gwasbean = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);
            if(gwasbean!=null ){
                System.out.println("count="+gwasbean.getTraitCount());
                totalcount = gwasbean.getTraitCount();
            }
            map.remove("count");
        }

        map.put("p1",offset);
        map.put("p2",limit);

        List<GwasAssociationBean>  t_gwasAssociationList =(List<GwasAssociationBean>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);

        if(t_gwasAssociationList != null && t_gwasAssociationList.size()>0) {

            List traitlist = new ArrayList();
            for (GwasAssociationBean gwas : t_gwasAssociationList) {
                traitlist.add(gwas.getTraitId());
            }
            Map t = new HashMap();
            t.put("traitlist",traitlist);
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
        }

        return SUCCESS;
    }


    /****************************************
     * execute search map gene
     * @return
     */
    public String execFunzzySearchMapGeneFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }



        int offset = -1;
        int limit = -1;

        int pageno = 0;
        int pagesize = 0;
        int totalcount = -1;

        if( (this.totalCount!=null && this.totalCount.equals("-1")==false)){
            totalcount = Integer.parseInt(totalCount);
        }

        if(this.isFirstSearchFlag == 0){
            if(totalCount != null && totalCount.length()>0){
                totalcount = Integer.parseInt(totalCount);
            }
        }

        if(this.pageNo != null ){
            pageno = (int)this.pageNo;
        }

        if(this.pageSize != null ){
            pagesize = (int) this.pageSize;
        }


        if(totalcount != -1){
            page = new Page(totalcount, pageno, pagesize, 0);

            if (this.page.getPageSize() > totalcount){
                offset = this.page.getRowFrom() -1 ;
                limit = totalcount ;
            }else {
                offset = this.page.getRowFrom() -1;
                limit = this.page.getPageSize() ;
            }
        }else{
            //first compute count
            offset = 0 ;
            limit =10;

            map.put("count","count");
            MapGeneBean genebean = (MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCount",map);
            if(genebean!=null ){
                totalcount = genebean.getGeneCount();
            }
            map.remove("count");
        }

        mapGeneBeanList = (List<MapGeneBean>) baseService.findResultList("cn.big.gvk.dm.MapGene.selectMapGeneCount",map);
        if(mapGeneBeanList != null ){
            for(MapGeneBean mgb: mapGeneBeanList){
               //get count

                Map t = new HashMap();
                t.put("gId",mgb.getGid()) ;
                t.put("count","count");

                //trait count
                GwasAssociationView gwas = (GwasAssociationView) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectGwasViewByGeneInfo",t);
                if(gwas != null){
                    mgb.setTraitCount(gwas.getTraitCount());
                }

                //study count
                StudyBean study = (StudyBean) baseService.findObjectByObject("cn.big.gvk.dm.study.selectStudyByMapGeneId",t);
                if(study != null ){
                    mgb.setStudyCount(study.getStudyCount());
                }

                //publication count
                PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubByGeneId",t);
                if (publication != null ){
                    mgb.setPublicationCount(publication.getPublicationCount());
                }
            }
        }
        return SUCCESS;
    }

    /****************************************
     * execute search map gene
     * @return
     */
    public String execGetMapGeneFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }



        int offset = -1;
        int limit = -1;

        int pageno = 0;
        int pagesize = 0;
        int totalcount = -1;

        if( (this.totalCount!=null && this.totalCount.equals("-1")==false)){
            totalcount = Integer.parseInt(totalCount);
        }

        if(this.isFirstSearchFlag == 0){
            if(totalCount != null && totalCount.length()>0){
                totalcount = Integer.parseInt(totalCount);
            }
        }

        if(this.pageNo != null ){
            pageno = (int)this.pageNo;
        }

        if(this.pageSize != null ){
            pagesize = (int) this.pageSize;
        }


        if(totalcount != -1){
            page = new Page(totalcount, pageno, pagesize, 0);

            if (this.page.getPageSize() > totalcount){
                offset = this.page.getRowFrom() -1 ;
                limit = totalcount ;
            }else {
                offset = this.page.getRowFrom() -1;
                limit = this.page.getPageSize() ;
            }
        }else{
            //first compute count
            offset = 0 ;
            limit =10;

            map.put("count","count");
            GwasAssociationBean gwasbean = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);
            if(gwasbean!=null ){
                System.out.println("count="+gwasbean.getTraitCount());
                totalcount = gwasbean.getTraitCount();
            }
            map.remove("count");
        }


        List<GwasAssociationBean>  t_gwasAssociationList =(List<GwasAssociationBean>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);

        if(t_gwasAssociationList != null && t_gwasAssociationList.size()>0) {

            List traitlist = new ArrayList();
            for (GwasAssociationBean gwas : t_gwasAssociationList) {
                System.out.println("gwas.getGenotypeId()"+gwas.getGenotypeId());
                traitlist.add(gwas.getGenotypeId());
            }
            Map t = new HashMap();
            t.put("genotypelist",traitlist);

            this.genotypeAnnotateGeneViewList = (List<GenotypeAnnotateGeneView>) baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeByList",t);

        }

        return SUCCESS;

    }


    /********************************************************
     * this is used to search variation
     * query param: chromosome: startpos endpos
     * @return
     */
    public String execFuzzySearchVariationFunc(){

        System.out.println("=========exec execFuzzySearchVariationFunc==========");
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            if(this.searchParam.contains(":")==true || this.searchParam.startsWith("chr") == true) {
                String chrom = "";
                int pstart = -1;
                int pend = -1;
                if (this.searchParam.indexOf(":") > -1) {
                    int idex = this.searchParam.indexOf(":");
                    chrom = this.searchParam.substring(0, idex - 1);
                    System.out.println("chrom=" + chrom);
                    map.put("chrom", chrom);
                    if (this.searchParam.indexOf("-") > -1) {
                        int idex1 = this.searchParam.indexOf("-");
                        pstart = Integer.parseInt(this.searchParam.substring(idex + 1, idex1 - 1));
                        pend = Integer.parseInt(this.searchParam.substring(idex1 + 1, this.searchParam.length()));
                        map.put("startpos", pstart);
                        map.put("endpos", pend);
                    } else {
                        pstart = Integer.parseInt(this.searchParam.substring(idex + 1, this.searchParam.length()));
                        map.put("startpos", pstart);
                    }

                } else if (this.searchParam.startsWith("chr") == true) {
                    map.put("chrom", this.searchParam);
                }
            }

            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }

        int offset = -1;
        int limit = -1;

        int pageno = 0;
        int pagesize = 0;
        int totalcount = -1;

        if( (this.totalCount!=null && this.totalCount.equals("-1")==false)){
            totalcount = Integer.parseInt(totalCount);
        }

        if(this.isFirstSearchFlag == 0){
            if(totalCount != null && totalCount.length()>0){
                totalcount = Integer.parseInt(totalCount);
            }
        }

        if(this.pageNo != null ){
            pageno = (int)this.pageNo;
        }

        if(this.pageSize != null ){
            pagesize = (int) this.pageSize;
        }




        if(totalcount != -1){
            page = new Page(totalcount, pageno, pagesize, 0);

            if (this.page.getPageSize() > totalcount){
                offset = this.page.getRowFrom() -1 ;
                limit = totalcount ;
            }else {
                offset = this.page.getRowFrom() -1;
                limit = this.page.getPageSize() ;
            }
        }else{
            //first compute count
            offset = 0 ;
            limit =10;

            map.put("count","count");

            //search genotype chr2:1-2345
            /////// search genotype
            GenotypeBean genotype = (GenotypeBean) baseService.findObjectByObject("cn.big.gvk.dm.Genotype.selectGenotypeByPos",map);
            if( genotype != null ){
                totalcount = genotype.getGenotypeCount();
            }


            map.remove("count");
        }

        System.out.println("=====total count="+totalcount);

        //here, parse search param  and get genotype information
        genotypeBeanList = (List<GenotypeBean>) baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeByPos",map);
        if(genotypeBeanList != null && genotypeBeanList.size() > 0 ){
            for(GenotypeBean tbean :genotypeBeanList ){
                List genotypelist = new ArrayList();
                genotypelist.add(tbean.getGenotypeId()) ;

                Map t = new HashMap();
                t.put("genotypelist",genotypelist);
                List<GenotypeAnnotateGeneView> annotateview = baseService.findResultList("cn.big.gvk.dm.Genotype.selectGenotypeByList",t);
                if(annotateview != null && annotateview.size()>0 ){
                    //here we need to filter the result
                    Map filtermap = new HashMap();
                    for(GenotypeAnnotateGeneView tview : annotateview){
                        String fkey = tview.getMapGeneId()+"_"+tview.getConseqtype();
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

                        tbean.setGenotypeAnnotateGeneView(alist);
                    }


                }

                //find association count
                GwasAssociationBean gwas = (GwasAssociationBean) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByGenotypeid",tbean.getGenotypeId());
                if(gwas != null){
                  tbean.setTraitCount(gwas.getGwasCount());
                }

                //find studycount
                Map cmap = new HashMap();
                cmap.put("genotypeId",tbean.getGenotypeId());
                if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
                    cmap.put("species",this.searchSpecies);
                }


                GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByGenotypeid",cmap);
                if(tg_bean1 != null ){
                    tbean.setStudyCount(tg_bean1.getGwasCount());
                }

            }



        }

        return SUCCESS;
    }



    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getSearchSpecies() {
        return searchSpecies;
    }

    public void setSearchSpecies(String searchSpecies) {
        this.searchSpecies = searchSpecies;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getMyUrl() {
        return myUrl;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public int getIsFirstSearchFlag() {
        return isFirstSearchFlag;
    }

    public void setIsFirstSearchFlag(int isFirstSearchFlag) {
        this.isFirstSearchFlag = isFirstSearchFlag;
    }

    public List<TermInformationBean> getTermInfoList() {
        return termInfoList;
    }

    public void setTermInfoList(List<TermInformationBean> termInfoList) {
        this.termInfoList = termInfoList;
    }

    public List<GwasAssociationView> getGwasAssociationViewList() {
        return gwasAssociationViewList;
    }

    public void setGwasAssociationViewList(List<GwasAssociationView> gwasAssociationViewList) {
        this.gwasAssociationViewList = gwasAssociationViewList;
    }

    public List<GenotypeAnnotateGeneView> getGenotypeAnnotateGeneViewList() {
        return genotypeAnnotateGeneViewList;
    }

    public void setGenotypeAnnotateGeneViewList(List<GenotypeAnnotateGeneView> genotypeAnnotateGeneViewList) {
        this.genotypeAnnotateGeneViewList = genotypeAnnotateGeneViewList;
    }

    public int getMtraitCount() {
        return mtraitCount;
    }

    public void setMtraitCount(int mtraitCount) {
        this.mtraitCount = mtraitCount;
    }

    public int getMgeneCount() {
        return mgeneCount;
    }

    public void setMgeneCount(int mgeneCount) {
        this.mgeneCount = mgeneCount;
    }

    public int getMgenotypeCount() {
        return mgenotypeCount;
    }

    public void setMgenotypeCount(int mgenotypeCount) {
        this.mgenotypeCount = mgenotypeCount;
    }

    public List<GwasAssociationBean> getGwasAssociationList() {
        return gwasAssociationList;
    }

    public void setGwasAssociationList(List<GwasAssociationBean> gwasAssociationList) {
        this.gwasAssociationList = gwasAssociationList;
    }

    public List<MapGeneBean> getMapGeneBeanList() {
        return mapGeneBeanList;
    }

    public void setMapGeneBeanList(List<MapGeneBean> mapGeneBeanList) {
        this.mapGeneBeanList = mapGeneBeanList;
    }


    public List<GenotypeBean> getGenotypeBeanList() {
        return genotypeBeanList;
    }

    public void setGenotypeBeanList(List<GenotypeBean> genotypeBeanList) {
        this.genotypeBeanList = genotypeBeanList;
    }

    public int getMmapGeneCount() {
        return mmapGeneCount;
    }

    public void setMmapGeneCount(int mmapGeneCount) {
        this.mmapGeneCount = mmapGeneCount;
    }
}
