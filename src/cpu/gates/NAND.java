package cpu.gates;

public final class NAND implements Gate{
    private final Gate and, not;
    private final Signal A, B;

    public NAND(boolean A, boolean B){
        this(Signal.makeSignal(A), Signal.makeSignal(B));
    }

    public NAND(Signal A, Signal B){
        this.A = A;
        this.B = B;
        this.and = new AND(this.A, this.B);
        this.not = new NOT(this.and.output());
    }

    @Override
    public Gate evaluate() {
        and.evaluate();
        not.evaluate();
        return this;
    }

    @Override
    public Signal output() {
        return not.output();
    }
}
