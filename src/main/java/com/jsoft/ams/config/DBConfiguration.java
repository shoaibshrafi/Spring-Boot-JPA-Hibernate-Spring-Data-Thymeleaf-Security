package com.jsoft.ams.config;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jsoft.ams.multitenancy.TenantContextHolder;

@Configuration
@MapperScan(basePackages = {"com.jsoft.ams.**.repository"}) // Scan DAO
@EnableTransactionManagement
public class DBConfiguration {

	enum DATABASE{H2, MYSQL};
    private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap();
    private AbstractRoutingDataSource multiTenantDataSource;
        
    @Bean("masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
    	return new EmbeddedDatabaseBuilder()
    	        .setName("master")
    	        .setType(EmbeddedDatabaseType.H2)
    	        .addScript("classpath:schema-master.sql")
    	        .addScript("classpath:data-master.sql")
    	        .build();
    	
    	//return DataSourceBuilder.create().build();
    }    
    
    @Bean("dataSource")
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "local")
    public DataSource dataSourceLocal(@Qualifier("masterDataSource") DataSource masterDataSource) {
        multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				// TODO Auto-generated method stub
				return TenantContextHolder.getTenant();
			}
			
        };
        multiTenantDataSource.setTargetDataSources(tenantDataSources);
        multiTenantDataSource.setDefaultTargetDataSource(masterDataSource);
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    @Bean("dataSource")
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "unit-test")
    public DataSource dataSourceUnitTest(@Qualifier("masterDataSource") DataSource masterDataSource) {
        multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				// TODO Auto-generated method stub
				return TenantContextHolder.getTenant() == null ? "1" : TenantContextHolder.getTenant();
			}
			
        };
        multiTenantDataSource.setTargetDataSources(tenantDataSources);
        multiTenantDataSource.setDefaultTargetDataSource(masterDataSource);
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource datasource) throws Exception {
    	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    	SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    	sessionFactory.setDataSource(datasource);
    	sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
    	sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
    	return sessionFactory.getObject();
    }
    
    public void addTenant(Long id, String db, String url, String username, String password) throws Exception {

        DataSource dataSource = 
        		/*DataSourceBuilder.create()
                .driverClassName(getDriverClassName(db))
                .url(url)
                .username(username)
                .password(password)
                .build();*/
        		new EmbeddedDatabaseBuilder()
    	        .setName(id.toString())
    	        .setType(EmbeddedDatabaseType.H2)
    	        .addScript("classpath:schema-tenant.sql")
    	        .addScript("classpath:data-tenant.sql")
    	        .build();

        // Check that new connection is 'live'. If not - throw exception
        try(Connection c = dataSource.getConnection()) {
            tenantDataSources.put(id.toString(), dataSource);
            multiTenantDataSource.afterPropertiesSet();
        }
    }

    public static String getDriverClassName(String db) throws Exception {    	
    	
    	if(db.equals(DATABASE.H2.name())) {
    		return "org.h2.Driver";
    	}else if(db.equalsIgnoreCase(DATABASE.MYSQL.name())) {
    		return "com.mysql.cj.jdbc.Driver";
    	}else {
    		throw new Exception("Invalid database vendor configured.");
    	}
    }

    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
      return new ConfigurationCustomizer() {
		
		@Override
		public void customize(org.apache.ibatis.session.Configuration configuration) {
			configuration.setMapUnderscoreToCamelCase(true);
			
		}

      };
    }
}
