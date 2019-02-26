package cn.big.gvk.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.CommonDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	protected Object determineCurrentLookupKey() {  
        return DatabaseContextHolder.getDataSource();   
    }  
}
