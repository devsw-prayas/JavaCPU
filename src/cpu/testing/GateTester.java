package cpu.testing;

import cpu.gates.Gate;
import cpu.gates.Signal;

public final class GateTester {
    private final Gate invokableGate;
    private final Operation operation;
    private final Signal[] signals;
    public GateTester(Gate invokableGate, Operation operation, Signal... signals) {
        this.invokableGate = invokableGate;
        this.operation = operation;
        this.signals = signals;
    }

    public void runTruthTable() {
        int total = 1 << signals.length;
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < signals.length; j++) {
                boolean value = ((i >> (signals.length- j - 1)) & 1) == 1;
                signals[j].set(value);
            }

            invokableGate.evaluate();
            operation.perform();
        }
    }
}
