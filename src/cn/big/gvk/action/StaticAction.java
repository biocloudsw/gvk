package cn.big.gvk.action;

<<<<<<< HEAD

=======
>>>>>>> origin/master
import cn.big.gvk.po.*;
import cn.big.gvk.service.IBaseService;
import cn.big.gvk.util.Page;
import com.opensymphony.xwork2.ActionSupport;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> origin/master
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticAction extends ActionSupport {
    private IBaseService baseService;
    private List<StatisticABean> statisticAList;
<<<<<<< HEAD
    private List<PublicationBean> pubBList;
    /*private List<String> pubYear; */
    private List<String> pubYear2;

    private List<Integer> yearCount;


    public String execStaticA(){

        this.statisticAList = (List<StatisticABean>) baseService.findResultList("cn.big.gvk.dm.Statistic.selectAllStatisticA", null);


=======

    public String execStaticA(){

        this.statisticAList = (List<StatisticABean>) baseService.findResultList( "cn.big.gvk.dm.Statistic.selectAllStatisticA", null);
>>>>>>> origin/master
        return SUCCESS;
    }


<<<<<<< HEAD
    public String execStaticB(){
        List<PublicationBean> pubYear =(List<PublicationBean>) baseService.findResultList("cn.big.gvk.dm.Statistic.selectPubStatisticB", null);
        pubYear2 = new ArrayList<String>();
        yearCount =new ArrayList<Integer>();
        for(PublicationBean tmp: pubYear){

            pubYear2. add(tmp.getYear());
            yearCount.add(tmp.getPublicationCount());
        }


        return SUCCESS;
    }
=======
>>>>>>> origin/master
    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }

    public  List<StatisticABean> getStatisticAList(){return statisticAList;}
    public void setStatisticAList(List<StatisticABean> statisticAList){this.statisticAList=statisticAList;}

<<<<<<< HEAD
    public List<PublicationBean> getPubBList(){return pubBList;}
    public void  setPubBList(List<PublicationBean> pubBList){this.pubBList=pubBList;}

    /*public List<String> getPubYear(){return pubYear;}
    public void setPubYear(List<String> pubYear){this.pubYear=pubYear;} */

    public List<String> getPubYear2(){return pubYear2;}
    public void setPubYear2(List<String> pubYear2){this.pubYear2=pubYear2;}

    public List<Integer> getYearCount() {
        return yearCount;
    }

    public void setYearCount(List<Integer> yearCount) {
        this.yearCount = yearCount;
    }
}
=======

}
>>>>>>> origin/master
