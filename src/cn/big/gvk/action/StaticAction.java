package cn.big.gvk.action;

import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticAction extends ActionSupport {
    private IBaseService baseService;
    private List<StatisticABean> statisticAList;

    public String execStaticA(){

        this.statisticAList = (List<StatisticABean>) baseService.findResultList( "cn.big.gvk.dm.Statistic.selectAllStatisticA", null);
        return SUCCESS;
    }


    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public  List<StatisticABean> getStatisticAList(){return statisticAList;}
    public void setStatisticAList(List<StatisticABean> statisticAList){this.statisticAList=statisticAList;}


}
