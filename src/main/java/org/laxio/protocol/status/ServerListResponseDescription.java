package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.laxio.chat.MessageComponent;

public class ServerListResponseDescription {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JsonOrgModule());
    }

    @JsonProperty("text")
    private JsonNode text;

    public ServerListResponseDescription() {
        // required by jackson
    }

    public ServerListResponseDescription(String text) {
        this.text = new TextNode(text);
    }

    public ServerListResponseDescription(MessageComponent component) {
        this.text = MAPPER.valueToTree(component.toJSON());
    }

    public JsonNode getText() {
        return text;
    }

    public void setText(JsonNode text) {
        this.text = text;
    }

}
