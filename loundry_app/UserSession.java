/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loundry_app;

/**
 *
 * @author Toriq
 */
public class UserSession {
    private static String username;
    private static String password;
    
    public static String getUsername(){
        return username;
    }
    public static void setUsername(String username){
       UserSession.username = username;
    }
    public static String getPass(){
        return password;
    }
    public static void setPass(String password){
        UserSession.password = password;
    }
}
