package com.example;

import java.net.Socket;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class invia extends Thread
{
    Socket s;
    crittografia c;
    DataOutputStream out;
    BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

    public invia(Socket s, crittografia c)
    {
        this.s = s;
        this.c = c;
    }

    public void run()
    {
        try 
        {
            out = new DataOutputStream(s.getOutputStream());
            for(;;)
            {
                boolean lista;
                Messaggio msg = new Messaggio();
                msg.setDestinatario(tastiera.readLine());
                msg.setTesto_mess(tastiera.readLine());
                
                msg.setMittente(login.nome);
                out.writeBytes(c.encrypt(serializza(msg)) + "\n");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("invia: " + e.getLocalizedMessage());
        }
        
    }

    public String serializza(Messaggio m)
    {
        try 
        {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.writeValueAsString(m);
        } 
        catch (Exception e) 
        {
            System.out.println("serializza: " + e.getMessage());
            return null;
        }
    }
}
