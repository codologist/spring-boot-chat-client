package challenge.models;

import javax.persistence.Entity;
import java.net.URL;

@Entity
public class VoiceMessaage extends ChatMessage{
    URL messageUrl;
    int hours;
    int minutes;
    int seconds;
}
