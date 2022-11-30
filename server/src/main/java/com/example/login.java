package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class login extends Thread
{
    private Socket s;
    private static ArrayList<String> host = new ArrayList<>();
    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static ArrayList<crittografia> chiavi = new ArrayList<>();
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
        } 
        catch (Exception e) 
        {
            System.out.println("login: " + e.getLocalizedMessage());
        }
    }
}
