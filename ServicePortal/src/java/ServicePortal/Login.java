/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServicePortal;

/**
 *
 * @author cb-raghu
 */
public class Login {
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public String address;

    public Login(String email, String firstName, String lastName, String password, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
    } 
    
}
