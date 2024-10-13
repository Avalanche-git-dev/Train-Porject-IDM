package treno.test.service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.dao.TrenoUtility;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.service.TransazioneService;

public class TestTransazioni00 {

	public static void main(String[] args) throws Exception {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfigurationTest.xml");

		TrenoUtility trenoDao = (TrenoUtility) ctx.getBean("TrenoDao");
		Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");

		Treno treno = trenoDao.findById(10);

		User venditore = userDao.findById(8);


		User acquirente = userDao.findById(10);
		acquirente.setPortafoglio(15000000.0);
		userDao.update(acquirente);

		TransazioneService service = (TransazioneService) ctx.getBean("TransazioneService");

		// service.mettiInVendita(treno.getOwner().getUserId(), treno.getIdTreno(),
		// 3000.0);
		System.out.println(service.mettiInVendita(venditore.getUserId(), treno.getIdTreno(), 3000.0));
		 //System.out.println (service.mettiInVendita(acquirente.getUserId(), treno.getIdTreno(), 3000.0));
		// service.compraTreno(acquirente, treno);

		service.compraTreno(acquirente.getUserId(), treno.getIdTreno());

		ctx.close();

	}
}