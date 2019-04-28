/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import banco.Cliente;
import banco.Conta;
import tree.ClienteTree;

/**
 *
 * @author Danilo
 */
public class ClienteDAO {
    private String nomeArq;
    private ArquivoBinario<Cliente> dataBase;
    
    public ClienteDAO(String nomeArq){
        this.nomeArq=nomeArq;
        dataBase = new ArquivoBinario();
    }
    
    public void toRepository(Cliente cliente){
        dataBase.write(cliente);
    }
    public void load(ClienteTree clientes){
        Cliente cliente;
        dataBase.OpenToRead(nomeArq);
        while((cliente=dataBase.read())!=null){
            clientes.inserir(cliente);
        }
        dataBase.closerAfterRead();
    }
    public void flush(){
        dataBase.writeFlush();
    }
    public void clean(){
        dataBase.removeArquivo(nomeArq);
    }
    public void open(){
        dataBase.openToWrite(nomeArq);
    }
}
