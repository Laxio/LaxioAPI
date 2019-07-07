package org.laxio.chat.component;

import org.json.JSONArray;
import org.json.JSONObject;
import org.laxio.chat.ChatColor;
import org.laxio.chat.MessageComponent;
import org.laxio.chat.event.click.ClickEvent;
import org.laxio.chat.event.hover.HoverEvent;
import org.laxio.exception.chat.ChatFormatException;

/**
 * Represents a styled text component for the chat system
 */
public class FormattedComponent implements MessageComponent {

    private static final String EXTRA_TAG = "extra";
    private static final String COLOR_TAG = "color";

    private MessageComponent[] extra;
    private ChatColor color;
    private ChatColor[] format;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public FormattedComponent() {
        this.extra = null;
        this.color = null;
        this.format = null;
        this.clickEvent = null;
        this.hoverEvent = null;
    }

    public FormattedComponent(MessageComponent[] extra, ChatColor color, ChatColor[] format, ClickEvent clickEvent, HoverEvent hoverEvent) {
        this.extra = extra;
        this.color = color;
        this.format = format;
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
    }

    @Override
    public MessageComponent[] getExtra() {
        return extra;
    }

    @Override
    public void setExtra(MessageComponent[] extra) {
        this.extra = extra;
    }

    @Override
    public ChatColor getColor() {
        return color;
    }

    @Override
    public void setColor(ChatColor color) {
        if (color.isFormat()) {
            throw new ChatFormatException(color.name() + " is a formatter not a color", this);
        }

        this.color = color;
    }

    @Override
    public ChatColor[] getFormat() {
        return format;
    }

    @Override
    public void setFormat(ChatColor[] format) {
        for (ChatColor val : format) {
            if (!val.isFormat()) {
                throw new ChatFormatException(val.name() + " is not a valid formatter", this);
            }
        }

        this.format = format;
    }

    @Override
    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    @Override
    public void setClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    @Override
    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public void setHoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        if (extra != null && extra.length > 0) {
            JSONArray array = new JSONArray();
            for (MessageComponent component : extra) {
                array.put(component.toJSON());
            }

            json.put(EXTRA_TAG, array);
        }

        if (clickEvent != null) {
            json.put("click_event", clickEvent.toJSON());
        }

        if (hoverEvent != null) {
            json.put("hover_event", hoverEvent.toJSON());
        }

        for (ChatColor chatFormat : ChatColor.format()) {
            json.put(chatFormat.getName(), hasFormat(chatFormat));
        }

        if (color != null) {
            json.put(COLOR_TAG, color.getName());
        }

        return json;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toText() {
        return parse(toJSON());
    }

    private String parse(JSONObject json) {
        StringBuilder message = new StringBuilder();

        format(json, message);
        translate(json, message);
        if (json.has("text")) {
            message.append(json.get("text"));
        }

        extra(json, message);
        return message.toString();
    }

    private void format(JSONObject json, StringBuilder message) {
        for (ChatColor chatFormat : ChatColor.format()) {
            if (json.has(chatFormat.getName()) && json.getBoolean(chatFormat.getName())) {
                message.append(chatFormat);
            }
        }

        if (json.has(COLOR_TAG)) {
            message.append(ChatColor.valueOf(json.getString(COLOR_TAG).toUpperCase()));
        }
    }

    private void translate(JSONObject json, StringBuilder message) {
        if (json.has("translate")) {
            String text = json.getString("translate");
            if (json.has("with")) {
                JSONArray array = json.getJSONArray("with");
                String[] translationValues = new String[array.length()];
                for (int i = 0; i < translationValues.length; i++) {
                    Object object = array.get(i);

                    String value;
                    if (object instanceof JSONObject) {
                        value = parse((JSONObject) object);
                    } else {
                        value = (String) object;
                    }

                    translationValues[i] = value;
                }

                text = String.format(text, translationValues);
            }
            message.append(text);
        }
    }

    private void extra(JSONObject json, StringBuilder message) {
        if (json.has(EXTRA_TAG)) {
            JSONArray extraJson = json.getJSONArray(EXTRA_TAG);
            for (int i = 0; i < extraJson.length(); i++) {
                Object object = extraJson.get(i);
                if (object instanceof JSONObject) {
                    message.append(parse((JSONObject) object));
                } else {
                    message.append(object);
                }
            }
        }
    }

}
