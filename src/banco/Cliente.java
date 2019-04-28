/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.Serializable;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Danilo
 */
public class Cliente implements Comparable<Cliente>, Serializable {
    private final String cpf;
    private String nome;
    private String email;
    private String telefone;

    public Cliente(String cpf) throws Exception{
        if(!cpf.matches("[0-9]{3}[0-9]{3}[0-9]{3}[0-9]{2}")){
            throw new Exception("CPF Inválido");
        }
        this.cpf=cpf;
    }
    public String getCpf(){
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome=nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws Exception {
        if(!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
            throw new Exception("E-mail inválido");
        }
        this.email=email;
    }
    public String getTelefone() {
        MascaraString mascara;
        if(this.telefone.length()==10){
            mascara = new MascaraString("(##) ####-####");
            return mascara.mascara(telefone);
        }else{
            mascara = new MascaraString("(##) ####-#####");
            return mascara.mascara(telefone);
        }
    }
    public void setTelefone(String telefone) throws Exception {
        if(!telefone.matches("^[0-9]{2}([0-9]{8}|[0-9]{9})")){
            throw new Exception("Telefone Inválido");
        }
        this.telefone=telefone;
    }
    @Override
    public int compareTo(Cliente t) {
        return this.cpf.compareTo(t.cpf);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        
        sb.append("Nome:")
                .append(this.nome)
                .append(" E-mail:")
                .append(this.email)
                .append(" Telefone:")
                .append(this.getTelefone());
        
        return sb.toString();
    }
    
}
