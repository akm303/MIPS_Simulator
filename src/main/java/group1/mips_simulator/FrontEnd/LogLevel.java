package group1.mips_simulator.FrontEnd;

/**
 * The level of debut to show.
 * A log level will show messages at its level, and all
 * levels below it. For example, DEBUG will show (ERROR, MESSAGE and DEBUG) messages.
 * The lowest level is ERROR which will only show itself
 */
public enum LogLevel {
    DEBUG,   // DEBUG & MESSAGE & ERROR
    MESSAGE, // MESSAGE & ERROR
    ERROR,   // ERROR
}
