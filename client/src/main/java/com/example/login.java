package com.example;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class login extends Thread
{
    
    private Socket s;
    private BufferedReader in;
    private DataOutputStream out;
    private BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
    public static String nome;

    public login(Socket s)
    {
        this.s = s;
    }

    public void run()
    {
        try
        {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());
            crittografia c = new crittografia(s);
            c.init();
            boolean logged = false;
            while(!logged)
            {
                String m = in.readLine();
                m = c.decrypt(m);
                if (m.equals("logged")) 
                {
                    logged = true;
                }
                else
                {
                    System.out.println(m);
                    nome = tastiera.readLine();
                    out.writeBytes(c.encrypt(nome) + "\n");
                }
            }
            printList(c);
            ascolta a = new ascolta(s, c);
            invia i = new invia(s, c);
            a.start();
            i.start();
            a.join();
        }
        catch(Exception e)
        {
            System.out.println("login: " + e.getLocalizedMessage());
        }
    }
    
    public void printList(crittografia c)
    {
        try
        {
            boolean a = false;
            System.out.println("utenti connessi: ");
            while (!a) 
            {
                String utente=c.decrypt(in.readLine());
                if (utente.equals("fine")) 
                {
                    a = true;
                }
                else
                {
                    System.out.println(utente);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("printList(): " + e.getLocalizedMessage());
        }
    }
}
