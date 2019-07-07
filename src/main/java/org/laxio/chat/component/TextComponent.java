package org.laxio.chat.component;

import org.json.JSONObject;
import org.laxio.chat.ChatColor;
import org.laxio.chat.MessageComponent;
import org.laxio.chat.event.click.ClickEvent;
import org.laxio.chat.event.hover.HoverEvent;

public class TextComponent extends FormattedComponent {

    private String text;

    public TextComponent() {
        // empty
    }

    public TextComponent(MessageComponent[] extra, ChatColor color, ChatColor[] format, ClickEvent clickEvent, HoverEvent hoverEvent, String text) {
        super(extra, color, format, clickEvent, hoverEvent);
        this.text = text;
    }

    /**
     * Gets the clean text element of this component
     *
     * @return The text of this component
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text element of this component, strips the text of all colors
     *
     * @param text The text to set
     */
    public void setText(String text) {
        ChatColor.stripColor(text);
        this.text = text;
    }

    /**
     * Checks if this text component is purely text, no formatting/color, etc
     *
     * @return true if the component is pure, false otherwise
     */
    public boolean isPureText() {
        return getExtra().length <= 0 && getColor() == null && getFormat().length <= 0;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("text", text);
        return json;
    }

}
