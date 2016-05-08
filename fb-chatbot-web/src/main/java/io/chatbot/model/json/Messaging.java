package io.chatbot.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class Messaging {

    private final MessagingActor sender;
    private final MessagingActor recipient;
    private final Long timestamp;
    private final Message message;

    public Messaging(@JsonProperty("sender") final MessagingActor sender,
                     @JsonProperty("recipient") final MessagingActor recipient,
                     @JsonProperty("timestamp") final Long timestamp,
                     @JsonProperty("message") final Message message) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.message = message;
    }

    public MessagingActor getSender() {
        return sender;
    }

    public MessagingActor getRecipient() {
        return recipient;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
