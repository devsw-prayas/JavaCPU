import cpu.gates.*;

void main(){
    Signal A = Signal.makeSignal(false);
    Signal B = Signal.makeSignal(false);

    System.out.println("Running an AND gate");
    System.out.println("A: "+A+ " B: "+B);
    AND and = new AND(A, B);
    and.evaluate();
    System.out.println("Output: " +and.output());
    A.flip();
    B.flip();
    System.out.println("A: "+A+ " B: "+B);
    and.evaluate();
    System.out.println("Output: " +and.output());

    System.out.println();
    System.out.println("Making an half adder");
    System.out.println("A: "+A+ " B: "+B);
    AND carry = new AND(A, B);
    carry.evaluate();
    System.out.println("Sum: " +carry.output());
    System.out.println();
    XOR sum = new XOR(A, B);
    sum.evaluate();
    System.out.println("Carry: " +sum.output());
}