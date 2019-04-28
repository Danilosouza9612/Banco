/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Danilo
 */
public class MascaraString {
    private String pattern;
    public MascaraString(String pattern){
        this.pattern=pattern;
    }
    public String mascara(String value){
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return value;
        }
    }
}
