package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T extends Comparable<T>> {
    protected Node<T> raiz;
    protected boolean decrease;
    protected boolean increase;
	

    public void inserir(T data){
        raiz = inserirNode(data, this.raiz);
    }
    public void remover(T data){
        raiz = removerNode(data, this.raiz);
    }
    Node<T> inserirNode(T data, Node<T> raiz){
    	int comp;
    	if(raiz==null) {
            raiz = new Node<T>(data);
            increase=true;
        }else {
            comp = data.compareTo(raiz.data);
            if(comp==0) {
                return raiz;
            }else if(comp<0) {
		raiz.left=inserirNode(data, raiz.left);
		if(increase) {
                    raiz.fatBal--;
                    if(raiz.fatBal==0) {
                        increase=false;
                    }
                    raiz=rebalancearEsq(raiz);
		}
            }else {
		raiz.right=inserirNode(data, raiz.right);
		if(increase) {
                    raiz.fatBal++;
                    if(raiz.fatBal==0) {
			increase=false;
                    }
                    raiz=rebalancearDir(raiz);
		}
            }
	}
	return raiz;
    }
    Node<T> removerNode(T data, Node<T> raiz){
	int comp=data.compareTo(raiz.data);
	Node<T> maiorEsq;
	if(comp==0) {
            if(raiz.left==null && raiz.right==null) {
		decrease=true;
		return null;
            }else if(raiz.left==null) {
		decrease=true;
		return raiz.right;
            }else if(raiz.right==null) {
		decrease=true;
		return raiz.left;
            }else {
		maiorEsq=maior(raiz.left);
		raiz.data=maiorEsq.data;
		raiz.left=removerMaiorValor(raiz.left);
		if(decrease) {
                    raiz.fatBal++;
                    raiz=rebalancearDir(raiz);
                    if(raiz.fatBal==1) {
			decrease=false;
                    }
		}
		return raiz;
            }
        }else if(comp<0) {
            if(raiz.left!=null) {
            	raiz.left=removerNode(data, raiz.left);
		if(decrease) {
                    raiz.fatBal++;
                    raiz=rebalancearDir(raiz);
                    if(raiz.fatBal==1) {
			decrease=false;
                    }
		}
            }
        }else {
            if(raiz.right!=null) {
                raiz.right=removerNode(data, raiz.right);
                if(decrease) {
                    raiz.fatBal--;
                    raiz=rebalancearEsq(raiz);
                    if(raiz.fatBal==-1) {
                        decrease=false;
                    }
                }
            }			
        }
	return raiz;
    }
    Node<T> maior(Node<T> raiz){
        if(raiz.right==null) {
            return raiz;
	}
		
	return maior(raiz.right);
    }
    Node<T> removerMaiorValor(Node<T> raiz){
	if(raiz.right==null) {
            decrease=true;
            return raiz.left;
	}
		
	raiz.right=removerMaiorValor(raiz.right);
	if(decrease) {
            raiz.fatBal--;
            raiz=rebalancearDir(raiz);
            if(raiz.fatBal==-1) {
		decrease=false;
            }
	}
	return raiz;	
    }
    Node<T> rebalancearEsq(Node<T> r) {
	Node<T> a, b, c;
	a=r;
	if(a.fatBal==-2) {
            b=a.left;
            if(b.fatBal>0) {
		c=b.right;
		if(c.fatBal<0) {
                    c.fatBal=0;
                    b.fatBal=0;
                    a.fatBal=1;
		}else if(c.fatBal==0) {
                    c.fatBal=0;
                    b.fatBal=0;
                    a.fatBal=0;
		}else {
                    c.fatBal=0;
                    b.fatBal=-1;
                    a.fatBal=0;
                    decrease=false;
		}
		b=rotacaoEsq(b, c);
            }else {
		if(b.fatBal==0) {
                    b.fatBal=1;
                    a.fatBal=-1;
                    decrease=false;
		}else {
                    b.fatBal=0;
                    a.fatBal=0;
		}
            }
            increase=false;
            return rotacaoDir(a , b);
	}
        return r;
    }
    Node<T> rebalancearDir(Node<T> r){
        Node<T> a, b, c;
        a=r;
        if(a.fatBal==2){
            b=a.right;
            if(b.fatBal<0) {
		c=b.left;
		if(c.fatBal>0) {
                    c.fatBal=0;
                    b.fatBal=0;
                    a.fatBal=-1;
		}else if(c.fatBal==0) {
                    c.fatBal=0;
                    b.fatBal=0;
                    a.fatBal=0;
		}else {
                    c.fatBal=0;
                    b.fatBal=1;
                    a.fatBal=0;
                    decrease=false;
                }
		b=rotacaoDir(b,c);
            }else {
		if(b.fatBal==0) {
                    b.fatBal=-1;
                    a.fatBal=1;
                    decrease=false;
		}else {
                    b.fatBal=0;
                    a.fatBal=0;
		}				
            }
            increase=false;
            return rotacaoEsq(a,b);
	}
	return r;
    }
    Node<T> rotacaoEsq(Node<T> a, Node<T> b){
        a.right=b.left;
	b.left=a;
	return b;
    }
    Node<T> rotacaoDir(Node<T> a, Node<T> b){
	a.left=b.right;
	b.right=a;
	return b;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
		
	if(raiz!=null) {
            raiz.emOrdem(sb);
	}
		
	return sb.toString();
    }
    public List<T> toList(){
        ArrayList<T> lista = new ArrayList<T>();
        
        if(raiz!=null){
            raiz.emOrdem(lista);
        }
        
        return lista;
    }    
}
