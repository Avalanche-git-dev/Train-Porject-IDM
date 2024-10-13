package treno.test.service;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.User;
import com.treno.application.model.Valutazione;
import com.treno.application.service.ValutazioneService;

public class TestValutazioneService01 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");

		ValutazioneService valutazioneService = (ValutazioneService) ctx.getBean("ValutazioneService");
		Dao<User> userDao = (Dao<User>) ctx.getBean("UserDao");
		
		User u = userDao.findById(2);
		
		List <Valutazione> valutazioni = valutazioneService.getValutazioniPerUtente(u.getUserId());
		
		
		for (Valutazione v : valutazioni) {
			System.out.println(v.getVotazione());
			
		}
		
		
		ctx.close();
	}

}
