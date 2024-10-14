package treno.test.user;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.configuration.Factory;
import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.dao.Dao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.service.UserService;

public class TestUserCreaTreno {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		
        UserService uservice = ctx.getBean(UserService.class);
        Factory f = ctx.getBean(FactoryConfiguration.class);
		f.setMarca("de");
        
        User user = uservice.getUserDao().findById(15);
        
        // Treno t = uservice.getTrenoBuilder().crealoRapido();
        
        @SuppressWarnings("unchecked")
		Dao<Treno> trenoDao = (Dao<Treno>) ctx.getBean("TrenoDao");
		// trenoDao.update(t);
        
        TrenoFilter filtro = new TrenoFilter();
        filtro.setPrezzoMin(1700);
        filtro.setPrezzoMax(2200);
        
        List<Treno> treniFiltrati = ((TrenoDao) trenoDao).filtraTrenoByParametro(filtro);

        for (Treno trenoFiltrato : treniFiltrati) {
            System.out.println(trenoFiltrato);
        }
        
		ctx.close();
	}

}
