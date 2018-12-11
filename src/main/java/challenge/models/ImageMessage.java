package challenge.models;

import javax.persistence.Entity;
import java.net.URL;

@Entity
public class ImageMessage extends ChatMessage {
    URL image_url;
    String type;
    int height;
    int width;
    String resolution;
}
