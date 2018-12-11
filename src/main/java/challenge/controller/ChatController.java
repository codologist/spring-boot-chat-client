package challenge.controller;

import challenge.models.*;
import challenge.repositories.*;
import org.hibernate.criterion.LikeExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@RestController
public class ChatController {

    Logger logger = Logger.getLogger("ChatController");
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ImageMessageRepository imageMessageRepository;
    @Autowired
    private VoiceMessageRepository voiceMessageRepository;
    @Autowired
    private TextMessageRepository textMessageRepository;
    @Autowired
    private LoginRepository loginRepository;

    private void validateSession(int userId, String sessionID){
        logger.info("now validating session.....");
        ExampleMatcher USER_ID_MATCHER = ExampleMatcher.matching().withNullHandler(ExampleMatcher.NullHandler.IGNORE);
        LoginSession exampleSession = new LoginSession();
        exampleSession.setSessionId(sessionID);
        exampleSession.setUserId(userId);
        exampleSession.setExpiresAt(null);
        Example<LoginSession> example = Example.of(exampleSession, USER_ID_MATCHER);
        LoginSession sess = loginRepository.findOne(example).orElse(null);
        if(sess == null){
            throw new IllegalStateException("No login session found for user. Please relogin and try.");
        }
        if(sess.getExpiresAt() < System.currentTimeMillis()){
            throw new IllegalStateException("User session expired. Please relogin and try ");
        }
        if(!sess.getSessionId().equals(sessionID)){
            throw new IllegalStateException("Wrong user session specified. Please recollect and try ");
        }
    }

    @PostMapping("/sendChat/{userId}/text")
    public String postChatMessage(@RequestHeader("Authorization") String sessionID,  @RequestBody TextMessage message, @PathVariable("userId") String userId){
        logger.info("Session id:" + sessionID);
        logger.info("userId: " + userId);
        validateSession(Integer.valueOf(userId), sessionID);
        message.setUser_id(Integer.valueOf(userId));
        chatRepository.save(message);
        return "{\"status\" : \"Success\"}";
    }

    @PostMapping("/sendChat/{userId}/image")
    public String postChatMessage(@RequestHeader("Authorization") String sessionID,  @RequestBody ImageMessage message, @PathVariable("userId") String userId){
        logger.info("Session id:" + sessionID);
        logger.info("userId: " + userId);
        validateSession(Integer.valueOf(userId), sessionID);
        message.setUser_id(Integer.valueOf(userId));
        chatRepository.save(message);
        return "{\"status\" : \"Success\"}";
    }

    @PostMapping("/sendChat/{userId}/voice")
    public String postChatMessage(@RequestHeader("Authorization") String sessionID,  @RequestBody VoiceMessaage message, @PathVariable("userId") String userId){
        logger.info("Session id:" + sessionID);
        logger.info("userId: " + userId);
        validateSession(Integer.valueOf(userId), sessionID);
        message.setUser_id(Integer.valueOf(userId));
        chatRepository.save(message);
        return "{\"status\" : \"Success\"}";
    }

    @GetMapping("/getChats/{userId}")
    public List<ChatMessage> getChats(@RequestHeader(value = "Authorization") String sessionID,  @PathVariable("userId") String userId){
        validateSession(Integer.valueOf(userId), sessionID);
        ExampleMatcher USER_ID_MATCHER = ExampleMatcher.matchingAny()
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE);
        ChatMessage chat = new ChatMessage();
        chat.setUser_id(Integer.valueOf(userId));
        Example<ChatMessage> example = Example.of(chat, USER_ID_MATCHER);
        List<ChatMessage> messages = chatRepository.findAll(example);
        logger.info("Finding for user id:" + userId);
        logger.info("No of messages: " + messages.size());
        return messages;
    }
}
