package treno.test.dao;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.Factory;
import com.treno.application.dao.Dao;
import com.treno.application.model.Treno;
import com.treno.application.model.builder.TBuilder;

import treno.FactoryConfiguration;

public class TestTrenoRiferimentiDB {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.println("Inserisci una Stringa per creare il tuo treno");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TBuilder builder = ctx.getBean(TBuilder.class);
		// System.out.println(builder);

		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");

		Treno treno = builder.creaTrenoDaStringa(input);

	
		System.out.println(treno.getSigla() + treno.getValutazioni());

		@SuppressWarnings("unchecked")
		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("trenoDao");
		trenoDao.save(treno);
		ctx.close();

	}

}
