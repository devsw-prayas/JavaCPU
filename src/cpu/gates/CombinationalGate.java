package cpu.gates;

import java.util.ArrayList;
import java.util.List;

public abstract class CombinationalGate implements Gate {
    private final List<Gate> gates = new ArrayList<>();
    protected Signal output;

    protected void register(Gate gate){
        gates.add(gate);
    }

    public Gate evaluate(){
        for(Gate gate : gates) gate.evaluate();
        return this;
    }

    public Signal output(){
        return output;
    }
}
