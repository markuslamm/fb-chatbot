package io.chatbot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Maps incoming message data received bei the registered facebook
 * webhook in {@code FacebookMessengerController}.
 * <p>
 * {
 * "object": "page",
 * "entry": [
 * {
 * "id": "250052192015902",
 * "time": 1458692752478,
 * "messaging": [
 * {
 * "sender": {
 * "id": "996148190503410"
 * },
 * "recipient": {
 * "id": "250052192015902"
 * },
 * "timestamp": 1460245672080,
 * "message": {
 * "mid": "mid.1460245671959:dad2ec9421b03d6f78",
 * "seq": 216,
 * "text": "hello"
 * }
 * }
 * ]
 * }
 * ]
 * }
 */
public final class IncomingMessageData {

    private final String object;
    private final List<Entry> entry;

    @JsonCreator
    public IncomingMessageData(@JsonProperty("object") final String object,
                               @JsonProperty("entry") final List<Entry> entry) {
        this.object = object;
        this.entry = entry;
    }

    public String getObject() {
        return object;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public static class Entry {
        private final Long pageId;
        private final Long time;
        private final List<Messaging> messaging;

        @JsonCreator
        public Entry(@JsonProperty("id") final Long pageId, @JsonProperty("time") final Long time,
                     @JsonProperty("messaging") final List<Messaging> messaging) {
            this.pageId = pageId;
            this.time = time;
            this.messaging = messaging;
        }

        public Long getPageId() {
            return pageId;
        }

        public Long getTime() {
            return time;
        }

        public List<Messaging> getMessaging() {
            return messaging;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    public static class MessagingActor {
        private final Long id;

        @JsonCreator
        public MessagingActor(@JsonProperty("id") final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }


    public static class Messaging {
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

    public static class Message {
        private final String mid;
        private final Integer seq;
        private final String text;

        @JsonCreator
        public Message(@JsonProperty("mid") String mid,
                       @JsonProperty("seq") final Integer seq,
                       @JsonProperty("text") final String text) {
            this.mid = mid;
            this.seq = seq;
            this.text = text;
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

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
