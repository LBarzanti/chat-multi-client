package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class login extends Thread
{
    private Socket s;
    public static ArrayList<String> host = new ArrayList<>();
    public static ArrayList<Socket> sockets = new ArrayList<>();
    public static ArrayList<crittografia> chiavi = new ArrayList<>();
    private BufferedReader in;
    private DataOutputStream out;

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
            boolean trovato = false;
            String nome = null;
            while (trovato) 
            {
                trovato = false;
                out.writeBytes(c.encrypt("inserire il nome utente") + "\n");
                nome = c.decrypt(in.readLine());
                for (int i = 0; i < host.size(); i++) 
                {
                    if (nome.equals(host.get(i))) 
                    {
                        out.writeBytes(c.encrypt("il nome scelto è già in uso") + "\n");
                        trovato=true;
                    }
                }
            }
            host.add(nome);
            sockets.add(s);
            chiavi.add(c);
            out.writeBytes("logged\n");
            for (int i = 0; i < host.size(); i++) 
            {
                out.writeBytes(c.encrypt(host.get(i)) + "\n");
            }
            out.writeBytes(c.encrypt("fine") + "\n");
            ascolta a = new ascolta(s, c);
            a.start();
        } 
        catch (Exception e) 
        {
            System.out.println("login: " + e.getLocalizedMessage());
        }
    }
}
