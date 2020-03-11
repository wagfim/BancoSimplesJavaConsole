import java.util.ArrayList;
import java.util.List;

/**
 * @author Wagner Bonfim <github.com/wagfim>
 */
public class Conta {
    private int numero;
    private int senha;
    private double saldo;
    private Data dataAbertura;
    private Cliente cliente;
    List<Operacao> historicoOperacoes;

    public Conta(int numero, int senha, Data dataAbertura, double saldo, Cliente cliente) {
        this.numero = numero;
        this.senha = senha;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.cliente = cliente;
        this.historicoOperacoes = new ArrayList();
    }
    
    public Conta(int numero, int senha, Data dataAbertura, Cliente cliente) {
        this(numero, senha, dataAbertura, 0, cliente);
    }    
    
    public boolean  sacar(double valor) {
        if (saldo > 0) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                armazenaDadosOperacao("Saque", this.numero, valor);
                return true; //sucesso
            }
        }
        return false;
    }  
    
    public boolean  depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            armazenaDadosOperacao("Deposito", this.numero, valor);
            return true;
        }
        return false;
    }
    
    public boolean transferirValor(double valor, Conta contaDestino) {
        if (!this.equals(contaDestino) && sacar(valor)) {
            contaDestino.depositar(valor);
            armazenaDadosOperacao("Transferencia", contaDestino.getNumero(), valor);
            return true;
        }
        return false;
    }
    
    public void armazenaDadosOperacao(String nome, int numBeneficiario, double valor) {
        historicoOperacoes.add(new Operacao(nome, numBeneficiario, valor));
    }
    
    public void imprimeInformacoesConta() {
        System.out.println("*****************************************");
        System.out.println("Nome do cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCPF());
        System.out.println("Data de abertura: " + this.dataAbertura.getDataFormatada());
        System.out.println("Saldo: " + this.saldo);
        System.out.println("*****************************************");
    }
    
    public void imprimeExtrato() {
        System.out.println("Operacao\tBeneficiario\tValor ");
        for(Operacao operacao : historicoOperacoes) {
            System.out.print(operacao.getNomeOperacao());
            if(operacao.getNomeOperacao().equals("Saque"))
                System.out.print("...........");
            if(operacao.getNomeOperacao().equals("Deposito"))
                System.out.print("........");
            if(operacao.getNomeOperacao().equals("Transferencia"))
                System.out.print("...");
            System.out.print(operacao.getNumBeneficiario() + "............");
            System.out.println(operacao.getValor());;  
        }
        /* Espaçamento usando tabs
        for(Operacao operacao : historicoOperacoes) {
            System.out.print(operacao.getNomeOperacao());
            if(operacao.getNomeOperacao().equals("Saque"))
                System.out.print("\t\t");
            if(operacao.getNomeOperacao().equals("Deposito"))
                System.out.print("\t");
            if(operacao.getNomeOperacao().equals("Transferencia"))
                System.out.print("\t");
            System.out.print(operacao.getNumBeneficiario() + "\t");
            System.out.println(operacao.getValor());;  
        }
        */     
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