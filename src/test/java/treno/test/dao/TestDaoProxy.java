package treno.test.dao;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treno.application.dao.Dao;
import com.treno.application.model.Motrice;
import com.treno.application.model.Vagone;

public class TestDaoProxy {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("BeansConfiguration.xml");
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("Bean --->" + beanName);
		}
		
		//Test save VagoneDao
		Motrice m = (Motrice) ctx.getBean("deMotrice");
		Dao<Vagone> daoVagoni = ( Dao <Vagone>) ctx.getBean("VagoneDao");
		//daoVagoni.save(m);
		
		
		//Test update VagoneDao
		//m.setMarca("ciao bello ho cambiato di nuovo");
		m.setIdVagone(5);
		//daoVagoni.update(m);
		
		//FindbyId
		Motrice mDiProva= (Motrice) daoVagoni.findById((long) 5);
		
		System.out.println(mDiProva);
		
		//FindByAll
		List<Vagone> vagoni = daoVagoni.findAll();
		
		for (Vagone v: vagoni) {
			System.out.println(vagoni);
		}
		
		
		
		
		
		
		
		
		ctx.close();
		

	}

}
