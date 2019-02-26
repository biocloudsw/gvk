package cn.big.gvk.action;

import cn.big.gvk.po.GwasAssociationBean;
import cn.big.gvk.po.PublicationBean;
import cn.big.gvk.po.tree.Node;
import cn.big.gvk.po.tree.Nodep;
import cn.big.gvk.po.tree.NodeState;
import cn.big.gvk.po.tree.NodeData;
import cn.big.gvk.po.tree.NodeliAttr;
import cn.big.gvk.po.tree.NodeChild;
import cn.big.gvk.po.tree.GoGeneBean;
import cn.big.gvk.po.TermInformationBean;

import cn.big.gvk.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;

import cn.big.gvk.po.tree.GoTermBean;
import cn.big.gvk.po.tree.GoTermDefinition;
import cn.big.gvk.po.tree.GoTermSynonym;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/************************************************************************
 * excute browse ontology
 */
public class OntologyAction extends ActionSupport {
    private String id;
    private List<GoTermBean> gotermList;
    private List<GoTermBean> ontologyTermList; //autocomplete
    private GoTermBean gotermbean;
    private Node node;
    private Nodep nodep;
    private String goacc;
    private GoTermDefinition gotermdefinition;
    private List<GoTermBean> goparentList; // go parent
    private String gowordsynonym;
    private String goidsynonym;

    private String nameStartsWith;
    private int ontologyType;


    private IBaseService baseService;

    /******************************************************************
     * this is used to get go child term list
     * and a detail information for given term
     * @return
     */
    public String execGetGoTermTreeAndGoDetailFunc(){
        if(goacc != null){
            //gotermList = baseService.findResultList("cn.ac.big.idog.go.selectGoTermList", goacc);
            if(goacc.indexOf("TO:") == -1){
                goacc = "TO:"+goacc.substring(2,goacc.length()) ;
            }

            //here to get detail information of given go acc
            gotermbean = (GoTermBean)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermByAcc", goacc);
            gotermdefinition = (GoTermDefinition)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermDefinition", goacc);
            goparentList = (List<GoTermBean>)baseService.findResultList("cn.big.gvk.go.selectGOParentTerm", goacc);
            if(goparentList != null && goparentList.size() > 0 ){
                GoTermBean temp_goterm = goparentList.get(0);
                gotermbean.setGoParentTerm(temp_goterm.getGoterm());
            }else if(goacc.equals("TO:0000387") == true){
                gotermbean.setGoParentTerm(null);
            }

            List<GoTermSynonym> list= baseService.findResultList("cn.big.gvk.go.selectGoTermSynonym", goacc);
            StringBuffer sb = new StringBuffer();
            StringBuffer sbid = new StringBuffer();
            if(list != null ){
                for(GoTermSynonym termsynonym:list ){
                    if(sb.toString().length() > 0 ){
                        sb.append(" | ").append(termsynonym.getGotermsynonym());
                    }else{
                        sb.append(termsynonym.getGotermsynonym());
                    }

                }

            }

            if(sb.toString().length()>0){
                gowordsynonym = sb.toString();
            }

            if(sbid.toString().length() > 0 ){
                goidsynonym = sbid.toString();
            }

            int termid = 0;
            if(gotermbean != null ){
                termid =  gotermbean.getGotermid();

                int trait_id= 0;
                //get trait_id
                TermInformationBean t_termbean =(TermInformationBean) baseService.findObjectByObject("cn.big.gvk.dm.Term.selectTraitByTermId",termid);
                if(t_termbean != null ){
                    trait_id= t_termbean.getTraitId();
                    gotermbean.setTraitid(trait_id);

                    //trait count
                    Map cmap = new HashMap();
                    cmap.put("traitId",trait_id);

                    GwasAssociationBean tg_bean = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectAssociationCountByTraitId",cmap);
                    if(tg_bean != null ){
                        gotermbean.setAssociationCount(tg_bean.getGwasCount());
                    }

                    //study count
                    GwasAssociationBean tg_bean1 = (GwasAssociationBean)baseService.findObjectByObject("cn.big.gvk.dm.GwasAssociation.selectStudyCountByTraitid",cmap);
                    if(tg_bean1 != null ){
                        gotermbean.setStudyCount(tg_bean1.getGwasCount());
                    }

                    //publication count
                    PublicationBean publication = (PublicationBean)baseService.findObjectByObject("cn.big.gvk.dm.publication.selectPubCountByTraitId",cmap);
                    if (publication != null ){
                        gotermbean.setPaperCount(publication.getPublicationCount());
                    }
                }


            }







        }

        return SUCCESS;
    }

   //this is used to get ontology term
    public String execGetOntologyTermFunc(){
        Map map = new HashMap();
        if(ontologyType == 1){
            map.put("termtype",1);
        }

        map.put("goterm",nameStartsWith);

        ontologyTermList = (List<GoTermBean>) baseService.findResultList("cn.big.gvk.go.selectTerm",map);

        return SUCCESS;
    }




    /*****************************************
     * this is used to get go term json file
     * @return
     */
    public String execGetGoTermJson(){

        String srcid="";
        GoGeneBean tbean = null;
        if(this.id == null || this.id.trim().length() == 0 || this.id.trim().equals("#") ){

            this.id="TO:0000387";
            srcid = this.id;
            gotermList = baseService.findResultList("cn.big.gvk.go.selectGoTermList", id);
            gotermbean = (GoTermBean)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermByAcc", id);
            node = new Node();
            this.id= this.id.replaceAll(":", "");
            node.setId(this.id);
            node.setText("plant trait");
            NodeState nodestate = new NodeState();
            nodestate.setOpened(true);
            nodestate.setDisabled(false);
            nodestate.setSelected(false);
            NodeData nodedata = new NodeData();
            NodeliAttr liattr = new NodeliAttr();
            liattr.setAccID(this.id);
            nodedata.setAccID(this.id);



            if(gotermbean.getGeneCount() != null && gotermbean.getGeneCount().indexOf("gene")>-1){

                String label = gotermbean.getGeneCount();
                nodedata.setAnnotationLabel(label);
                nodedata.setAnnotationUrl("/browse/showgogene.action?id="+gotermbean.getGoacc()+"&totalCount="+label);
            }


            node.setData(nodedata);
            node.setState(nodestate);
            node.setLi_attr(liattr);
            List<NodeChild> nodelist = new ArrayList<NodeChild>();
            if(gotermList != null ){

                for(GoTermBean goterm:gotermList){
                    if(goterm != null ){
                        NodeChild tnode = new NodeChild();
                        String idstr = goterm.getGoacc();
                        srcid = idstr;
                        idstr= idstr.replaceAll(":", "");
                        tnode.setId(idstr);
                        tnode.setText(goterm.getGoterm()) ; //name

                        if(goterm.getHasChildren()>0){
                            tnode.setChildren(true);
                        }
                        NodeData tnodedata = new NodeData();
                        NodeliAttr tliattr = new NodeliAttr();
                        tliattr.setAccID(goterm.getGoacc());
                        tnodedata.setAccID(goterm.getGoacc());

                        String label = "";
                        if(goterm.getGeneCount() != null ){
                            label = goterm.getGeneCount();

                            tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount="+label);
                        }else{
                            label = "0";
                            tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount=-1");


                        }
                        tnodedata.setAnnotationLabel(label);



                        tnodedata.setAnnotationLabel(label);


                        tnode.setData(tnodedata);
                        tnode.setLi_attr(tliattr);

                        nodelist.add(tnode);
                    }
                }
            }
            node.setChildren(nodelist);
        }else if (this.id!= null && this.id.trim().equals("#") ==false){
            System.out.println("load gotermList");
            String sid = "TO:"+this.id.substring(2,this.id.length());
            gotermList = baseService.findResultList("cn.big.gvk.go.selectGoTermList", sid);
            gotermbean = (GoTermBean)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermByAcc", sid);
            node = new Node();
            node.setId(this.id);
            node.setText(gotermbean.getGoterm());

            NodeData nodedata = new NodeData();
            NodeliAttr liattr = new NodeliAttr();
            liattr.setAccID(this.id);
            nodedata.setAccID(this.id);

            srcid = sid;
            int genecount = 0;
            //nodedata.setAnnotationLabel(gotermbean.getGeneCount()+" genes");
            String label="";
            tbean = null;
            if(gotermbean.getGeneCount() != null && gotermbean.getGeneCount().indexOf("gene")>-1){
                label = gotermbean.getGeneCount();
                int gocount= 0 ;
                nodedata.setAnnotationLabel(label);
                nodedata.setAnnotationUrl("/go/showgogene.action?id="+gotermbean.getGoacc()+"&totalCount="+gocount);
            }else{
                nodedata.setAnnotationLabel("0");
                nodedata.setAnnotationUrl("/go/showgogene.action?id="+gotermbean.getGoacc()+"&totalCount=-1");
            }




            node.setData(nodedata);
            node.setLi_attr(liattr);



            List<NodeChild> nodelist = new ArrayList<NodeChild>();
            if(gotermList != null ){
                for(GoTermBean goterm:gotermList){
                    if(goterm != null ){
                        NodeChild tnode = new NodeChild();
                        String idstr = goterm.getGoacc();
                        srcid = idstr;
                        idstr= idstr.replaceAll(":", "");
                        tnode.setId(idstr);
                        tnode.setText(goterm.getGoterm()) ; //name

                        if(goterm.getHasChildren() > 0 ){
                            tnode.setChildren(true);
                        }else{
                            tnode.setChildren(false);
                        }

                        NodeData tnodedata = new NodeData();
                        NodeliAttr tliattr = new NodeliAttr();
                        tliattr.setAccID(goterm.getGoacc());
                        tnodedata.setAccID(goterm.getGoacc());


                        if(goterm.getGeneCount() != null){
                            label = goterm.getGeneCount();

                            int gocount= 0 ;

                            tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount="+gocount);
                        }else{
                            label = "0";
                            tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount=-1");

                        }

                        tnodedata.setAnnotationLabel(label);

                        tnode.setData(tnodedata);
                        tnode.setLi_attr(tliattr);

                        nodelist.add(tnode);
                    }
                }

            }
            node.setChildren(nodelist);
        }


        return SUCCESS;
    }

    /********************************************************
     * this is used to get childterm, but we also need go the parent term
     * we need to extend the child's parent node, select current node and close all other node
     * @return
     */
    public String execGetChildTermJsonFunc(){
        //a recursize will be used to get the root node

        System.out.println("execGetChildTermJsonFunc");
        if(this.id!= null ){

            //select current goacc information
            String acc = this.id;
            List acclist = new ArrayList();
            acclist.add(acc);
            execRecursiveParentTerm(acc,acclist);
            String label="";

            Map map = new HashMap();
            Nodep tnode = null;
            if(acclist.size() > 0 ){
                int listsize = acclist.size();
                //			System.out.println(" list size="+listsize);
                for(int i=listsize-1;i>0;i--){

                    String curacc = (String)acclist.get(i);
                    System.out.println("----"+curacc);
                    String nextacc = "";
                    if(i >= 1 ){
                        nextacc = (String)acclist.get(i-1);
                    }

                    String srcid = curacc;


                    if(map.get(curacc)!= null){
                        tnode = (Nodep)map.get(curacc);
                    }else{
                        //find go term
                        GoTermBean	cgotermbean = (GoTermBean)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermByAcc", srcid);


                        tnode = new Nodep();

                        String cid = curacc.replaceAll(":", "");
                        System.out.println("cid=="+cid);

                        tnode.setId(cid);

                        tnode.setText(cgotermbean.getGotermtype());
                        NodeState nodestate = new NodeState();
                        nodestate.setOpened(true);
                        nodestate.setDisabled(false);
                        nodestate.setSelected(false);
                        NodeData nodedata = new NodeData();
                        NodeliAttr liattr = new NodeliAttr();
                        liattr.setAccID(cid);
                        nodedata.setAccID(cid);

                        if(cgotermbean.getGeneCount()!=null){
                            label = cgotermbean.getGeneCount();

                            int gocount= 0 ;

                            nodedata.setAnnotationUrl("/go/showgogene.action?id="+cgotermbean.getGoacc()+"&totalCount="+gocount);

                        }else{

                            label = "0 ";
                            nodedata.setAnnotationUrl("/go/showgogene.action?id="+cgotermbean.getGoacc()+"&totalCount=-1");



                        }

                        nodedata.setAnnotationLabel(label);


                        tnode.setData(nodedata);
                        tnode.setState(nodestate);
                        tnode.setLi_attr(liattr);
                        if(i==(listsize-1)){
                            nodep = tnode;
                        }

                    }





                    //then get children term
                    List<GoTermBean> cgotermList = baseService.findResultList("cn.big.gvk.go.selectGoTermList", curacc);


                    List<Nodep> nodelist = new ArrayList<Nodep>();
                    if(cgotermList != null ){

                        for(GoTermBean goterm:cgotermList){
                            if(goterm != null ){

                                Nodep cnode = new Nodep();
                                NodeState nodestate = new NodeState();

                                nodestate.setDisabled(false);

                                String idstr = goterm.getGoacc();
                                srcid = idstr;
                                if(nextacc!= null && nextacc.length() > 0 ){
                                    if(srcid.equals(nextacc)){
                                        nodestate.setOpened(true);
                                    }else{
                                        nodestate.setOpened(false);
                                    }


                                }
                                if(srcid.equals(acc) == true){
                                    nodestate.setSelected(true);
                                }else{
                                    nodestate.setSelected(false);
                                }

                                idstr= idstr.replaceAll(":", "");
                                cnode.setId(idstr);
                                cnode.setText(goterm.getGoterm()) ; //name


                                NodeData tnodedata = new NodeData();
                                NodeliAttr tliattr = new NodeliAttr();
                                tliattr.setAccID(goterm.getGoacc());
                                tnodedata.setAccID(goterm.getGoacc());

                                if(goterm.getGeneCount()!=null){
                                    label = goterm.getGeneCount();

                                    int gocount= 0 ;

                                    tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount="+gocount);

                                }else{

                                    label = "0 genes,0 annotation";
                                    tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc()+"&totalCount=-1");




                                }
                                tnodedata.setAnnotationLabel(label);
                                //	tnodedata.setAnnotationUrl("/go/showgogene.action?id="+goterm.getGoacc());

                                cnode.setData(tnodedata);
                                cnode.setLi_attr(tliattr);
                                cnode.setState(nodestate);
                                if(nextacc!= null && nextacc.length() > 0 ){
                                    if(srcid.equals(nextacc)){
                                        map.put(srcid, cnode) ;
                                    }
                                }


                                nodelist.add(cnode);
                            }
                        }
                    }
                    if(tnode != null){
                        System.out.println("tnode acc="+tnode.getId());
                        tnode.setChildren(nodelist);
                    }


                }
            }



        }
        return SUCCESS;
    }


    /***************************************
     * this is used to recursive to get parent go term
     * @return
     */
    private void  execRecursiveParentTerm(String acc,List acclist){

        //get parent, may be have many, only get the first parent and the parent need to opened

        String tacc = "";
        List<GoTermBean>  list =  baseService.findResultList("cn.big.gvk.go.selectParentGoTermList", acc);
        if(list != null && list.size() > 0 ){
            //		System.out.println("list size="+list.size());
            GoTermBean go = (GoTermBean)list.get(0); // get the first parent

            //get parent all children list and set the selected for the finded children
            if(go != null ){
                tacc = go.getGoacc();

                //		System.out.println("=====pacc="+tacc);
                if(tacc.equals("all") == true){
                    return;
                }
                else if(tacc.equals("TO:0000387") == false ){
                    acclist.add(tacc);
                    execRecursiveParentTerm(tacc,acclist);
                }else{
                    return;
                }
            }
        }


    }
    /**********************************
     * this is used to get gene count of given go term
     * @param sid
     * @return
     */
    private GoGeneBean getGeneCount(String sid){
        GoGeneBean tbean = null;

        if(sid.indexOf("TO:") == -1){
            sid = "TO:"+this.id.substring(2,goacc.length()) ;
        }

        //here to get detail information of given go acc
        GoTermBean tgotermbean = (GoTermBean)baseService.findObjectByObject("cn.big.gvk.go.selectGoTermByAcc", sid);

        //get gene list for GO annotation,get gene id
        if(tgotermbean != null ){
            String geneid = tgotermbean.getGeneId();
            if(geneid != null && geneid.equals("-") == false){
                List list = new ArrayList();
                if(geneid.indexOf("|") > - 1){
                    String [] genes = geneid.split("\\|");
                    if(genes!= null ){
                        for(String tgene: genes){
                            list.add(tgene);
                        }
                    }
                }else {
                    list.add(geneid);
                }

                Map map = new HashMap();
                map.put("count", "count") ;
                map.put("list", list) ;
                //map.put("goname", gotermbean.getGoterm());

                tbean = (GoGeneBean) baseService.findObjectByObject("cn.big.gvk.go.selectGOGeneByList", map);
                int gocount =  tbean.getGocount();
                int genecount = tbean.getRescount();
                //if(genecount > 0 ){
                Map tmap = new HashMap();
                String gores = "'"+genecount+" gene,"+gocount+" annotation'";
                tmap.put("genecount", gores);

            }
        }

        return tbean;
    }

    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GoTermBean> getGotermList() {
        return gotermList;
    }

    public void setGotermList(List<GoTermBean> gotermList) {
        this.gotermList = gotermList;
    }

    public GoTermBean getGotermbean() {
        return gotermbean;
    }

    public void setGotermbean(GoTermBean gotermbean) {
        this.gotermbean = gotermbean;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Nodep getNodep() {
        return nodep;
    }

    public void setNodep(Nodep nodep) {
        this.nodep = nodep;
    }

    public String getGoacc() {
        return goacc;
    }

    public void setGoacc(String goacc) {
        this.goacc = goacc;
    }

    public GoTermDefinition getGotermdefinition() {
        return gotermdefinition;
    }

    public void setGotermdefinition(GoTermDefinition gotermdefinition) {
        this.gotermdefinition = gotermdefinition;
    }

    public List<GoTermBean> getGoparentList() {
        return goparentList;
    }

    public void setGoparentList(List<GoTermBean> goparentList) {
        this.goparentList = goparentList;
    }

    public String getGowordsynonym() {
        return gowordsynonym;
    }

    public void setGowordsynonym(String gowordsynonym) {
        this.gowordsynonym = gowordsynonym;
    }

    public String getGoidsynonym() {
        return goidsynonym;
    }

    public void setGoidsynonym(String goidsynonym) {
        this.goidsynonym = goidsynonym;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public void setNameStartsWith(String nameStartsWith) {
        this.nameStartsWith = nameStartsWith;
    }

    public int getOntologyType() {
        return ontologyType;
    }

    public void setOntologyType(int ontologyType) {
        this.ontologyType = ontologyType;
    }

    public List<GoTermBean> getOntologyTermList() {
        return ontologyTermList;
    }

    public void setOntologyTermList(List<GoTermBean> ontologyTermList) {
        this.ontologyTermList = ontologyTermList;
    }
}


