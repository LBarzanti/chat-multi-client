package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
            out.writeBytes();
        } 
        catch (Exception e) 
        {
            System.out.println("invia(): " + e.getMessage());
        }
    }

    public void broad(Messaggio m)
    {

    }
}
