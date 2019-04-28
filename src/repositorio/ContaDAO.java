/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import banco.Cliente;
import banco.Conta;
import tree.ClienteTree;
import tree.ContaTree;
import tree.Tree;

/**
 *
 * @author Danilo
 */
public class ContaDAO{
    private String nomeArq;
    private ArquivoBinario<GConta> dataBase;
    
    public ContaDAO(String nomeArq){
        dataBase = new ArquivoBinario();
        this.nomeArq=nomeArq;
    }
    
    public void toRepository(Conta conta){
        GConta gConta = new GConta(conta);
        dataBase.write(gConta);
    }
    public void flush(){
        dataBase.writeFlush();
    }
    public void load(ClienteTree clientes, ContaTree contas){
        GConta gConta;
        dataBase.OpenToRead(nomeArq);
        while((gConta=dataBase.read())!=null){
            contas.inserir(gConta.toConta(clientes));
        }
        dataBase.closerAfterRead();
    }
    public void clean(){
        dataBase.removeArquivo(nomeArq);
    }
    public void open(){
        dataBase.openToWrite(nomeArq);        
    }
}
