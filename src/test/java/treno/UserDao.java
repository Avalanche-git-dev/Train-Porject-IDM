package treno;




import java.util.List;

import com.treno.application.dao.ProxyDao;
import com.treno.application.dao.UserUtility;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;

import jakarta.persistence.NoResultException;



public class UserDao extends ProxyDao<User> implements UserUtility{

	public UserDao() {
		super(User.class);
	}

	


	    @Override
	    public User findByUsername(String username) {
	        String hql = "FROM User u WHERE u.username = :username";
	        try {
	            return em.createQuery(hql, User.class)
	                     .setParameter("username", username)
	                     .getSingleResult();
	        } catch (NoResultException e) {
	            return null; // Nessun utente trovato con questo username
	        }



	
}




		@Override
		public List<User> filtraUtenti(UtenteFilter filtro, long userId) {
			return null;
		}




}
