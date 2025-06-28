package cpu.gates.combinational;

import cpu.gates.*;

public final class HalfAdder extends CombinationalGate {
    private final Signal A, B;
    private final Gate and, xor;
    public HalfAdder(boolean A, boolean B){
        this(Signal.makeSignal(A), Signal.makeSignal(B));
    }

    public HalfAdder(Signal A,Signal B){
        this.A = A;
        this.B = B;
        and = new AND(A, B);
        xor = new XOR(A, B);
        register(and);
        register(xor);
    }

    /**
     * @return Returns the sum of the adder
     */
    public Signal sum(){
        return xor.output();
    }

    /**
     * @return Returns the carry of the adder
     */
    public Signal carry(){
        return and.output();
    }

    /**
     * @return Returns the sum of the adder
     */
    @Override
    public Signal output(){
        return sum();
    }
}
