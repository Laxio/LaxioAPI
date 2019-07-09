package org.laxio.event;

/**
 *
 * Used by Listeners to determine what order of the hierarchy the handler is to listen on
 *
 */
public enum Priority {

    /**
     * Run 1st
     */
    APPLICATION,

    /**
     * Run 2nd
     */
    LOWEST,

    /**
     * Run 3rd
     */
    LOW,

    /**
     * Run 4th
     */
    NORMAL,

    /**
     * Run 5th
     */
    HIGH,

    /**
     * Run 6th
     */
    HIGHEST,

    /**
     * Run 7th.
     * Only use this for monitoring end-data of Packets, make no changes here
     */
    MONITOR

}
