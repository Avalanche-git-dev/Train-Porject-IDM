package treno.test.filtri;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.dao.TrenoUtility;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

public class TestFiltroTreno {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		TrenoUtility trenoDao = (TrenoUtility) ctx.getBean("TrenoDao");
		
		TrenoFilter filtro = new TrenoFilter();
        // filtro.setPrezzoMin(1000);
        // filtro.setPrezzoMax(5000);
        
        // filtro.setPesoMin(50);
        // filtro.setPesoMax(200);
		
		filtro.setSigla("HP");
        
        List<Treno> treniFiltrati = trenoDao.filtraTrenoByParametro(filtro);
        
        if(treniFiltrati.size() == 0) {
        	System.out.println("Nessun treno corrisponde a questa ricerca");
        } else {
        	for (Treno trenoFiltrato : treniFiltrati) {
        		System.out.println(trenoFiltrato.toString());
        	}
        }
        ctx.close();
		
	}
	
}
