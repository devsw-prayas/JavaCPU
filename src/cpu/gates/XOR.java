package cpu.gates;

public final class XOR implements Gate {
    private final Gate and1, and2, not1, not2, or;
    private final Signal A, B;

    public XOR(Signal A, Signal B) {
        this.A = A;
        this.B = B;
        this.not1 = new NOT(this.A);
        this.not2 = new NOT(this.B);
        this.and1 = new AND(this.A, not2.output());
        this.and2 = new AND(this.B, not1.output());
        this.or = new OR(this.and1.output(), this.and2.output());
    }

    public XOR(boolean A, boolean B){
        this(Signal.makeSignal(A), Signal.makeSignal(B));
    }

    @Override
    public Gate evaluate() {
        not1.evaluate();
        not2.evaluate();
        and1.evaluate();
        and2.evaluate();
        or.evaluate();
        return this;
    }

    @Override
    public Signal output() {
        return or.output();
    }
}
