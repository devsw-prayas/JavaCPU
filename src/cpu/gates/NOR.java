package cpu.gates;

public final class NOR implements Gate{
    private final Gate not, and;
    private final Signal A, B;

    public NOR(boolean A, boolean B){
        this(Signal.makeSignal(A),  Signal.makeSignal(B));
    }

    public NOR(Signal A, Signal B){
        this.A = A;
        this.B = B;
        and = new OR(this.A, this.B);
        not = new NOT(and.output());
    }

    @Override
    public Signal output() {
        return not.output();
    }

    public Gate evaluate(){
        and.evaluate();
        not.evaluate();
        return this;
    }
}
