package com.java.mytest;

import java.util.HashSet;
import java.util.Iterator;

public class CollectionTest {
   // Statics
   public static void main( String [] args ) {
      System.out.println( "Collection Test" );
      // Create a collection
      HashSet collection = new HashSet();
      // Adding
      String dog1 = "Max", dog2 = "Bailey", dog3 = "Harriet";
      collection.add( dog1 );
      collection.add( dog2 );
      collection.add( dog3 );
      // Sizing
      System.out.println( "Collection created" + 
        ", size=" + collection.size() + 
        ", isEmpty=" + collection.isEmpty() );
      // Containment
      System.out.println( "Collection contains " + dog3 + 
         ": " + collection.contains( dog3 ) );
      // Iteration. Iterator supports hasNext, next, remove
      System.out.println( "Collection iteration (unsorted):" );
      Iterator iterator = collection.iterator();
      while ( iterator.hasNext() ) 
         System.out.println( "   " + iterator.next() );
      // Removing
      collection.remove( dog1 );
      collection.clear();
   }
}