package Model;

public class Endereco {
    private String logradouro;
    private int numero;
    private String complemento;
    private Cidade cidade;

    //CONSTRUTORES
    public Endereco() {

    }

    public Endereco(String logradouro, int numero, String complemento, Cidade cidade) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    //GETTERS E SETTERS
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Endereco = (" +
                logradouro +
                ", " + numero +
                "), (Complemento = " + complemento +
                ")" + cidade;
    }
}
