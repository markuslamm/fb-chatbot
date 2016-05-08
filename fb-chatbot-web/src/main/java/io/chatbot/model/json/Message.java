package io.chatbot.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Message {

    private final String mid;
    private final Integer seq;
    private final String text;

    private final List<Attachment> attachments;

    @JsonCreator
    public Message(@JsonProperty("mid") String mid,
                   @JsonProperty("seq") final Integer seq,
                   @JsonProperty("text") final String text,
                   @JsonProperty("attachments") final List<Attachment> attachments) {
        this.mid = mid;
        this.seq = seq;
        this.text = text;
        this.attachments = Optional.ofNullable(attachments).orElse(new ArrayList<>());
    }

    public String getMid() {
        return mid;
    }

    public Integer getSeq() {
        return seq;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
