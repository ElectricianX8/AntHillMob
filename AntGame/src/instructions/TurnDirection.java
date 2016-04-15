package instructions;

/**
 * Enum of possible turn directions.
 * Each direction *turns* one cell,so in the hexagon cell, you can turn RIGHT
 * five times before returning to the start position.
 */
public enum TurnDirection {
    LEFT, RIGHT;
}
