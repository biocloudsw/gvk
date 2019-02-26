package cn.big.gvk.datasource;
import org.aspectj.lang.JoinPoint;
public class DataSourceInterceptor {
	public void setdataSourceOne(JoinPoint jp) {  
        DatabaseContextHolder.setDataSource("dataSource");  
    }  
      
    public void setdataSourceTwo(JoinPoint jp) {  
        DatabaseContextHolder.setDataSource("dataSource"); 
    }  
}
