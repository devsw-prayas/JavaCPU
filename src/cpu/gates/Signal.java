package cpu.gates;

/**
 * Defines a togglable signal that can be passed to gates and flipped as well.
 * Keeping in mind boolean algebra
 * {@code true} => {@code 1}
 * {@code false} => {@code 0}
 */
public final class Signal {
    private boolean state;
    public static final Signal HIGH = new Signal(true);
    public static final Signal LOW = new Signal(false);

    private Signal(boolean signal){
        this.state = signal;
    }

    public boolean signal() {
        return state;
    }

    public void set(boolean signal){
        state = signal;
    }

    public void flip(){
        state = !state;
    }

    public static Signal makeSignal(boolean signal){
        return new Signal(signal);
    }

    public static int toInt(Signal signal){
        return signal.signal() ? 1 : 0;
    }

    public boolean equals(Signal signal){
        return signal.signal() == this.signal();
    }

    @Override
    public String toString(){
        return "Signal: "+Signal.toInt(this);
    }
}
