/**
 * Database.java
 *
 * This class contains the methods the readers and writers will use
 * to coordinate access to the database. Access is coordinated using semaphores.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Database
{  
   public Database() {
      readerCount = 0;
      
      mutex = new Semaphore(1);
      db = new Semaphore(1);
   }

   // readers and writers will call this to nap
   public static void napping() {
     int sleepTime = (int) (NAP_TIME * Math.random() );
     try { Thread.sleep(sleepTime*1000); }
     catch(InterruptedException e) {}
   }


   public int startRead() { 
      mutex.P();
      
      ++readerCount;

      // if I am the first reader tell all others
      // that the database is being read
      if (readerCount == 1)
         db.P();
         
      mutex.V();
     
      return readerCount;
   }

   public int endRead() {
      mutex.P();
      
      --readerCount;

      // if I am the last reader tell all others
      // that the database is no longer being read
      if (readerCount == 0)
         db.V();;
      
      mutex.V();

      return readerCount;
   }

   public void startWrite() {
      db.P();
   }

   public void endWrite() {
      db.V();
   }

   // the number of active readers
   private int readerCount;

   Semaphore mutex;  // controls access to readerCount
   Semaphore db;     // controls access to the database
    
   private static final int NAP_TIME = 15;
}
