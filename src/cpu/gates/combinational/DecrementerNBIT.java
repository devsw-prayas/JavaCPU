package cpu.gates.combinational;

import cpu.gates.CombinationalGate;
import cpu.gates.Constants;
import cpu.gates.SignalBus;

public final class DecrementerNBIT extends CombinationalGate {
    private final SubtractorNBIT subtractorNBIT;
    private final SignalBus num;

    public DecrementerNBIT(SignalBus num){
        this.num = num;
        subtractorNBIT = new SubtractorNBIT(SubtractorNBIT.SubtractionMode.TWOS_COMPLEMENT,
                num, SignalBus.fromInt(1, num.size()));
        register(subtractorNBIT);
    }

    public SignalBus sum(){
        return subtractorNBIT.sum();
    }
}
