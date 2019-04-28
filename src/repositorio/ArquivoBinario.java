/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Danilo
 */
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

class ArquivoBinario<T extends Serializable> {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    ArquivoBinario(){
        
    }
    
    private void criarArquivo(String nomeArq){
        File arquivo = new File(nomeArq);
        
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                System.err.println("Erro ao abrir o arquivo");
                System.exit(1);
            }
        }
    }
    void removeArquivo(String nomeArq){
        File arquivo = new File(nomeArq);
        
        if(arquivo.exists()){
            arquivo.delete();
        }
    }
    void OpenToRead(String nomeArq){
        try {
            FileInputStream fileInput = new FileInputStream(nomeArq);
            input = new ObjectInputStream(fileInput);
        } catch (FileNotFoundException ex) {
            criarArquivo(nomeArq);
        } catch(IOException ex){
            System.out.println("Erro ao abrir o arquivo");
        }
    }
    void openToWrite(String nomeArq){
        try {
            FileOutputStream fileOutput = new FileOutputStream(nomeArq);
            output = new ObjectOutputStream(fileOutput);
        } catch (FileNotFoundException ex) {
            criarArquivo(nomeArq);
        } catch(IOException ex){
            System.out.println("Erro ao abrir o arquivo");
        }
    }
    public T read(){
        T objeto=null;
        
        if(input!=null){
            try {
                objeto = (T)input.readObject();
                return objeto;
            }catch(EOFException ex){
                return null;
            }catch (IOException ex) {
                System.out.println("Erro ao ler o arquivo");
                System.exit(1);
            } catch (ClassNotFoundException ex) {
                System.out.println("Tipo incompatível");
                System.exit(1);
            }
        }
        return null;
    }
    void write(T objeto){
        if(output!=null){
            try {
                output.writeObject(objeto);
            } catch (IOException ex) {
                System.err.println("Erro ao gravar no arquivp");
            }
        }
    }
    void writeFlush(){
        if(output!=null){
            try {
                output.flush();
            } catch (IOException ex) {
                System.err.println("Erro ao gravar no arquivp");
            }
        }
    }
    void closerAfterRead(){
        if(input!=null){
            try {
                input.close();
            } catch (IOException ex) {
                System.err.println("Erro ao fechar o arquivo");
            }
            input=null;
        }
    }
    void closerAfterWrite(){
        if(output!=null){
            try {
                output.close();
            } catch (IOException ex) {
                System.err.println("Erro ao fechar o arquivo");
            }
            output=null;
        }
    }
    
}
