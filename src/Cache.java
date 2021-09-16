//
// Feedback atividade formativa Cache Simplificada
// PossÃ­vel implementaÃ§Ã£o da Cache Simplificada
// (considerando que a prÃ³xima memÃ³ria Ã© a RAM)
// PSCF - Prof. Luiz Lima Jr.
//

class InvalidAddress extends Exception {
    private final int ender;
    public InvalidAddress(int e) {
        super();
        ender = e;
    }
    @Override
    public String toString() {
        return "Endereço " + ender + " inválido!";
    }
}

public class Cache {
    private RAM ram = null;         // prÃ³xima memÃ³ria
    private int capacidade = 0;     // capacidade da cache
    private int end_ram = -1;       // ender. RAM do inÃ­cio do bloco armazenado
    private boolean modif = false;  // modificada?
    private int [] bloco = null;    // bloco de dados da cache

    public Cache(int tam, RAM prox) {
        ram = prox;
        capacidade = tam;
        bloco = new int [capacidade];
    }

    public int Read(int ender) throws InvalidAddress, EnderecoInvalid {

//        int x = Integer.parseInt(Integer.toBinaryString(ender));
//        int w = x & 0b111111;
//        int r = (x & 0b1111111000000) >> 6;
//        int t = (x & 0b111111111110000000000000) >> 13;

        VerificaCache(ender);   // cache hit ou cache miss?
        return bloco[ender - end_ram];
    }

    public void Write(int ender, int w) throws InvalidAddress, EnderecoInvalid {
        VerificaCache(ender);
        bloco[ender - end_ram] = w;
        modif = true;
    }

    private void VerificaCache(int ender) throws InvalidAddress, EnderecoInvalid {
        if (Hit(ender))
            System.out.println("Cache HIT: " + ender);
        else {
            System.err.println("Cache MISS: " + ender);
            TrazParaCache(ender);
        }
    }

    private boolean Hit(int ender) {
        if (end_ram > 0)    // cache jÃ¡ tem algum bloco carregado?
            return ((ender >= end_ram) && (ender < end_ram + capacidade));
        else
            return false;
    }

    private void TrazParaCache(int ender) throws InvalidAddress, EnderecoInvalid {
        if (modif) {
            // copia da cache para a RAM, pois foi modificado na cache
            for (int i=0; i<capacidade; ++i)
                ram.Write(end_ram+i, bloco[i]);
            modif = false;
        }
        // traz bloco iniciando no endereÃ§o solicitado da ram para a cache
        end_ram = ender;
        for (int a=ender; a<ender+capacidade && a<ram.Size(); ++a)
            bloco[a-ender] = ram.Read(a);
    }
}