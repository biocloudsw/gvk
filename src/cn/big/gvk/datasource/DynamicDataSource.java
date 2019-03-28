package cn.big.gvk.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.CommonDataSource;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DynamicDataSource extends AbstractRoutingDataSource {
	protected Object determineCurrentLookupKey() {  
        return DatabaseContextHolder.getDataSource();   
    }

   @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
