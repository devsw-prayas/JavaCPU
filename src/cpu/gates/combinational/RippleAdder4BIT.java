package cpu.gates.combinational;

import cpu.gates.CombinationalGate;
import cpu.gates.Gate;
import cpu.gates.Signal;
import cpu.gates.SignalBus;

public final class RippleAdder4BIT extends CombinationalGate{
    private final Gate gates[];
    private final SignalBus numA, numB;
    private final Signal cin;
    private final SignalBus output;

    public RippleAdder4BIT(SignalBus numA, SignalBus numB, Signal cin){
        this.numA = numA;
        this.numB = numB;
        this.cin = cin;
        gates = new Gate[4];
        for (int i = 0; i < 4; i++)
            if(i == 0) gates[i] = new FullAdder(this.numA.get(i), this.numB.get(i), cin);
            else gates[i] = new FullAdder(this.numA.get(i), this.numB.get(i), ((FullAdder)gates[i-1]).carry());
        for (int i = 0; i < 4; i++) register(gates[i]);
        this.output = new SignalBus(false, false, false, false);
    }

    @Override
    public Gate evaluate() {
        super.evaluate();
        for(int i = 0; i < 4; i++) output.setAt(gates[i].output().signal(), i);
        return this;
    }

    public SignalBus sum(){
        return output;
    }

    public Signal carry(){
        return ((FullAdder) gates[3]).carry();
    }
}