package cpu.gates.combinational;

import cpu.BusOverflowException;
import cpu.gates.CombinationalGate;
import cpu.gates.Gate;
import cpu.gates.Signal;
import cpu.gates.SignalBus;

public final class RippleAdderNBIT extends CombinationalGate{
    private final SignalBus numA, numB;
    private final Signal cin;
    private final Gate[] gates;
    private final SignalBus output;
    public RippleAdderNBIT(SignalBus numA, SignalBus numB, Signal cin) throws BusOverflowException {
        if(numB.size() != numA.size()) throw new BusOverflowException("Incompatible Ripple Adder N-B");
        this.numA = numA;
        this.numB = numB;
        this.cin = cin;
        gates = new Gate[numA.size()];
        for(int i = 0; i < numB.size(); i++)
            if(i == 0) gates[i] = new FullAdder(this.numA.get(i), this.numB.get(i), cin);
            else gates[i] = new FullAdder(this.numA.get(i), this.numB.get(i), ((FullAdder) gates[i -1]).carry());
        for (Gate gate : gates) register(gate);
        output = new SignalBus(gates.length);
    }

    public Gate evaluate() {
        super.evaluate();
        for(int i = 0; i < gates.length; i++) output.setAt(gates[i].output().signal(), i);
        return this;
    }

    public SignalBus sum(){
        return output;
    }

    public Signal carry(){
        return ((FullAdder) gates[gates.length-1]).carry();
    }
}
