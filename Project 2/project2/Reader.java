/**
 * Reader.java
 *
 * A reader to the database.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Reader extends Thread
{
   public Reader(int r, Database db)
   {
      readerNum = r;
      server = db;
   }

   public void run()
   {
   int c;

     while (true)
      {
       System.out.println("reader " + readerNum + " is sleeping.");
       Database.napping();

       System.out.println("reader " + readerNum + " wants to read.");
       c = server.startRead();
   
       // you have access to read from the database
       System.out.println("reader " + readerNum + " is reading. Count = " + c);
       Database.napping();
       
       c = server.endRead();
       System.out.println("reader " + readerNum + " is done reading. Count = " + c);
      }
   }
   
   private Database	server;
   private int       readerNum;
}
