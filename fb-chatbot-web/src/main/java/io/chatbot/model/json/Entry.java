package io.chatbot.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public final class Entry {

    private final Long id;
    private final Long time;
    private final List<Messaging> messaging;

    @JsonCreator
    public Entry(@JsonProperty("id") final Long id, @JsonProperty("time") final Long time,
                 @JsonProperty("messaging") final List<Messaging> messaging) {
        this.id = id;
        this.time = time;
        this.messaging = messaging;
    }

    public Long getId() {
        return id;
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
