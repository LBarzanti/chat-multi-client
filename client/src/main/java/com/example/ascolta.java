package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ascolta extends Thread
{
    Socket s;
    crittografia c;

    public ascolta(Socket s, crittografia c)
    {
        this.s = s;
        this.c = c;
    }

    public void run()
    {
        try 
        {
            for(;;)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                Messaggio m = deserializza(c.decrypt(in.readLine()));
                if (m.getDestinatario().equals(login.nome)) 
                {
                    System.out.println(m.getMittente() + ": " + m.getTesto_mess());
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ascolta: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public Messaggio deserializza(String mess)
    {
        try 
        {
            XmlMapper xmlmapper2 = new XmlMapper();
            Messaggio m2 = xmlmapper2.readValue(mess, Messaggio.class);
            return m2;
        } 
        catch (Exception e) 
        {
            System.out.println("deserializza: " + e.getLocalizedMessage());
            return null;
        }
        
    }
}
