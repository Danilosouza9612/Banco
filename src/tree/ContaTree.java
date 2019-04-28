/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import banco.Conta;
import java.util.ArrayList;
import java.util.List;
import repositorio.ContaDAO;

/**
 *
 * @author Danilo
 */
public class ContaTree extends Tree<Conta> {
    public ContaTree(){
        super();
    }
    public List<Conta> listarPorCPF(String cpf){
        Queue fila;
        Node<Conta> aux;
        ArrayList<Conta> list = new ArrayList();
        if(raiz==null){
            return list;
        }else{
            fila = new Queue();
            fila.enQueue(raiz);
            while(!fila.isEmpty()){
                aux = fila.deQueue();
                if(aux.left!=null){
                    fila.enQueue(aux.left);
                }
                if(aux.data.getCpfCliente().equals(cpf)){
                    list.add(aux.data);
                }
                if(aux.right!=null){
                    fila.enQueue(aux.right);
                }
            }
        }
        return list;
    }
    public boolean temContaCadastrada(String cpf){
        Queue fila;
        Node<Conta> aux;
        if(raiz==null){
            return false;
        }else{
            fila = new Queue();
            fila.enQueue(raiz);
            while(!fila.isEmpty()){
                aux = fila.deQueue();
                if(aux.data.getCpfCliente().equals(cpf)){
                    return true;
                }
                if(aux.left!=null){
                    fila.enQueue(aux.left);
                }
                if(aux.right!=null){
                    fila.enQueue(aux.right);
                }
            }
        }
        return false;
    }
    public void toRepository(ContaDAO dao){
        Queue fila = new Queue();
        Node<Conta> aux;
        dao.open();
        if(raiz==null){
            dao.clean();
        }else{
            fila.enQueue(raiz);
            while(!fila.isEmpty()){
                aux=fila.deQueue();
                if(aux.left!=null){
                    fila.enQueue(aux.left);
                }
                dao.toRepository(aux.data);
                if(aux.right!=null){
                    fila.enQueue(aux.right);
                }
            }
        }
    }

   public Conta busca(String cpf){
        Node<Conta> result;
        if(raiz==null){
            return null;
        }else{
            result=buscar(cpf, raiz);
            if(result==null){
                return null;
            }else{
                return result.getData();
            }
        }
    }
    Node<Conta> buscar(String cpf, Node<Conta> raiz){
        int comp=cpf.compareTo(raiz.data.getNumeroConta());
        if(comp==0){
            return raiz;
        }else if(comp<0){
            if(raiz.left==null){
                return null;
            }
            return buscar(cpf, raiz.left);
        }else{
            if(raiz.right==null){
                return null;
            }
            return buscar(cpf, raiz.right);
        }
    }
}
