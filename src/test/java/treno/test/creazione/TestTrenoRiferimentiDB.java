package treno.test.creazione;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.Treno.Valutazione;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;

public class TestTrenoRiferimentiDB {

	public static void main(String[] args) {
		
		
		System.out.println("Inserisci una Stringa per creare il tuo treno");
		Scanner scanner = new Scanner (System.in);
		String input = scanner.nextLine();
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TrenoBuilder builder = ctx.getBean(TBuilder.class);
		//System.out.println(builder);
		
		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
		
		Treno treno = builder.creaTrenoDaStringa(input);
		
		treno.addValutazione(Valutazione.UNO);
		treno.addValutazione(Valutazione.DUE);
		treno.addValutazione(Valutazione.TRE);
		treno.addValutazione(Valutazione.QUATTRO);
		treno.addValutazione(Valutazione.CINQUE);
		System.out.println(treno.getSigla()+treno.getValutazioni());
		
		
		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("trenoDao");
		trenoDao.save(treno);
		

	}

}
