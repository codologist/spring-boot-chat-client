package challenge.controller;

import challenge.models.LoginSession;
import challenge.models.User;
import challenge.repositories.LoginRepository;
import challenge.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class LoginController {

    Logger logger = Logger.getLogger("LoginController");

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRespository userRespository;

    private final long ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    protected String getSessionString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder session = new StringBuilder();
        Random rnd = new Random();
        while (session.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            session.append(SALTCHARS.charAt(index));
        }
        String saltStr = session.toString();
        return saltStr;

    }

    private LoginSession getExistingSession(int userId){
        return loginRepository.findById(userId).orElse(null);
    }

    @PostMapping(value = "/login")
    public LoginSession login(@Valid @RequestBody User user) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();
        Example<User> example = Example.of(user, exampleMatcher);
        logger.info("User logging in: " + user.getUser_id() + "," + user.getUsername() + "," + user.getPassword());
        User loggedInUser = userRespository.findOne(example).orElse(null);
        if(loggedInUser == null) {
            throw new IllegalArgumentException("User not found in the database " + user.getUsername());
        }
        LoginSession existingSession = getExistingSession(loggedInUser.getUser_id());
        // Take care of existing expired session
        if(existingSession != null && existingSession.getExpiresAt() < System.currentTimeMillis()){
            loginRepository.delete(existingSession);
            existingSession = null;
        }
        //Return existing session if its not expired
        if(existingSession == null){
            LoginSession session = new LoginSession();
            session.setExpiresAt(System.currentTimeMillis() + ONE_DAY_IN_MILLIS);
            session.setSessionId(getSessionString());
            session.setUserId(loggedInUser.getUser_id());
            return loginRepository.save(session);
        }else{
            return existingSession;
        }
    }
}
