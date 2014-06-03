import java.util.*;

/**
 * RWQueue.java
 *
 * A basic queue using a LinkedList and Java synchronization.
 *
 * @author Bruce Millard
 * @version 1.0 - June 7, 2003
 * Copyright 2000 by Bruce R. Millard
 * for CET 386 Summer 2003
 */

public final class RWQueue
{

   private LinkedList theQueue;

   public RWQueue()
   {
       theQueue = new LinkedList();
   }

   public synchronized void enqueue()
   {
         try {
             theQueue.add( Thread.currentThread() );
             wait();
         }
         catch (InterruptedException e)
         {
         }

   }

   public synchronized boolean dequeueReader()
   {
      boolean returnValue = false;

      if( theQueue.size() > 0 )
          if( theQueue.getFirst() instanceof  Reader )
          {
              ((Thread)theQueue.removeFirst()).interrupt();
              returnValue = true;
          }
      return returnValue;
   }

   public synchronized boolean dequeueWriter()
   {
      boolean returnValue = false;

      if( theQueue.size() > 0 )
          if( theQueue.getFirst() instanceof  Writer )
          {
              ((Thread)theQueue.removeFirst()).interrupt();
              returnValue = true;
          }
      return returnValue;
   }
}

