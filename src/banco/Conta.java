/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author Danilo
 */
public class Conta implements Comparable<Conta>{
    private final String numeroConta;
    private int tipoConta;
    private double saldo;
    private Cliente cliente;

    public Conta(String numeroConta, int tipoConta) {
        this.numeroConta=numeroConta;
        this.tipoConta=tipoConta;
    }
    public Conta(String numeroConta, double saldo, int tipoConta){
        this.saldo=saldo;
        this.numeroConta=numeroConta;
        this.tipoConta=tipoConta;
    }
    public String getNumeroConta(){
        return numeroConta;
    }  
    public int getTipoConta() {
        return tipoConta;
    }
    public String getTipoContaString(){
        if(tipoConta==1){
            return "Conta Corrente Simples";
        }else{
            return "Conta Corrente Especial";
        }        
    }
    public void setTipoConta(int tipoConta) {
        this.tipoConta=tipoConta;
    }
    public double getSaldo() {
        if(saldo<0){
            return 0;
        }
        return saldo;
    }
    public double getCredito(){
        if(tipoConta==2){
            if(saldo<0){
                return 2000-(-saldo);
            }else{
                return 2000;
            }
        }else{
            return 0;
        }
    }
    public void creditar(double valor) throws Exception{
        if(valor>0){
            this.saldo=this.saldo+valor;
        }else{
            throw new Exception("Valor Inválido");
        }
    }
    public void debitar(double valor) throws Exception{
        double credito;
        if(saldo>0){
            if(valor>saldo){
                if(this.tipoConta==2){
                    credito=this.getCredito();
                    if(credito<valor-saldo){
                        throw new Exception("Credito insuficiente");
                    }else{
                        this.saldo=this.saldo-valor; 
                    }
                }else{
                    throw new Exception("Saldo insuficiente");                    
                }
            }else{
                this.saldo=this.saldo-valor;
            }
        }else{
            if(this.tipoConta==1){
                throw new Exception("Não há saldo");
            }else{
                credito=this.getCredito();
                if(credito>0){
                    if(valor>credito){
                        throw new Exception("Não há mais crédito suficiente");                        
                    }else{
                        this.saldo=this.saldo-valor;
                    }
                }else{
                    throw new Exception("Não há mais crédito suficiente");                                            
                }
            }
        }
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente=cliente;
    }
    @Override
    public int compareTo(Conta t) {
        return this.numeroConta.compareTo(t.numeroConta);
    }
    public String getCpfCliente(){
        return cliente.getCpf();
    }
    
    
    
}
