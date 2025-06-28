package cpu.gates;

public final class XNOR implements Gate{
    private final Gate xor, not;
    private final Signal A, B;

    public XNOR(Signal A, Signal B){
        this.A = A;
        this.B = B;
        xor = new XOR(this.A, this.B);
        not = new NOT(xor.output());
    }

    public XNOR(boolean A, boolean B){
        this(Signal.makeSignal(A), Signal.makeSignal(B));
    }

    @Override
    public Gate evaluate() {
        xor.evaluate();
        not.evaluate();
        return this;
    }

    @Override
    public Signal output() {
        return not.output();
    }
}
