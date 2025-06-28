package cpu.gates;

public final class AND implements Gate {
    private final Signal A, B;
    private final Signal state;

    public AND(boolean A, boolean B) {
        this(Signal.makeSignal(A), Signal.makeSignal(B));
    }

    public AND(Signal A, Signal B){
        this.A = A;
        this.B = B;
        this.state = Signal.makeSignal(false);
    }

    @Override
    public Gate evaluate() {
        state.set(A.signal() && B.signal());
        return this;
    }

    @Override
    public Signal output() {
        return state;
    }
}
