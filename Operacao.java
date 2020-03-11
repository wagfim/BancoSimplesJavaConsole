/**
 * Classe utilizada para armazenar um historico das operações realizadas em uma conta
 * @author Wagner Bonfim <github.com/wagfim>
 */
public class Operacao {
    private String nomeOperacao;
    private double valor;
    private int numBeneficiario;

    public Operacao(String nomeOperacao, int numBeneficiario, double valor) {
        this.nomeOperacao = nomeOperacao;
        this.valor = valor;
        this.numBeneficiario = numBeneficiario;
    }

    public String getNomeOperacao() {
        return nomeOperacao;
    }
    
    public double getValor() {
        return valor;
    }

    public int getNumBeneficiario() {
        return numBeneficiario;
    }

}
