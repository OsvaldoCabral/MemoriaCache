import java.io.PrintStream;

public class IO {

    public final PrintStream out;

    public IO(PrintStream out) {
        this.out = out;
    }

    public void Output(String s) {
        out.println(s);
    }

}
