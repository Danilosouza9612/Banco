/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Aluno
 */
class Queue {
    private QueueNode first;
    private QueueNode last;
    
    boolean isEmpty(){
        if(first==null){
            return true;
        }
        
        return false;
    }
    boolean isFull(){
        return false;
    }
    void enQueue(Node data){
        if(isEmpty()){
            first= new QueueNode(data);
            last=first;
        }else{
            last.setNext(new QueueNode(data));
            last=last.getNext();
        }
    }
    Node deQueue(){
        Node aux = first.getData();
        first=first.getNext();
        if(first==null){
            last=null;
        }
        
        return aux;
    }
}
