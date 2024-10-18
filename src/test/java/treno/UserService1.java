package treno;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;
import com.treno.application.utility.UserUtility;
import com.treno.eccezzioni.InvalidPasswordException;
import com.treno.eccezzioni.UserAlreadyExistsException;
import com.treno.eccezzioni.UserNotFoundException;

@Service
public class UserService1 {

    @Autowired
    @Qualifier("UserDao")
    private UserUtility userDao;

    public Dao<User> getUserDao() {
        return userDao;
    }

    public void setUserDao(Dao<User> userDao) {
        this.userDao = (UserUtility) userDao;
    }

    @Transactional
    public String registra(UserDTO userDto) {
        User utenteEsistente = userDao.findByUsername(userDto.getUsername());
        if (utenteEsistente != null) {
            throw new UserAlreadyExistsException("Utente già registrato con questo username!");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // Aggiungi qui la logica di hashing se necessario
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setTelefono(userDto.getTelefono());
        user.setEmail(userDto.getEmail());
        user.setPortafoglio(0);
        userDao.save(user);
        return "Registrazione avvenuta con successo!";
    }

    public void logout() {
    }

    @Transactional
    public void cancellaAccount(UserDTO userDto) {
        User user = userDao.findById(userDto.getUserId());
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato per cancellazione");
        }
        userDao.delete(user);
    }

    public UserDTO findById(long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato con ID: " + id);
        }
        return convertToDTO(user);
    }

    @Transactional
    public void update(UserDTO userDto) {
        User user = userDao.findById(userDto.getUserId());
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato per aggiornamento");
        }
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPortafoglio(userDto.getPortafoglio());
        user.setStato(User.Stato.valueOf(userDto.getStato()));
        userDao.update(user);
    }

    public void login(UserDTO userDto) {
        User user = userDao.findByUsername(userDto.getUsername());
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato");
        }

        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new InvalidPasswordException("Password non corretta");
        }
    }

    public UserDTO findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Utente non trovato con username: " + username);
        }
        return convertToDTO(user);
    }

    public List<UserDTO> filtraUtenti(UtenteFilter filtro, long userId) {
        List<User> utenti = userDao.filtraUtenti(filtro, userId);
        if (utenti.isEmpty()) {
            throw new UserNotFoundException("Nessun utente trovato con il filtro specificato");
        }
        return utenti.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPortafoglio(user.getPortafoglio());
        userDto.setStato(user.getStato().name());
        return userDto;
    }
}