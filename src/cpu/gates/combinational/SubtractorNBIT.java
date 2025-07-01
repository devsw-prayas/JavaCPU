package cpu.gates.combinational;

import cpu.gates.CombinationalGate;
import cpu.gates.Signal;
import cpu.gates.SignalBus;

public final class SubtractorNBIT extends CombinationalGate {
    public enum SubtractionMode{
        ONES_COMPLEMENT, TWOS_COMPLEMENT
    }

    private final SubtractionMode subtractionMode;
    private final RippleAdderNBIT rippleAdderNBIT;
    private final SignalBus numA, numB;
    public SubtractorNBIT(SubtractionMode subtractionMode, SignalBus numA, SignalBus numB){
        this.subtractionMode = subtractionMode;
        this.numA = numA;
        this.numB = numB;
        Signal carryIn = (subtractionMode == SubtractionMode.TWOS_COMPLEMENT) ?
                Signal.makeSignal(true) : Signal.makeSignal(false);
        rippleAdderNBIT = new  RippleAdderNBIT(numA, numB.not(), carryIn);

        register(rippleAdderNBIT);
    }

    public SignalBus sum(){
        return rippleAdderNBIT.sum();
    }

    public boolean overflow(){
        return ((rippleAdderNBIT.sum().MSB().signal() ^ numA.MSB().signal()) &&
                (numA.MSB().signal() ^ numB.MSB().signal()));
    }

    public Signal sign(){
        return sum().MSB();
    }
}
