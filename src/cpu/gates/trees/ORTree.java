package cpu.gates.trees;

import cpu.gates.CombinationalGate;
import cpu.gates.Gate;
import cpu.gates.OR;
import cpu.gates.Signal;

public class ORTree extends CombinationalGate {
    private final Signal[] signals;
    private final Gate[] gates;

    public ORTree(Signal[] signals) {
        this.signals = signals;
        this.gates = new OR[signals.length - 1];
        for (int i = 0; i < signals.length - 1; i++) {
            if (i == 0)
                gates[0] = new OR(signals[0], signals[1]);
            else
                gates[i] = new OR(gates[i - 1].output(), signals[i + 1]);
        }

        for (Gate gate : gates) register(gate);
    }

    @Override
    public Signal output() {
        return gates[gates.length-1].output();
    }
}
