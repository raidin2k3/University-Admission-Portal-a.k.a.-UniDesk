/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unidesk_login;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.sql.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Lester Greeks
 */
public class JavaEmailSender {
    public static void main(String[] args){
        Random r=new Random();
        String num=String.valueOf(r.nextInt(100000,1000000));
        String fromEmail = "pranshumishra2003@yahoo.com";//user.getFromEmail(); //requires valid gmail id
        String password = "tpouyiugqryfuokj";//user.getPassword(); // correct password for gmail id
        String toEmail = "noidafam@gmail.com"; // can be any email id 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.yahoo.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        try{
            Session session = Session.getDefaultInstance(props,
            new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail,password);
                }
            });
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject("JKLU Offer Letter");
//            msg.setText("Your Email Verification OTP is: "+num);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart msgprt = new MimeBodyPart();
            msgprt.setText("Congrats! You've been selected for B.Tech./BBA/M.Tech/MBA programme at JKLU\n"
                    + "Please find the attached offer letter\n"
                    +"Application No. "+num+"/JKLUAD22");
            MimeBodyPart msgprt2 = new MimeBodyPart();
            String filename = "C:\\Users\\Lester Greeks\\Documents\\Offer Letter.pdf";
            DataSource source = new FileDataSource(filename);
            msgprt2.setDataHandler(new DataHandler(source));
            msgprt2.setFileName(filename);
            multipart.addBodyPart(msgprt);
            multipart.addBodyPart(msgprt2);
            msg.setContent(multipart);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            System.out.println("Message is ready");
            Transport trans=session.getTransport("smtp");
            trans.connect("smtp.mail.yahoo.com",fromEmail,password);
            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}