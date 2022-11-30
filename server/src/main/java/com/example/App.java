package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try 
        {
            ServerSocket ss = new ServerSocket(25565);
            for(int i=1; i>1; i++)
            {
                Socket s = ss.accept();
                System.out.println("connessione effettuata");
                login l = new login(s);
                l.start();
            }
            ss.close();
        } 
        catch (Exception e) 
        {
            System.out.println("main: " + e.getLocalizedMessage());
        }
    }
}
