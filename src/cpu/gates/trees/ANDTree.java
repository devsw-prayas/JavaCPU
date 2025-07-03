package cpu.gates.trees;

import cpu.gates.AND;
import cpu.gates.CombinationalGate;
import cpu.gates.Gate;
import cpu.gates.Signal;

public class ANDTree extends CombinationalGate{
    private final Signal[] signals;
    private final Gate[] gates;

    public ANDTree(Signal...signals){
        this.signals = signals;
        this.gates = new AND[signals.length-1];
        for (int i = 0; i < signals.length - 1; i++) {
            if (i == 0)
                gates[0] = new AND(signals[0], signals[1]);
            else
                gates[i] = new AND(gates[i - 1].output(), signals[i + 1]);
        }
    }

    @Override
    public Signal output(){
        return gates[gates.length-1].output();
    }
}
