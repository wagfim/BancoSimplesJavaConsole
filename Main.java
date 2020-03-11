import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 * @author Wagner Bonfim <github.com/wagfim>
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numeroConta;
        int senhaInformada;
        boolean sairDoSistema = false;
        
        ClientesProntos clientes = new ClientesProntos();
        List<Conta> listaContas = new ArrayList<>();
        
        Conta conta1 = new Conta(4758, 12345, new Data(10,3,2020), 87.00, clientes.cliente1);
        Conta conta2 = new Conta(2045, 12345, new Data(10,3,2020), 758.80, clientes.cliente2);
        Conta conta3 = new Conta(1584, 12345, new Data(10,3,2020), 10000.00, clientes.cliente3);   
        listaContas.add(conta1);
        listaContas.add(conta2);
        listaContas.add(conta3);
        
        System.out.println("************************");
        System.out.println("   Bem vindo ao Banco   ");
        System.out.println("************************");
        System.out.println();
        
        do {
            //identifica a conta do cliente
            do {            
            System.out.print("\nInforme o seu numero de conta: ");
            numeroConta = Integer.parseInt(scan.next());
            
            if (!checaConta(numeroConta, listaContas))
                System.out.println("Número invalido. Tente novamente");
            
            } while (!checaConta(numeroConta, listaContas));

            Conta contaEscolhida = retornaConta(numeroConta, listaContas);

            do {
                System.out.print("Informe a sua senha: ");
                senhaInformada = Integer.parseInt(scan.next());

                if (!checaSenha(senhaInformada, contaEscolhida))
                    System.out.println("Senha invalido. Tente novamente");

            } while (!checaSenha(senhaInformada, contaEscolhida));
            //fim identifica conta
            
            //menu de conta
            if(!Objects.isNull(contaEscolhida)) {
                boolean continueRodando = true;
                int opcaoEscolhida;

                System.out.println("\nOla, " + contaEscolhida.getCliente().getNome() + "!");

                do {                
                    System.out.println("\nQual operacao deseja realizar?");
                    System.out.println("1. Saldo");
                    System.out.println("2. Saque");
                    System.out.println("3. Deposito");
                    System.out.println("4. Transferencia entre contas");
                    System.out.println("5. Informacoes desta conta");
                    System.out.println("6. Extrato de Operacoes");
                    System.out.println("7. Alternar para outra conta");
                    System.out.println("0. Sair");
                    System.out.print("> ");

                    opcaoEscolhida = Integer.parseInt(scan.next());

                    switch(opcaoEscolhida) {
                        case 0:
                            continueRodando = false; //sai do loop do menu de conta
                            sairDoSistema = true; //sai do loop que mantem o sistema
                        break;
                        case 1:
                            System.out.println("Saldo: " + contaEscolhida.getSaldo());
                            pressioneEnter();
                        break;
                        case 2:
                            executaProcessoDeSaque(listaContas, contaEscolhida);
                            pressioneEnter();
                        break;
                        case 3:
                            executaProcessoDeDeposito(listaContas, contaEscolhida);
                            pressioneEnter();
                        break;
                        case 4:
                            executaProcessoDeTransferencia(listaContas, contaEscolhida);
                            pressioneEnter();
                        break;
                        case 5:
                            contaEscolhida.imprimeInformacoesConta();
                            pressioneEnter();
                        break;
                        case 6:
                            contaEscolhida.imprimeExtrato();
                            pressioneEnter();
                        break;
                        case 7:
                            continueRodando = false;
                            pressioneEnter();
                        break;
                        case 99:
                            executaTestes(listaContas, contaEscolhida);
                            pressioneEnter();
                        break;
                        default:
                            System.out.println("Opcao invalida");
                        break;
                    }
                } while (continueRodando);
            } //fim menu de conta //
        } while (!sairDoSistema); //fim sistema
    }
    
    public static void executaProcessoDeSaque(List<Conta> listaContas, Conta conta) {
        Scanner scan = new Scanner(System.in);
        double valor;

        System.out.print("Valor: ");
        valor = scan.nextDouble();
        if (conta.sacar(valor)) {
            System.out.println("Operacao realizada com sucesso!");
        } else {
            System.out.println("Erro na operacao");
        }
    }
    
    public static void executaProcessoDeDeposito(List<Conta> listaContas, Conta conta) {
        Scanner scan = new Scanner(System.in);
        double valor;
        
        System.out.print("Valor: ");
        valor = scan.nextDouble();
        if (conta.depositar(valor)) {
            System.out.println("Operacao realizada com sucesso!");
        } else {
            System.out.println("Erro na operacao");
        }
    }
    
    public static void executaProcessoDeTransferencia(List<Conta> listaContas, Conta contaOrigem) {
        Scanner scan = new Scanner(System.in);
        double valor;
        
        int numeroContaDestino;   
        do {
            System.out.print("Informe o numero da conta destino: ");
            numeroContaDestino = Integer.parseInt(scan.nextLine());
            if (!checaConta(numeroContaDestino, listaContas)) {
                System.out.println("Numero incorreto. Verifique e tente novamente.");
            }
        } while (!checaConta(numeroContaDestino, listaContas));

        Conta contaDestino = retornaConta(numeroContaDestino, listaContas);

        System.out.print("Valor: ");
        valor = scan.nextDouble();

        if (contaOrigem.transferirValor(valor, contaDestino)) {
            System.out.println("Operacao realizada com sucesso!");
        } else {
            System.out.println("Erro na operacao");
        }
    }
    
    public static void executaTestes(List<Conta> listaContas, Conta conta) {
        int contaDestino = 0;
        switch(conta.getNumero()){
            case 4758: contaDestino = 2045; break;
            case 2045: contaDestino = 4758; break;
            case 1584: contaDestino = 2045; break;
            default: break;
        }
        conta.sacar(15);
        conta.depositar(7000);
        conta.transferirValor(150, retornaConta(contaDestino, listaContas));
    }
    
    public static Conta retornaConta(int numero, List<Conta> listaContas) {
        for (int i = 0; i < listaContas.size(); i++) {
            if (listaContas.get(i).getNumero() == numero) {
                return listaContas.get(i);
            }
        }
        return null;
    }
    public static boolean checaSenha(int senha, Conta conta) {
        if (conta.getSenha() == senha) {
            return true;
        }
        return false;
    }
    public static boolean checaConta(int numeroConta, List<Conta> listaContas) {
        for (int i = 0; i < listaContas.size(); i++) {
            if (listaContas.get(i).getNumero() == numeroConta) {
                return true;
            }
        }
        return false;
    }
    public static void pressioneEnter() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Pressione Enter para continuar...");
        scan.nextLine();
        //scan.close();
    }
    
}
