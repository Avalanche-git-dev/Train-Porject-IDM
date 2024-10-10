package com.treno.application.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages="com.treno")
public class AppConfiguration {

	@Bean(name="dataSource")
	public DataSource getDataSource () {
		
		DriverManagerDataSource ds = new DriverManagerDataSource(); 
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("Giorick1997.");
		ds.setUrl("jdbc:mysql://localhost:3306/trainproject");
		return ds; 
	} 

	// E' come se fosse un context per l'entity manager.
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManager() {

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		// factory.setValidationMode(ValidationMode.AUTO);
		// JDBC
		factory.setDataSource(getDataSource());
		// imposta il dialogo tra JPA e hibernate
		factory.setJpaVendorAdapter(getJpaVendorAdapter()); // imposta il dialogo tra JPA e hibernate
		// impostare il luogo dove si trovano le entity con il mapping
		factory.setPackagesToScan("com.treno.application"); // "com.corso.spring"
		// oppure "com.corso.spring...." al posto di
		// this.getClass().getPackage().getName()
		
		Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Specifica il dialect
        properties.put("hibernate.hbm2ddl.auto", "update"); // Crea o aggiorna il database
        properties.put("hibernate.show_sql", "true"); // Mostra le query SQL
        factory.setJpaProperties(properties);
		
		return factory;
	}

	private HibernateJpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL); // obbligatorio: serve per tradurre le query nel particolare Dialetto
		adapter.setGenerateDdl(true); // facoltativo, attiva il DDL cio� hibernate creer� le strutture nel DB se non
		adapter.setShowSql(true); // mostra l'SQL, comodo per i corsi e per il debug ma in produzione solitamente
		return adapter;
	}

	/*** transazioni ***/
	@Bean
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		// transactionManager.setEntityManagerFactory(getEntityManager().getObject());
		// transactionManager.setNestedTransactionAllowed(false);
		return transactionManager;
	}

	////////// DAO
//	@Bean(name="motriceDao")
//	public MotriceDao getMotriceDAO() {
//		return new MotriceDao();
//
//	}
	
	
	

}
