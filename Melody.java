// Nour G.
// Period 1
// Assignment 12
// This program edits and plays a song saved in a queue.
// Melody
// April 8, 2021

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody
{
   /**
   * This variable creates a queue for the song in the constructor
   *
   */
   private Queue<Note> song;
   
   /**
   * This constructor constructs a melody with a song and throws an exception if the song is null
   *
   * @param song notes in a queue that make up a song
   */
   public Melody(Queue<Note> song)
   {
      if(song == null)
      {
         throw new IllegalArgumentException();
      }
      this.song = song;
   }
   
   /**
   * This method returns the length of the full song including repeated sections
   *
   */
   public double getLength()
   {
      double length = 0.0;
      int end = 0;
      boolean loop = false;
      for(int i = 0; i < this.song.size(); i++)
      {
         Note note = this.song.remove();
         if(note.getPitch().equals("R"))
         {
            length = length;
         }
         else
         {
            double duration = note.getDuration();
            if(note.isRepeat() == true)
            {
               if(loop == true)
               {
                  loop = false;
                  end = end + 1;
               }
               else
               {
                  loop = true;
                  end = 0;
               }
            }
            
            if(loop == true || end == 1)
               {
                  length = length + 2 * duration;
                  end = 0;
               }
               
            else
            {
               length = length + duration;
            }
            
         }
         this.song.add(note);
      }
      return length;
   }
   
   /**
   * This method returns a string representation of the notes in the queue
   *
   */
   public String toString()
   {
      String noteString = "";
      for(int i = 0; i < this.song.size(); i++)
      {
         noteString = noteString + this.song.peek() + "\n";
         this.song.add(this.song.remove());
      }
      return noteString;
   }
   
   /**
   * This method alows the user to change the tempo of the song (slower/faster)
   *
   */
   public void changeTempo(double tempo)
   {
      if(tempo <= 0)
      {
         throw new IllegalArgumentException();
      }
      
      for(int i = 0; i < this.song.size(); i++)
      {
         Note note = this.song.remove();
         double newTempo = note.getDuration()/tempo;
         note.setDuration(newTempo);
         this.song.add(note);
      }
   }
   
   /**
   * This method reverses the order of the notes in the song
   *
   */
   public void reverse()
   {
      Stack<Note> tempStack = new Stack<>();
      while(!this.song.isEmpty())
      {
         Note note = this.song.remove();
         tempStack.push(note);
      }
      
      while(!tempStack.isEmpty())
      {
         Note tempNote = tempStack.pop();
         this.song.add(tempNote);
      }
   }
   
   /**
   * This method adds another melody onto the end of the existing song
   *
   * @param other the Melody being added to the end of the existing son
   */
   public void append(Melody other)
   {
      if(other == null)
      {
         throw new IllegalArgumentException();
      }
      
      for(int i = 0; i < other.song.size(); i++)
      {
         Note note = other.song.remove();
         this.song.add(note);
         other.song.add(note);
      }
   }
   
   /**
   * This method plays each note of the song includng repeated sections
   *
   */
   public void play()
   {
      int end = 0;
      boolean loop = false;
      Queue<Note> temp = new LinkedList<>();
      
      for(int i = 0; i < this.song.size(); i++)
      {
         Note note = this.song.remove();
         this.song.add(note);
         
         //if the note is true, the loop variable turns true, but if it reaches a second 
         //note that is true, the loop variable turns to false
         //end equals 1 at the last note of repeated section
         if(note.isRepeat() == true)
         {
            if(loop == true)
            {
               loop = false;
               end = end + 1;
            }
            
            else
            {
               loop = true;
               end = 0;
            }
         }
         
         //if notes are within repeated section, play them and add them to a temporary queue
         if(loop == true || end == 1)
         {
            note.play();
            temp.add(note);
            
            //if you reach the end of the loop, play the notes inside the temporary queue
            //and remove them from the temporary queue
            if(end == 1)
            {
               int size = temp.size();
               
               for(int j = 0; j < size; j++)
               {
                  Note repeat = temp.remove();
                  repeat.play();
               }
            }
            end = 0;
         }
         
         else
         {
            note.play();
         }

      }
   }
}