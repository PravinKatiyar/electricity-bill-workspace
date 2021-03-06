package com.cg.springjwt.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;



@Configuration 
public class MailConfig {
	
	@Autowired
    private Environment env;

    @Bean
    public JavaMailSender javaMailService() {
    	
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
     
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
     
           
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            
        Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp"); 
	
		mailSender.setJavaMailProperties(props);
        
        return mailSender;
    }


}
