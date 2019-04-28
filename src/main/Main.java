/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import banco.Banco;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author aluno
 */
public class Main {
    public static void main(String[] args){
        int opcao;
        Scanner l = new Scanner(System.in);
        Banco banco = new Banco();
        do{
            System.out.println("Menu Principal");
            System.out.println("1 - Contas\n2 - Clientes\n3 - Sair");
            System.out.println("Digite uma opção:");
            try{
                opcao=l.nextInt();
            }catch(InputMismatchException ex){
                opcao=0;
            }
            l.nextLine();
            switch(opcao){
                case 1:
                    menuConta(banco);
                    break;
                case 2:
                    menuCliente(banco);
                    break;
                case 3:
                    banco.toRepository();
                    break;
                default:
                    System.err.println("Opção Inválida"); 
            }
        }while(opcao!=3);
    }
    
    public static void menuCliente(Banco banco){
        int opcao;
        Scanner l = new Scanner(System.in);
        do{
            System.out.println("Menu de Clientes");
            System.out.println("1 - Cadastrar Cliente\n2 - Remover Cliente\n3 - Consultar Cliente\n4 - Alterar Cliente\n5 - Voltar");
            System.out.println("Digite uma opção:");
            try{
                opcao=l.nextInt();
            }catch(InputMismatchException ex){
                opcao=0;
            }
            l.nextLine();
            switch(opcao){
                case 1:
                    banco.cadastrarCliente(pegarCPFCliente());
                    break;
                case 2:
                    banco.removerCliente(pegarCPFCliente());
                    break;
                case 3:
                    banco.ConsultarCliente(pegarCPFCliente());
                    break;
                case 4:
                    banco.editarCliente(pegarCPFCliente());
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Opção inválida");
            }
        }while(opcao!=5);
    }
    public static void menuConta(Banco banco){
        int opcao;
        Scanner l = new Scanner(System.in);
        
        do{
            System.out.println("Menu de Contas");
            System.out.println("1 - Cadastrar Conta\n2 - Consultar Saldo\n3 - Fazer Depósito\n4 - Fazer Saque\n5 - Exibir Contas\n6 - Exibir Contas de Cliente\n7 - Remover Conta\n8 - Voltar");
            System.out.println("Digite uma opção:");
            try{
                opcao=l.nextInt();
            }catch(InputMismatchException ex){
                opcao=0;
            }
            l.nextLine();
            switch(opcao){
                case 1:
                    banco.cadastrarConta(pegarNumeroConta());
                    break;
                case 2:
                    banco.consultarSaldo(pegarNumeroConta());
                    break;
                case 3:
                    banco.fazerDeposito(pegarNumeroConta());
                    break;
                case 4:
                    banco.fazerSaque(pegarNumeroConta());
                    break;
                case 5:
                    banco.verContas();
                    break;
                case 6:
                    banco.exibirContasPorCPF(pegarCPFCliente());
                    break;
                case 7:
                    banco.removerConta(pegarNumeroConta());
                    break;
                case 8:
                    break;
                default:
                    System.err.println("Opção Inválida");
            }
        }while(opcao!=8);
    }
    public static String pegarNumeroConta(){
        Scanner l = new Scanner(System.in);
        System.out.println("Digite o número da conta");
        return l.nextLine();        
    }
    public static String pegarCPFCliente(){
        Scanner l = new Scanner(System.in);
        System.out.println("Digite o CPF do Cliente (Sem Hifen e Pontos)");
        return l.nextLine();        
    }
    
}
