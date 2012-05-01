/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraries;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author gautam
 */
public class Validation {

    public static boolean isValidEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    public static boolean isValidPassword(String password){
        try {
            if(password.length() < 6){
                return false;
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
