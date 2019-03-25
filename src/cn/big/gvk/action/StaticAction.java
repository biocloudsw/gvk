package cn.big.gvk.action;


import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticAction extends ActionSupport {
    private IBaseService baseService;

    private List<StatisticABean> statisticAList;

    private List<String> pubYear2;
    private List<Integer> yearCount;


    private List<String> traitName;
    private List<Integer> traitPubCount;

    private List<StatisticBBean> statisticBList;


    public String execStaticA(){

        statisticAList = (List<StatisticABean>) baseService.findResultList( "cn.big.gvk.dm.statistic.selectAllStatisticA", null);
        /*statisticBList = (List<StatisticBBean>) baseService.findResultList("cn.big.gvk.dm.statistic.selectPubStatisticD",null); */

        return SUCCESS;
    }

    public String execStaticB(){
        List<PublicationBean> pubYear =(List<PublicationBean>) baseService.findResultList("cn.big.gvk.dm.statistic.selectPubStatisticB", null);
        pubYear2 = new ArrayList<String>();
        yearCount =new ArrayList<Integer>();
        for(PublicationBean tmp: pubYear){

            pubYear2. add(tmp.getYear());
            yearCount.add(tmp.getPublicationCount());
        }


        return SUCCESS;
    }

    public String execStaticC(){
        List<GwasAssociationBean> traitTop10 = (List<GwasAssociationBean>) baseService.findResultList("cn.big.gvm.dm.statistic.selectPubStatisticC", null);
        traitName = new ArrayList<String>();
        traitPubCount = new ArrayList<Integer>();
        for(GwasAssociationBean tmp: traitTop10){
            traitName.add(tmp.getTraitName());
            traitPubCount.add(tmp.getPaperCount());
        }

        return SUCCESS;
    }

    public String execStaticD(){
        statisticBList = (List<StatisticBBean>) baseService.findResultList("cn.big.gvk.dm.statistic.selectPubStatisticD",null);
        return SUCCESS;
    }

    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public  List<StatisticABean> getStatisticAList(){return statisticAList;}
    public void setStatisticAList(List<StatisticABean> statisticAList){this.statisticAList=statisticAList;}



    public List<String> getPubYear2(){return pubYear2;}
    public void setPubYear2(List<String> pubYear2){this.pubYear2=pubYear2;}

    public List<Integer> getYearCount() {
        return yearCount;
    }

    public void setYearCount(List<Integer> yearCount) {
        this.yearCount = yearCount;
    }

    public List<String> getTraitName() {
        return traitName;
    }

    public void setTraitName(List<String> traitName) {
        this.traitName = traitName;
    }

    public List<Integer> getTraitPubCount() {
        return traitPubCount;
    }

    public void setTraitPubCount(List<Integer> traitPubCount) {
        this.traitPubCount = traitPubCount;
    }
    public List<StatisticBBean> getStatisticBList() {
        return statisticBList;
    }

    public void setStatisticBList(List<StatisticBBean> statisticBList) {
        this.statisticBList = statisticBList;
    }

}
