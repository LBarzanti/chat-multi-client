package com.example;

import java.io.DataOutputStream;
import java.net.Socket;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class inoltra
{
    public inoltra()
    {

    }

    public void invia(Socket s, Messaggio m, crittografia c)
    {
        try 
        {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeBytes(c.encrypt(serializza(m)) + "\n");
        } 
        catch (Exception e) 
        {
            System.out.println("invia(): " + e.getMessage());
        }
    }

    public void broad(Messaggio m)
    {
        for (int i = 0; i < login.sockets.size(); i++) 
        {
            try 
            {
                DataOutputStream out = new DataOutputStream(login.sockets.get(i).getOutputStream());
                out.writeBytes(login.chiavi.get(i).encrypt(serializza(m)) + "\n");
            } 
            catch (Exception e) 
            {
                System.out.println("broad(): " + e.getLocalizedMessage());
            }
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

    public void sconosciuto(Socket s, crittografia c)
    {
        try 
        {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeBytes(c.encrypt("utente non trovato") + "\n");
        } 
        catch (Exception e) 
        {
            System.out.println("sconosciuto(): " + e.getLocalizedMessage());
        }
    }
}
