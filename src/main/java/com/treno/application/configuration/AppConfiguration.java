package com.treno.application.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.treno.application.dao.TransazioneDao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.dao.UserDao;
import com.treno.application.dao.VagoneDao;
import com.treno.application.dao.ValutazioneDao;
import com.treno.application.model.Transazione;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.service.TransazioneService;
import com.treno.application.service.TrenoService;
import com.treno.application.service.UserService;
import com.treno.application.service.ValutazioneService;

@Configuration
@EnableTransactionManagement
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfiguration {

	@Bean(name="dataSource")
	public DataSource getDataSource () {
		
		DriverManagerDataSource ds = new DriverManagerDataSource(); 
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("momo");
		ds.setUrl("jdbc:mysql://localhost:3307/hibernate_db");
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
	
	//Configurazione della Factory
	
	@Bean(name = "Factory")
	public FactoryConfiguration getFactoryConfiguration() {
		return new FactoryConfiguration();
	}
	
	//Configurazione del builder
	
	@Bean (name = "Builder")
	//@Scope("prototype")
	public TBuilder getTrenoBuilder() {
		return new TBuilder();
	}
	
	//Bean Di configurazione che ne ho abbastanza.
	
	@Bean(name = "VagoneDao" )
	@Scope("prototype")
	public VagoneDao getVagoneDao() {
		return new VagoneDao();
	}
	
	@Bean(name = "UserDao" )
	@Scope("prototype")
	public UserDao getUserDao() {
		return new UserDao();
	}
	
	@Bean(name = "TrenoDao")
	@Scope("prototype")
	public TrenoDao getTrenoDao() {
		return new TrenoDao();
	}
	
	@Bean (name = "TransazioneDao")
	@Scope("prototype")
	public TransazioneDao getTransazioneDao() {
		return new TransazioneDao();
		
	}
	
	@Bean (name = "ValutazioneDao")
	@Scope("prototype")
	public ValutazioneDao getValutazioneDao() {
		return new ValutazioneDao();
	}
	
	//Service Bean
	
	@Bean (name = "ValutazioneService")
	@Scope("prototype")
	public ValutazioneService getValutazioneService() {
		return new ValutazioneService();
	}
	
	@Bean (name = "TrenoService")
	@Scope("prototype")
	public TrenoService getTrenoService() {
		return new TrenoService();
	}
	
	@Bean (name = "UserService")
	@Scope("prototype")
	public UserService getUserService() {
		return new UserService();
	}
	
	@Bean (name = "TransazioneService")
	@Scope ("prototype")
	public TransazioneService getTransazioneService() {
		return new TransazioneService();
		
	}

	
	
	
	//Bean Component
	
	@Bean ("Transazione")
	@Scope ("prototype")
	public Transazione getTransazione() {
		return new Transazione();
	}
	
	
	
}
	
	
	
	
	

	
	


