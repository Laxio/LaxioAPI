package org.laxio.chat;

import org.json.JSONObject;
import org.laxio.chat.component.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {

    private static final String SPLITTER = ChatColor.COLOR_CHAR + "";

    private String message;
    private JSONObject json;

    private MessageBuilder() {
        // empty but private
    }

    public MessageBuilder message(String message) {
        this.json = null;
        this.message = message;
        return this;
    }

    public MessageBuilder json(JSONObject json) {
        this.message = null;
        this.json = json;
        return this;
    }

    /**
     * Builds the component based on if this builder is using a message or json start
     *
     * @return The completed component based on the supplied input
     */
    public MessageComponent build() {
        if (this.message != null) {
            return buildString();
        } else if (this.json != null) {
            return buildJSON();
        }

        return null;
    }

    /**
     * Builds the component assuming that the builder has a String input
     *
     * @return The completed component based on a string input
     */
    private MessageComponent buildString() {
        List<ColorString> strings = new ArrayList<>();
        TextComponent component = new TextComponent();
        TextComponent resp = message(component, strings);
        if (resp != null) {
            return resp;
        }

        TextComponent primary = component;

        int pos = 0;
        for (ColorString entry : strings) {
            if (pos == 1) {
                // first component is primary component
                primary = component;
            }

            pos++;
            ChatColor color = entry.getColor();
            String text = entry.getString();

            if (color.isFormat()) {
                component = format(color, text, component);
            } else {
                // new color, new component
                component = component.addExtra(new TextComponent());
                if (text == null) {
                    text = "";
                }

                component.setText(text);
                component.setColor(color);
            }
        }

        if (pos == 1) {
            // first component is primary component
            primary = component;
        }

        return primary;
    }

    /**
     * Checks the string and adds ColorString to the supplied list
     *
     * @param component The base component to work off
     * @param message   The list to add to
     *
     * @return A completed component if the message cannot be split, otherwise null
     */
    private TextComponent message(TextComponent component, List<ColorString> message) {
        if (!this.message.contains(SPLITTER)) {
            component.setText(this.message);
            return component;
        }

        String[] split = this.message.split(SPLITTER);
        // Splits the string by color codes

        for (String str : split) {
            if (str.length() < 1) {
                // Removes double code
                continue;
            }

            // Creates a color string for color:text
            char ch = str.charAt(0);
            ChatColor color = ChatColor.getByChar(ch);
            str = str.substring(1);

            message.add(new ColorString(color, str));
        }

        return null;
    }

    /**
     * Works on the supplied component based on the color/text provided
     *
     * @param color  The color to check
     * @param text   The text preceding the color
     * @param origin The component to work on
     */
    private TextComponent format(ChatColor color, String text, TextComponent origin) {
        TextComponent component = origin;
        if (component.hasFormat(color)) {
            if (text != null) {
                // format already included but has text
                component.setText(component.getText() + text);
            }

            return component;
        }

        if (text != null && text.length() > 0) {
            if (component.getText() != null && component.getText().length() > 0) {
                // add new component with existing formatting
                component = component.add(new TextComponent());
                component.setText(text);
            } else {
                // use parent component because it was empty
                component.setText(text);
            }
        }

        // add the format supplied by this tag to the component
        component.addFormat(color);
        return component;
    }

    private MessageComponent buildJSON() {
        // TODO: write JSON -> MessageComponent conversion
        return null;
    }

    /**
     * Creates a new Builder
     *
     * @return The new builder
     */
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    /**
     * Represents a string prefixed by a supplied color
     */
    private static class ColorString {

        private ChatColor color;
        private String string;

        private ColorString(ChatColor color, String string) {
            this.color = color;
            this.string = string;
        }

        private ChatColor getColor() {
            return color;
        }

        private String getString() {
            return string;
        }

        @Override
        public String toString() {
            return "{" + color.name() + ":'" + string + "'}";
        }

    }

}
