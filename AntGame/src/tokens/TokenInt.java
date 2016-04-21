/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * @author oliverthomas
 */
public class TokenInt extends Token {
    int num;
    
    public TokenInt(int i) {
        this.num = i;
    }
    
    public TokenInt(String i){
        String stringToInt = i.trim();
        Integer j = new Integer(stringToInt);
        this.num = j;
    }

    public int getNum() {
        return num;
    }
    @Override
    public String toString() {
        return new String(""+num);
    }
}
