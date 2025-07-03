package cpu.gates.combinational;

import cpu.gates.CombinationalGate;
import cpu.gates.Gate;
import cpu.gates.Signal;
import cpu.gates.SignalBus;


public class ComparatorNBIT extends CombinationalGate{
    private final SubtractorNBIT subtractorNBIT;
    private final SignalBus numA, numB;
    private final SignalBus ZERO;
    private final Signal equal, less, greater;

    public ComparatorNBIT(SignalBus numA, SignalBus numB){
        this.numA = numA;
        this.numB = numB;
        subtractorNBIT = new SubtractorNBIT(SubtractorNBIT.SubtractionMode.TWOS_COMPLEMENT, numA, numB);
        register(subtractorNBIT);
        ZERO = SignalBus.fromInt(0, numA.size());
        equal = Signal.makeSignal(false);
        less = Signal.makeSignal(false);
        greater = Signal.makeSignal(false);
    }


    @Override
    public Gate evaluate() {
        super.evaluate();
        equal.set(subtractorNBIT.sum().equals(ZERO));
        less.set(subtractorNBIT.overflow() ^ subtractorNBIT.sum().MSB().signal());
        greater.set(!less.signal() && !equal.signal());
        return this;
    }

    public Signal isEqual(){
        return equal;
    }

    public Signal isLessThan(){
        return less;
    }

    public Signal isGreaterThan(){
        return greater;
    }
}
