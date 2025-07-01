package cpu.gates;
import java.lang.Math;

import cpu.BusOverflowException;

public final class SignalBus implements Cloneable {
    private final Signal[] signals;
    public SignalBus(boolean...signals){
        this.signals = new Signal[signals.length];
        for(int i = 0; i < signals.length; i++)this.signals[signals.length - i -1] = Signal.makeSignal(signals[i]);

    }
    public SignalBus(Signal... signals){
        this.signals = signals;
    }

    public SignalBus(int length) {
        this.signals = new Signal[length];
        for (int i = 0; i < length; i++) this.signals[i] = Signal.makeSignal(false);
    }

    public int size(){
        return this.signals.length;
    }

    public void flipAt(int bitIdx){
        signals[bitIdx].flip();
    }

    public void setAt(boolean signal, int bitIdx){
        signals[bitIdx].set(signal);
    }

    public Signal get(int bitIdx){
        return signals[bitIdx];
    }

    public Signal[] toArray(){
        return signals;
    }

    public Signal MSB(){
        return signals[signals.length-1];
    }

    public Signal LSB(){
        return signals[0];
    }

    public void setSignals(boolean ...signals){
        for(int i = 0; i < signals.length; i++) this.signals[i].set(signals[i]);
    }

    @Override
    public String toString() {
        String str = "";
        for(Signal signal : signals){
            str = STR."\{Signal.toInt(signal)}".concat(str);
        }
        return STR."[\{str}]";
    }

    public int toInt(){
        int val = 0;
        for(int i = 0; i < signals.length; i++) if(signals[i].signal()) val |= (1 << i);
        return val;
    }

    public SignalBus xor(SignalBus other){
        if(this.size() != other.size()) throw new BusOverflowException("Invalid bus sizes");
        Signal[] result = new Signal[this.size()];
        for(int i = 0; i < this.size(); i++)
            result[i] = Signal.makeSignal(this.get(i).signal() ^ other.get(i).signal());
        return new SignalBus(result);
    }

    public SignalBus or(SignalBus other){
        if(this.size() != other.size()) throw new BusOverflowException("Invalid bus sizes");
        Signal[] result = new Signal[this.size()];
        for(int i = 0; i < this.size(); i++)
            result[i] = Signal.makeSignal(this.get(i).signal() | other.get(i).signal());
        return new SignalBus(result);
    }

    public SignalBus and(SignalBus other){
        if(this.size() != other.size()) throw new BusOverflowException("Invalid bus sizes");
        Signal[] result = new Signal[this.size()];
        for (int i = 0; i < this.size(); i++)
            result[i] = Signal.makeSignal(this.get(i).signal() & other.get(i).signal());
        return new SignalBus(result);
    }

    public SignalBus not(){
        Signal[] result = new Signal[this.size()];
        for(int i = 0; i < this.size(); i++) result[i] = Signal.makeSignal(!this.signals[i].signal());
        return new SignalBus(result);
    }

    public int toSigned() {
        int raw = this.toInt();
        return this.MSB().signal() ? raw - (1 << this.size()) : raw;
    }

    @Override
    public SignalBus clone() throws CloneNotSupportedException {
        SignalBus signalBus = (SignalBus) super.clone();
        signalBus = new SignalBus(signals);
        for (int i = 0; i < signals.length; i++) {
            signalBus.setAt(signals[i].signal(), i);
        }
        return signalBus;
    }
}
