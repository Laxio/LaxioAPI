package org.laxio.chat;


/**
 * Refer to <a href="http://wiki.vg/Chat#Processing_chat">this</a> for references regarding visibility
 */
public enum ChatPosition {

    CHAT(0),
    SYSTEM(1),

    @Deprecated
    INFO(2);

    private byte position;

    ChatPosition(int position) {
        this.position = (byte) position;
    }

    public byte getPosition() {
        return position;
    }

}
