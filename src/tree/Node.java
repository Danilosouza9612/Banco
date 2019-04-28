package tree;

import java.util.List;

class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    T data;
    int fatBal;
	
    Node(T data){
    	this.data=data;
    }
    Node<T> getLeft() {
	return left;
    }
    void setLeft(Node<T> left) {
	this.left = left;
    }
    Node<T> getRight() {
    	return right;
    }

    void setRight(Node<T> right) {
    	this.right = right;
    }

    T getData() {
    	return data;
    }

    void setData(T data) {
    	this.data = data;
    }

    int getFatBal() {
    	return fatBal;
    }

    void setFatBal(int fatBal) {
    	this.fatBal = fatBal;
    }
    void emOrdem(StringBuilder sb) {
	if(this.left!=null) {
            left.emOrdem(sb);
	}
	sb.append(this.data).append("-").append("(").append(this.fatBal).append(") ");
	if(this.right!=null) {
            right.emOrdem(sb);
	}
    }
   void emOrdem(List<T> lista) {
	if(this.left!=null) {
            left.emOrdem(lista);
	}
	lista.add(data);
	if(this.right!=null) {
            right.emOrdem(lista);
	}
    }
}