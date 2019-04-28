/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import banco.Cliente;
import repositorio.ClienteDAO;

/**
 *
 * @author Danilo
 */
public class ClienteTree extends Tree<Cliente> {
    public ClienteTree(){
        super();
    }
    
    public void toRepository(ClienteDAO dao){
        Queue fila = new Queue();
        Node<Cliente> aux;
        dao.open();
        if(raiz==null){
            dao.clean();
        }else{
            fila.enQueue(raiz);
            while(!fila.isEmpty()){
                aux=fila.deQueue();
                dao.toRepository(aux.data);
                if(aux.left!=null){
                    fila.enQueue(aux.left);
                }
                if(aux.right!=null){
                    fila.enQueue(aux.right);
                }
            }
        }
    }
   public Cliente busca(String cpf){
        Node<Cliente> result;
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
    Node<Cliente> buscar(String cpf, Node<Cliente> raiz){
        int comp=cpf.compareTo(raiz.data.getCpf());
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
