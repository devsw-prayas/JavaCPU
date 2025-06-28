package cpu.gates;

public class NOT implements Gate {
    private final Signal A;
    private  final Signal state;
    public NOT(boolean A) {
        this(Signal.makeSignal(A));
    }

    public NOT(Signal A){
        this.A = A;
        this.state = Signal.makeSignal(false);
    }

    @Override
    public Gate evaluate(){
        state.set(!A.signal());
        return this;
    }

    @Override
    public Signal output(){
        return state;
    }
}
