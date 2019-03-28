package cn.big.gvk.action;

import cn.big.gvk.po.*;
import cn.big.gvk.po.search.SearchItemBean;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
/*******************************************************************
 * this is used to export data by different format
 */
public class ExportAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{


    private IBaseService baseService;
    private HttpServletResponse response;
    //input parameters
    private String searchParam;
    private String searchSpecies;

    //Filter parameters
    private String searchTrait;
    private String psitu;
    private String pvalue;

    private int format; //1 txt


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


    /************************************************************
     * this is used to search custom word such as height,loc,b
     * @return
     */
    public void execFuzzySearchCustomWordFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            map.put("searchParam",this.searchParam);
        }

        int idenfilter = 0 ;
        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            idenfilter = 1;
            map.put("species",this.searchSpecies.trim());
        }


        if(this.searchTrait != null && this.searchTrait.equals("null")==false && this.searchTrait.length()>0){
            idenfilter =1;
            List traitlist = new ArrayList();
            if(this.searchTrait.indexOf(",")>-1){
                String [] traits = this.searchTrait.split(",");
                if(traits != null && traits.length>0){
                    for(String tr:traits){
                        traitlist.add(tr);
                    }
                }
            }else{
                traitlist.add(this.searchTrait);
            }
            map.put("traitlist", traitlist) ;
        }

        if(this.pvalue != null && this.pvalue.equals("null")==false && this.pvalue.length()>0 ){
            idenfilter =1;
            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
        }

        //this is search customword
        List<SearchItemBean> searchlist = (List<SearchItemBean>) baseService.findResultList("cn.big.gvk.dm.Search.selectCustomWordBySearch",map);
        if( searchlist != null ){
            gwasAssociationList = new ArrayList<GwasAssociationBean>() ;
            mapGeneBeanList = new  ArrayList<MapGeneBean>();
            for(SearchItemBean tmpbean : searchlist){
                if(tmpbean != null ){
                    if(tmpbean.getItemType() == 1){
                        //selecTraitByFuzzySearch
                        Map cmp = new HashMap();
                        cmp.put("traitId", tmpbean.getItemId());
                        GwasAssociationBean tmpgwas = (GwasAssociationBean)  baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",cmp);
                        if(tmpgwas != null ){
                            //trait count
                            Map cmap = new HashMap();
                            cmap.put("traitId",tmpgwas.getTraitId()) ;
                            if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
                                cmap.put("species",this.searchSpecies);

                            }
                            GwasAssociationBean tg_bean = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByTraitId",cmap);
                            if(tg_bean != null ){
                                tmpgwas.setTraitCount(tg_bean.getGwasCount());
                            }

                            List<TermInformationBean> tilist = (List<TermInformationBean>)baseService.findResultList("cn.big.gvk.dm.Term.selectTermDefinition",tmpgwas.getTraitId());
                            String s="";
                            if(tilist != null && tilist.size()>0 ){
                                for(TermInformationBean tb:tilist ){
                                    if(tb!= null && tb.getTermDefinition() != null ){
                                        s+= tb.getTermDefinition() +";";
                                    }

                                }
                            }
                            if(s.length()>0){
                                s= s.substring(0, s.length()-1) ;
                            }
                            tmpgwas.setTermDefinition(s);

                            //study count
                            GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByTraitid",cmap);
                            if(tg_bean1 != null ){
                                tmpgwas.setGwasCount(tg_bean1.getGwasCount());
                            }
                            gwasAssociationList.add(tmpgwas) ;
                        }
                    }else if(tmpbean.getItemType() == 2){
                        Map cmp = new HashMap();
                        cmp.put("gid",tmpbean.getItemId()) ;
                        MapGeneBean mgb = (MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCount",cmp);
                        if(mgb != null ){
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
                            mapGeneBeanList.add(mgb) ;

                        }

                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        //generate hidden html table and then use tableExport.js export
        if(gwasAssociationList != null && gwasAssociationList.size()>0){
            for(GwasAssociationBean gwas : gwasAssociationList){
                sb.append(gwas.getTraitName()).append("\t").append(gwas.getTermDefinition()).append("\t").append(gwas.getTraitCount()).append("\t").append(gwas.getGwasCount()).append("\n");
            }
        }

        if(mapGeneBeanList != null && mapGeneBeanList.size()>0){
            for(MapGeneBean mapgene: mapGeneBeanList){
                sb.append(mapgene.getMapGeneId()).append("\t").append(mapgene.getMapGeneChrom()).append(":")
                       .append(mapgene.getMapGeneStart()).append("-").append(mapgene.getMapGeneEnd()).append("\t").append(mapgene.getTraitCount()).append("\t")
                        .append(mapgene.getStudyCount()).append("\n");
            }
        }

        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    /*****************************************************
     * SEARCH DATA BY position such as chr1:1-120
     * @return
     */
    public void execFuzzySearchRangeFunc(){

        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            if(this.searchParam.contains(":")==true || this.searchParam.startsWith("chr") == true){
                String chrom = "";
                int pstart = -1;
                int pend = -1;
                if(this.searchParam.indexOf(":") > -1 ){
                    int idex = this.searchParam.indexOf(":");
                    chrom = this.searchParam.substring(0,idex );
                    System.out.println("chrom="+chrom);
                    map.put("chrom",chrom);
                    if(this.searchParam.indexOf("-") > -1 ){
                        int idex1 = this.searchParam.indexOf("-");
                        pstart = Integer.parseInt(this.searchParam.substring(idex+1, idex1));
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
            }
        }

        int idenfilter = 0;
        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            idenfilter =1;
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("species",this.searchSpecies);
        }

        if(this.searchTrait != null&& this.searchTrait.equals("null")==false  && this.searchTrait.length()>0){
            idenfilter =1;
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("searchTrait", this.searchTrait) ;
        }

        if(this.pvalue != null&& this.pvalue.equals("null")==false  && this.pvalue.length()>0){
            idenfilter =1;
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
        }

        List<SearchItemBean> searchlist = (List<SearchItemBean>) baseService.findResultList("cn.big.gvk.dm.Search.selectRangeBySearch",map);
        if( searchlist != null ){
            genotypeBeanList = new ArrayList<GenotypeBean>();
            mapGeneBeanList = new ArrayList<MapGeneBean>();
            for(SearchItemBean tmpbean : searchlist){
                if(tmpbean.getItemType() == 1){ //variant
                    Map cmp = new HashMap();
                    cmp.put("genotypeid",tmpbean.getItemId());
                    GenotypeBean tbean = (GenotypeBean) baseService.findObjectByObject("cn.big.gvk.dm.Genotype.selectGenotypeByPos",cmp);
                    if(tbean != null ){
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

                        genotypeBeanList.add(tbean) ;
                    }




                }else if(tmpbean.getItemType() == 2){//gene

                    Map cmp = new HashMap();
                    cmp.put("gid",tmpbean.getItemId()) ;
                    MapGeneBean mgb = (MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCount",cmp);
                    if(mgb != null ){
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
                        mapGeneBeanList.add(mgb) ;

                    }


                }
            }
        }
        StringBuffer sb = new StringBuffer();
        //generate hidden html table and then use tableExport.js export
        if(genotypeBeanList != null && genotypeBeanList.size()>0){
            for(GenotypeBean genotype: genotypeBeanList){
                sb.append(genotype.getVarId()).append("\t").append(genotype.getChrom()).append(":")
                        .append(genotype.getStartPos()).append("\t").append(genotype.getTraitCount())
                        .append("\t").append(genotype.getStudyCount()).append("\n");
            }
        }

        if(mapGeneBeanList != null && mapGeneBeanList.size()>0){
            for(MapGeneBean mapgene: mapGeneBeanList){
                sb.append(mapgene.getMapGeneId()).append("\t").append(mapgene.getMapGeneChrom()).append(":")
                        .append(mapgene.getMapGeneStart()).append("-").append(mapgene.getMapGeneEnd()).append("\t").append(mapgene.getTraitCount()).append("\t")
                        .append(mapgene.getStudyCount()).append("\n");
            }
        }


        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    /*****************************************************
     * this is used to execute search function from home (fuzzy match)
     * @return
     */
    public void execFuzzySearchTraitFunc(){

        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println("=============search param"+this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
            System.out.println("species="+this.searchSpecies);
        }

        if(this.searchTrait != null&& this.searchTrait.equals("null")==false && this.searchTrait.length()>0){


            map.put("searchTrait", this.searchTrait) ;
        }

        if(this.pvalue != null && this.pvalue.equals("null")==false&& this.pvalue.length()>0){


            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
        }



        gwasAssociationList =(List<GwasAssociationBean>) baseService.findResultList("cn.big.gvk.dm.GwasAssociation.selecTraitByFuzzySearch",map);

        if(gwasAssociationList != null && gwasAssociationList.size()>0){

            for(GwasAssociationBean gwas: gwasAssociationList){

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
                        if(tb!= null && tb.getTermDefinition() != null ){
                            s+= tb.getTermDefinition() +";";
                        }

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

        StringBuffer sb = new StringBuffer();
        if(this.gwasAssociationList != null && this.gwasAssociationList.size()>0){
            for(GwasAssociationBean gwas: gwasAssociationList){
                sb.append(gwas.getTraitName()).append("\t").append(gwas.getTermDefinition()).append("\t")
                        .append(gwas.getTraitCount()).append("\t").append(gwas.getGwasCount()).append("\n");
            }
        }

        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


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
    public void execFunzzySearchMapGeneFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            map.put("searchParam",this.searchParam);
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("all") == false){
            map.put("species",this.searchSpecies);
        }

        if(this.searchTrait != null && this.searchTrait.equals("null")==false && this.searchTrait.length()>0){

            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("searchTrait", this.searchTrait) ;
        }

        if(this.pvalue != null && this.pvalue.equals("null")==false && this.pvalue.length()>0){

            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
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

        StringBuffer sb = new StringBuffer() ;

        if(mapGeneBeanList != null && mapGeneBeanList.size()>0){
            for(MapGeneBean mapgene: mapGeneBeanList){
                sb.append(mapgene.getMapGeneId()).append("\t").append(mapgene.getMapGeneChrom()).append(":")
                        .append(mapgene.getMapGeneStart()).append("-").append(mapgene.getMapGeneEnd()).append("\t").append(mapgene.getTraitCount()).append("\t")
                        .append(mapgene.getStudyCount()).append("\n");
            }
        }

        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /********************************************
     * SEARCH gene by postion such as chr1
     * @return
     */
    public void execFunzzySearchMapGeneByRangeFunc(){
        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            if(this.searchParam.contains(":")==true || this.searchParam.startsWith("chr") == true){
                String chrom = "";
                int pstart = -1;
                int pend = -1;
                if(this.searchParam.indexOf(":") > -1 ){
                    int idex = this.searchParam.indexOf(":");
                    chrom = this.searchParam.substring(0,idex );
                    System.out.println("chrom="+chrom);
                    map.put("chrom",chrom);
                    if(this.searchParam.indexOf("-") > -1 ){
                        int idex1 = this.searchParam.indexOf("-");
                        pstart = Integer.parseInt(this.searchParam.substring(idex+1, idex1));
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
            }
        }

        if(this.searchSpecies!= null && this.searchSpecies.equals("null") ==false && this.searchSpecies.equals("all") == false){
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("species",this.searchSpecies);
        }

        if(this.searchTrait != null && this.searchTrait.equals("null") ==false && this.searchTrait.length()>0){

            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("searchTrait", this.searchTrait) ;
        }

        if(this.pvalue != null && this.pvalue.equals("null") ==false && this.pvalue.length()>0){
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
        }


        List<MapGeneBean> tmplist = (List<MapGeneBean>) baseService.findResultList("cn.big.gvk.dm.MapGene.selectMapGeneCountByPosition",map);
        if(tmplist != null && tmplist.size()>0){
            mapGeneBeanList = new ArrayList<MapGeneBean>();

            for(MapGeneBean tbean: tmplist){
                Map cmp = new HashMap();
                cmp.put("gid",tbean.getGid());
                MapGeneBean genebean = (MapGeneBean) baseService.findObjectByObject("cn.big.gvk.dm.MapGene.selectMapGeneCount",cmp);
                if(genebean != null ){

                    Map t = new HashMap();
                    t.put("gId",genebean.getGid()) ;
                    t.put("count","count");

                    //trait count
                    GwasAssociationView gwas = (GwasAssociationView) baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectGwasViewByGeneInfo",t);
                    if(gwas != null){
                        genebean.setTraitCount(gwas.getTraitCount());
                    }

                    //study count
                    StudyBean study = (StudyBean) baseService.findObjectByObject("cn.big.gvk.dm.study.selectStudyByMapGeneId",t);
                    if(study != null ){
                        genebean.setStudyCount(study.getStudyCount());
                    }

                    //publication count
                    PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubByGeneId",t);
                    if (publication != null ){
                        genebean.setPublicationCount(publication.getPublicationCount());
                    }
                    mapGeneBeanList.add(genebean) ;
                }

            }
        }

        StringBuffer sb = new StringBuffer() ;

        if(mapGeneBeanList != null && mapGeneBeanList.size()>0){
            for(MapGeneBean mapgene: mapGeneBeanList){
                sb.append(mapgene.getMapGeneId()).append("\t").append(mapgene.getMapGeneChrom()).append(":")
                        .append(mapgene.getMapGeneStart()).append("-").append(mapgene.getMapGeneEnd()).append("\t").append(mapgene.getTraitCount()).append("\t")
                        .append(mapgene.getStudyCount()).append("\n");
            }
        }

        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
    public void execFuzzySearchVariationFunc(){


        Map map = new HashMap();

        if(this.searchParam != null && this.searchParam.length()> 0 ){
            System.out.println(this.searchParam );
            if(this.searchParam.contains(":")==true || this.searchParam.startsWith("chr") == true) {
                String chrom = "";
                int pstart = -1;
                int pend = -1;
                if (this.searchParam.indexOf(":") > -1) {
                    int idex = this.searchParam.indexOf(":");
                    chrom = this.searchParam.substring(0, idex );
                    System.out.println("chrom=" + chrom);
                    map.put("chrom", chrom);
                    if (this.searchParam.indexOf("-") > -1) {
                        int idex1 = this.searchParam.indexOf("-");
                        pstart = Integer.parseInt(this.searchParam.substring(idex + 1, idex1 ));
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

        if(this.searchSpecies!= null && this.searchSpecies.equals("null") ==false && this.searchSpecies.equals("all") == false){
            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("species",this.searchSpecies);
        }

        if(this.searchTrait != null&& this.searchTrait.equals("null") ==false  && this.searchTrait.length()>0){

            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("searchTrait", this.searchTrait) ;
        }

        if(this.pvalue != null&& this.pvalue.equals("null") ==false && this.pvalue.length()>0){

            map.put("gwas","gwas") ;
            map.put("gwasend","gwasend") ;
            map.put("psitu", this.psitu);
            map.put("pval", this.pvalue) ;
        }


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

        StringBuffer sb = new StringBuffer() ;

        if(genotypeBeanList != null && genotypeBeanList.size()>0){
            for(GenotypeBean genotype: genotypeBeanList){
               sb.append(genotype.getVarId()).append("\t").append(genotype.getChrom()).append(":")
                       .append(genotype.getStartPos()).append("\t").append(genotype.getTraitCount())
                       .append("\t").append(genotype.getStudyCount()).append("\n");
            }
        }

        if(format == 1 ){ //export txt
            this.response.reset();
            this.response.setHeader("Content-Disposition",
                    "attachment;filename=export.txt");
            this.response.setContentType("application/ms-txt");
            try {
                PrintWriter pr = this.response.getWriter();
                pr.print(sb.toString());
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    public String getSearchTrait() {
        return searchTrait;
    }

    public void setSearchTrait(String searchTrait) {
        this.searchTrait = searchTrait;
    }

    public String getPsitu() {
        return psitu;
    }

    public void setPsitu(String psitu) {
        this.psitu = psitu;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setServletResponse(HttpServletResponse arg0) {
        // TODO Auto-generated method stub
        this.response = arg0;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }
}
