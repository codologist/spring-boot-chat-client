package challenge.models;

import javax.persistence.Entity;

@Entity
public class TextMessage extends ChatMessage{
    String text;
    int length;
    String encoding;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
