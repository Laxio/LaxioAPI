package org.laxio.chat;

import org.laxio.chat.event.click.ClickEvent;
import org.laxio.chat.event.hover.HoverEvent;
import org.laxio.exception.chat.ChatFormatException;

public interface MessageComponent extends ChatComponent {

    MessageComponent[] getExtra();

    void setExtra(MessageComponent[] extra);

    default <T extends MessageComponent> T addExtra(T extra) {
        if (getExtra() == null) {
            setExtra(new MessageComponent[]{extra});
            return extra;
        }

        MessageComponent[] clone = new MessageComponent[getExtra().length + 1];
        clone[clone.length - 1] = extra;

        for (int i = 0; i < clone.length - 1; i++) {
            clone[i] = getExtra()[i];
        }

        setExtra(clone);
        return extra;
    }

    /**
     * Gets the color of this component
     *
     * @return The color of this component
     */
    ChatColor getColor();

    /**
     * Sets the color of this component
     *
     * @param color The color to set
     *
     * @throws ChatFormatException If the supplied color is a formatter
     */
    void setColor(ChatColor color);

    /**
     * Gets the formatting elements set on this component
     *
     * @return The formatting elements for this component
     */
    ChatColor[] getFormat();

    void setFormat(ChatColor[] format);

    default void addFormat(ChatColor format) {
        if (!format.isFormat()) {
            throw new ChatFormatException(format.name() + " is not a valid formatter", this);
        }

        if (getFormat() == null) {
            setFormat(new ChatColor[]{format});
            return;
        }

        ChatColor[] clone = new ChatColor[getFormat().length + 1];
        clone[0] = format;

        for (int i = 1; i < clone.length; i++) {
            clone[i] = getFormat()[i - 1];
        }

        setFormat(clone);
    }

    default boolean hasFormat(ChatColor format) {
        if (!format.isFormat()) {
            throw new ChatFormatException(format.name() + " is not a valid formatter", this);
        }

        if (getFormat() == null) {
            return false;
        }

        for (ChatColor color : getFormat()) {
            if (color.equals(format)) {
                return true;
            }
        }

        return false;
    }

    ClickEvent getClickEvent();

    void setClickEvent(ClickEvent event);

    HoverEvent getHoverEvent();

    void setHoverEvent(HoverEvent event);

    default <T extends MessageComponent> T add(T component) {
        if (getColor() != null)
            component.setColor(getColor());

        if (getFormat() != null)
            component.setFormat(getFormat());

        if (getClickEvent() != null)
            component.setClickEvent(getClickEvent());

        if (getHoverEvent() != null)
            component.setHoverEvent(getHoverEvent());

        addExtra(component);
        return component;
    }

    String toText();

}
