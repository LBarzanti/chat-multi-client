package com.example;

import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try
        {
            Socket s = new Socket("localhost", 25565);
            System.out.println("connessione effettuata");
            login l = new login(s);
            l.start();
        }
        catch(Exception e)
        {
            System.out.println("main: " + e.getMessage());
        }
    }
}
