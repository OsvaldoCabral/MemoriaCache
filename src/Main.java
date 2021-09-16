public class Main {

    public static void main(String[] args) throws InvalidAddress {
        IO es = new IO(System.out);
        RAM ram = new RAM(7);
        Cache cache = new Cache(8, ram);
        CPU cpu = new CPU(cache, es);

        try {
            final int start = 10;

            ram.Write(start, 118);
            ram.Write(start+1, 130);

            cpu.Run(start);
        }
        catch (EnderecoInvalid e) {
            System.out.print(e);
        }
    }

}

