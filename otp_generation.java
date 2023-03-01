/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unidesk_login;
import java.util.Random;
/**
 *
 * @author Lester Greeks
 */
public class otp_generation {
    public static void main(String[] args){
        Random r=new Random();
        String num=String.valueOf(r.nextInt(100000,1000000));
        System.out.print(num);
    }
    
}
