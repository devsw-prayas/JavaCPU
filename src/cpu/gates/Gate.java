package cpu.gates;

/**
 * All gates derive from this class and to evaluate a gate, simply call output on the gate
 */
public interface Gate {
    Signal output(); //Returns the final cached output
    Gate evaluate(); //Evaluates this
}
