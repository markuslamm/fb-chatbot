package io.chatbot.util.json;

public class JsonException extends RuntimeException {

    private static final long serialVersionUID = 8158934958358565325L;

    public JsonException(final Throwable cause) {
        super(cause);
    }

    public JsonException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JsonException(final byte[] input, final Throwable cause) {
        super("Cannot parse: " + new String(input), cause);
    }
}
