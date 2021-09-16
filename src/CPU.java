public class CPU {

    private int pc;
    private final Cache cache;
    private final IO es;

    public CPU(Cache cache, IO es) {
        this.cache = cache;
        this.es = es;
    }

    public void Run(int endereco) throws EnderecoInvalid, InvalidAddress {

        pc = endereco;

        int regA = cache.Read(pc++);
        int regB = cache.Read(pc++);

        for (int i = regA; i<=regB; i++) {
            cache.Write(i, i-regA+1);
            es.Output("cpu> " + i + " -> " + (i-regA+1));
        }

    }

}
