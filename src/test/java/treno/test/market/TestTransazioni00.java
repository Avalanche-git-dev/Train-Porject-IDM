package treno.test.market;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.application.service.TransazioneService;

public class TestTransazioni00 {

	public static void main(String[] args) throws Exception {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TrenoBuilder builder = ctx.getBean(TBuilder.class);
		// System.out.println(builder);

		Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");

		Treno treno = builder.crealoRapido();
		
		treno.setIdTreno(30);

		User marco = new User();
		marco.setNome("marco");
		treno.setOwner(marco);
		marco.setUserId(25);

		System.out.println(treno.getOwner().getNome());

		User pippo = new User();
		pippo.setNome("pippo");

//		for (String beanName : ctx.getBeanDefinitionNames()) {
//			System.out.println("Bean --->" + beanName);
//		}

		// Lo so esistono i bean che palle..ormai sono qua , senza component se creo
		// l'istanza a mano fallisce tutto. ecco perchè
		// TransazioneService transazione = new TransazioneService();

		TransazioneService transazione = ctx.getBean(TransazioneService.class);
		try {
			transazione.mettereInVendita(treno, marco);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		pippo.setUserId(30);
		System.out.println(pippo.getUserId());
		transazione.compraTreno(pippo,treno);

		//transazione.mettereInVendita(treno, marco);

		System.out.println(treno.getOwner().getNome());
		
		
		
		//transazione.acquistaTreno(x, pippo, 30);
		System.out.println(treno.getOwner().getNome());
		
		//rivendiamo il treno proviamo un po
//		transazione.mettereInVendita(treno, pippo);
//		Transazione x2 = transazione.getMarket();
		
//		
		System.out.println(treno.getOwner().getNome());

	}

}
