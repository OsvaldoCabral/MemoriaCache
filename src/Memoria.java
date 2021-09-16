public interface Memoria {

    int Read(int endereco) throws EnderecoInvalid;

    void Write(int endereco, int palavra) throws EnderecoInvalid;

}
