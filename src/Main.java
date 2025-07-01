import cpu.gates.*;
import cpu.gates.combinational.FullAdder;
import cpu.gates.combinational.RippleAdder4BIT;
import cpu.gates.combinational.RippleAdderNBIT;
import cpu.gates.combinational.SubtractorNBIT;
import cpu.testing.GateTester;

import java.util.Arrays;

void main(){
    int[][] tests = {
            {3, 2}, {2, 3}, {7, -1}, {-8, 1},
            {-4, -4}, {-3, 5}, {4, 4}, {-7, -1}
    };

    for (int[] test : tests) {
        int a = test[0], b = test[1];
        SignalBus A = of(a), B = of(b);
        SubtractorNBIT sub = new SubtractorNBIT(SubtractorNBIT.SubtractionMode.TWOS_COMPLEMENT, A, B);
        sub.evaluate();
        SignalBus result = sub.sum();

        System.out.printf("A: %2d [%s]  B: %2d [%s]  → A-B = [%s] = %2d | Overflow: %s | Sign: %s ",
                a, A, b, B,
                result, result.toSigned(),
                sub.overflow(), result.MSB().signal());
        System.out.println();
    }
}

SignalBus of(int value) {
    // Assumes value is in [-8, 7] and returns LSB-first SignalBus
    boolean[] bits = new boolean[4];
    int masked = value & 0xF; // 4-bit wrap
    for (int i = 0; i < 4; ++i) bits[i] = ((masked >> 3 - i) & 1) == 1;
    System.out.println(Arrays.toString(bits));
    return new SignalBus(bits);
}
