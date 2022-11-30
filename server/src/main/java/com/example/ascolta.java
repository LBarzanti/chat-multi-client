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
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            inoltra i = new inoltra();
            for(;;)
            {
                String m=in.readLine();
                m=c.decrypt(m);
                Messaggio msg = deserializza(m);
                for (int j = 0; j < login.host.size(); j++) 
                {
                    if (msg.getDestinatario().equals(login.host.get(j))) 
                    {
                        i.invia(s, msg, c);
                    }
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ascolta: " + e.getLocalizedMessage());
        }
    }

    public Messaggio deserializza(String mess) throws Exception
    {
        XmlMapper xmlmapper2 = new XmlMapper();
        Messaggio m2 = xmlmapper2.readValue(mess, Messaggio.class);
        return m2;
    }
}
