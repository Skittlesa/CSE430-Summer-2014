/**
 * Semaphore.java
 *
 * A basic counting semaphore using Java synchronization.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */
 
public final class Semaphore
{
   public Semaphore() {
      value = 0;
   }
   
   public Semaphore(int v) {
      value = v;
   }
   
   public synchronized void P() {
      while (value <= 0) {
         try {
            wait();
         }
         catch (InterruptedException e) { }
      }
      
      value --;
   }
   
   public synchronized void V() {
      ++value;
      
      notify();
   }
   
   private int value;
}
     
