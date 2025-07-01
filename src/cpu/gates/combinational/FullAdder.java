package cpu.gates.combinational;

import cpu.gates.*;

public final class FullAdder extends CombinationalGate {
    private final Gate sumXor1, sumXor2;
    private final Gate carryAnd1, carryAnd2, carryAnd3;
    private final Gate carryOr1, carryOr2;
    private final Signal A, B, C;

    public FullAdder(boolean A, boolean B, boolean C){
        this(Signal.makeSignal(A), Signal.makeSignal(B), Signal.makeSignal(C));
    }

    public FullAdder(Signal A, Signal B, Signal C){
        this.A = A;
        this.B = B;
        this.C = C;

        sumXor1 = new XOR(A, B);
        sumXor2 = new XOR(sumXor1.output(), C);
        carryAnd1 = new AND(A, B);
        carryAnd2 = new AND(B, C);
        carryAnd3 = new AND(A, C);
        carryOr1 = new OR(carryAnd1.output(), carryAnd2.output());
        carryOr2 = new OR(carryOr1.output(), carryAnd3.output());

        register(sumXor1);
        register(sumXor2);
        register(carryAnd1);
        register(carryAnd2);
        register(carryAnd3);
        register(carryOr1);
        register(carryOr2);
    }

    public Signal sum(){
        return sumXor2.output();
    }

    public Signal carry(){
        return carryOr2.output();
    }

    public Signal output(){
        return sum();
    }
}
