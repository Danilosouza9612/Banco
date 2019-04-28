/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import banco.Cliente;
import banco.Conta;
import java.io.Serializable;
import tree.ClienteTree;
import tree.Tree;

/**
 *
 * @author Danilo
 */
public class GConta implements Serializable{
    private final String numeroConta;
    private int tipoConta;
    private double saldo;
    private String cpfCliente;
    
    GConta(Conta conta){
        this.numeroConta=conta.getNumeroConta();
        this.tipoConta=conta.getTipoConta();
        this.saldo=conta.getSaldo();
        this.cpfCliente=conta.getCpfCliente();
    }
    
    Conta toConta(ClienteTree clientes){
        Conta conta = new Conta(numeroConta, saldo, tipoConta);
        conta.setCliente(clientes.busca(cpfCliente));       
        return conta;
    }
}
