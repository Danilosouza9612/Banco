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
class QueueNode {
    private Node data;
    private QueueNode next;
    
    QueueNode(Node data){
        this.data=data;
    }
    
    Node getData(){
        return data;
    }
    void setData(Node data){
        this.data=data;
    }
    QueueNode getNext(){
        return next;
    }
    void setNext(QueueNode next){
        this.next=next;
    }
}
