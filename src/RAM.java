

class EnderecoInvalid extends Exception {
    private final int ender;
    public EnderecoInvalid(int e) {
        super();
        ender = e;
    }
    @Override
    public String toString() {
        return "Endereço " + ender + " inválido!";
    }
}


public class RAM implements Memoria{

    private int size = 0;
    public int[] enderecos;


    public RAM(int k) {
        this.size = (int) Math.pow(2, k);
        this.enderecos = new int[size];
    }

    public int Size() {
        return this.size;
    }

    @Override
    public int Read(int endereco) throws EnderecoInvalid {
        if(IsValid(endereco))
            return enderecos[endereco];
        else
            throw new EnderecoInvalid(endereco);
    }


    @Override
    public void Write(int endereco, int palavra) throws EnderecoInvalid {

       boolean hit = valueIsInCache(enderecos[endereco]);

        if(hit)
            enderecos[endereco] = palavra;
        else
            throw new EnderecoInvalid(endereco);
    }

    public boolean IsValid(int ender) {
        return (ender > 0 && ender < size);
    }


    public boolean valueIsInCache(int valor) {
        boolean result = false;
        for (int val : enderecos) {
            if(val == valor) result = true;
        }
        return result;
    }

}
