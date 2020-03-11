package iniciante.banco;
/**
 * @author Wagner Bonfim <github.com/wagfim>
 */
public class Conta {
    private int numero;
    private int senha;
    private double saldo;
    private Data dataAbertura;
    private Cliente cliente;

    public Conta(int numero, int senha, Data dataAbertura, double saldo, Cliente cliente) {
        this.numero = numero;
        this.senha = senha;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.cliente = cliente;
    }
    
    public Conta(int numero, int senha, Data dataAbertura, Cliente cliente) {
        this(numero, senha, dataAbertura, 0, cliente);
    }    
    
    public boolean  sacar(double valor) {
        if (saldo > 0) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                return true; //sucesso
            }
        }
        return false;
    }
    
    public boolean  depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }
    
    public boolean transferirValor(double valor, Conta contaDestino) {
        if (!this.equals(contaDestino) && sacar(valor)) {
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }
    
    public void imprimeInformacoesConta() {
        System.out.println("*****************************************");
        System.out.println("Nome do cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCPF());
        System.out.println("Data de abertura: " + this.dataAbertura.getDataFormatada());
        System.out.println("Saldo: " + this.saldo);
        System.out.println("*****************************************");
    }
    

    public int getNumero() {
        return numero;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Data getDataAbertura() {
        return dataAbertura;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    
    
}
