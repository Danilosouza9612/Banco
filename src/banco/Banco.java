/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import repositorio.ClienteDAO;
import repositorio.ContaDAO;
import tree.ClienteTree;
import tree.ContaTree;

/**
 *
 * @author Aluno
 */
public class Banco{
    private ContaTree contas;
    private ClienteTree clientes;
    private ContaDAO contaDAO;
    private ClienteDAO clienteDAO;
    
    public Banco(){
        contas = new ContaTree();
        clientes= new ClienteTree();
        clienteDAO = new ClienteDAO("clientes");
        contaDAO = new ContaDAO("contas");
        clienteDAO.load(clientes);
        contaDAO.load(clientes, contas);
    }
    
    
    public void cadastrarConta(String numeroConta){
        Scanner l = new Scanner(System.in);
        Conta nova;
        int tipo;
        String cpf;
        Cliente cliente;
        Conta busca;
        busca = buscaConta(numeroConta);
        if(busca!=null){
            System.err.println("Essa conta já existe em nosso cadastro");
        }else{
            do{
                System.out.println("Digite o CPF do cliente (Sem Hifen e Pontos) (Digite FIM para Cancelar)");
                cpf=l.nextLine();
                if(cpf.equalsIgnoreCase("FIM")){
                    return;
                }
                cliente = buscaCliente(cpf);
                if(cliente==null){
                    System.err.println("Cliente não encontrado");
                }
            }while(cliente==null);


            do{
                System.out.println("Digite o tipo da conta (1 - Conta Corrente Simples 2 - Conta Corrente Especial) (-1 para Sair)");
                tipo=l.nextInt();
                l.nextLine();
                if(tipo==-1){
                    return;
                }
            }while(tipo!=1 && tipo!=2);

            nova = new Conta(numeroConta, tipo);
            nova.setCliente(cliente);
            contas.inserir(nova);
        }
    }
    
    public Conta buscaConta(String numeroConta){
        return contas.busca(numeroConta);
    }
    public void exibirContasPorCPF(String cpf){
        List<Conta> contasCPF = contas.listarPorCPF(cpf);
        
        if(contasCPF.isEmpty()){
            System.err.println("Não há nenhuma conta com esse CPF");
        }else{
            for(Conta item : contasCPF){
                System.out.println("Número da Conta:"+item.getNumeroConta() + " Tipo:"+item.getTipoContaString());
            }
        }
    }
    public void consultarSaldo(String numeroConta){
        Conta resultado;
      
        resultado = buscaConta(numeroConta);

        if(resultado==null){
            System.err.println("Conta não encontrada");
        }else{
            System.out.println("Saldo: R$"+resultado.getSaldo());
            if(resultado.getTipoConta()==2){
                System.out.println("Crédito:R$"+resultado.getCredito());
            }
        }
    }
    public void fazerDeposito(String numeroConta){
        Scanner l = new Scanner(System.in);
        Conta resultado;
        double deposito=0;
        
        resultado = buscaConta(numeroConta);
        
         if(resultado==null){
            System.err.println("Conta não encontrada");
        }else{
            System.out.println("Digite o valor a ser depositado:");
            try{
                deposito=l.nextDouble();
                resultado.creditar(deposito);
            }catch(java.util.InputMismatchException ex){
                System.err.println("Valor inválido");
            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }            
        }
    }
    public void fazerSaque(String numeroConta){
        Scanner l = new Scanner(System.in);
        Conta resultado;
        double saque;
        
        
        resultado = buscaConta(numeroConta);
        
        if(resultado==null){
            System.err.println("Conta não encontrada");
        }else{
            System.out.println("Digite o valor a ser sacado:");
            try {
                saque=l.nextDouble();
                resultado.debitar(saque);
            }catch(java.util.InputMismatchException ex){
                System.err.println("Valor inválido");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            l.nextLine();
        }
    }
    public void removerConta(String numeroConta){
        Conta resultado;
            
        resultado=buscaConta(numeroConta);
        
        if(resultado.getSaldo()==0 && resultado.getCredito()==2000){
            contas.remover(resultado);
        }else{
            System.err.println("Conta com saldo ou com dívida de crédito");
        }
    }
    public void verContas(){
        List<Conta> lista = contas.toList();
        
        if(!lista.isEmpty()){
            Collections.sort(lista, Comparator.comparing(Conta::getCpfCliente));

            for(Conta item : lista){
                System.out.println("Número:"+item.getNumeroConta() + " Titular:"+item.getCliente().getNome()+" CPF do Titular:"+item.getCpfCliente()+" Telefone:"+item.getCliente().getTelefone());
            }
        }else{
            System.err.println("Não há nenhuma conta cadastrada");
        }
    }
    public Cliente buscaCliente(String cpf){
        return clientes.busca(cpf);
    }
    public void cadastrarCliente(String cpf){
        Cliente busca, novo;
        
        busca=buscaCliente(cpf);
        if(busca!=null){
            System.err.println("O cliente já está cadastrado");
        }else{      
            try{
                novo = new Cliente(cpf);
                if(!edicaoCliente(novo)){
                    return;
                }
                clientes.inserir(novo);
            }catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    private boolean edicaoCliente(Cliente cliente){
        Scanner l = new Scanner(System.in);
        boolean valido;
        String nome, email, telefone;
        System.out.println("Digite FIM para Cancelar e AV para Avançar");
        System.out.println("Digite o nome");
        do{
            valido=true;
            nome=l.nextLine();
            if(nome.equalsIgnoreCase("FIM")){
                return false;
            }else if(nome.equalsIgnoreCase("AV")){
                valido=false;
                System.err.println("Informe o nome do cliente");
            }else{
                cliente.setNome(nome);
            }
        }while(!valido);
        
        System.out.println("Digite o email");
        do{
            valido=true;
            email=l.nextLine();
            if(email.equalsIgnoreCase("FIM")){
                return false;
            }else if(!email.equalsIgnoreCase("AV")){
                try {
                    cliente.setEmail(email);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    valido=false;
                }
            }
        }while(!valido);
        
        System.out.println("Digite o Telefone (DDXXXXXXXXXX");
        do{
            valido=true;
            try{
                telefone=l.nextLine();
                if(telefone.equalsIgnoreCase("FIM")){
                    return false;
                }else if(!telefone.equalsIgnoreCase("AV")){
                    cliente.setTelefone(telefone);  
                }
            }catch(Exception ex){
                valido=false;
                System.err.println(ex.getMessage());
            }
        }while(!valido);
        return true;
    }
    public void ConsultarCliente(String cpf){
        Cliente cliente = buscaCliente(cpf);
        
        if(cliente==null){
            System.err.println("Cliente não encontrado");
        }else{
            System.out.println(cliente);
        }
    }
    public void removerCliente(String cpf){
        Cliente cliente;
        
        if(!contas.temContaCadastrada(cpf)){
            cliente = buscaCliente(cpf);
            clientes.remover(cliente);
        }else{
            System.err.println("O cliente tem conta cadastrada");
        }
    }
    public void editarCliente(String cpf){
        Cliente cliente = buscaCliente(cpf);
        
        if(cliente==null){
            System.err.println("Cliente não encontrado");
        }else{
            edicaoCliente(cliente);
        }
    }
    public void toRepository(){
        clientes.toRepository(clienteDAO);
        contas.toRepository(contaDAO);
    }
}
