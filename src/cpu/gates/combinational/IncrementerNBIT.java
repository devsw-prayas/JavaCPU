package cpu.gates.combinational;

import cpu.gates.CombinationalGate;
import cpu.gates.Signal;
import cpu.gates.SignalBus;

public final class IncrementerNBIT extends CombinationalGate {
    private final RippleAdderNBIT rippleAdderNBIT;
    private final SignalBus num;
    public IncrementerNBIT(SignalBus num) {
        this.num = num;
        rippleAdderNBIT = new RippleAdderNBIT(num, new SignalBus(num.size()), Signal.makeSignal(true));
        register(rippleAdderNBIT);
    }

    public SignalBus sum(){
        return rippleAdderNBIT.sum();
    }

    public Signal carry(){
        return rippleAdderNBIT.carry();
    }
}
