import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody
{
   private Queue<Note> song;
   
   public Melody(Queue<Note> song)
   {
      if(song == null)
      {
         throw new IllegalArgumentException();
      }
      this.song = song;
   }
   
   public double getLength()
   {
      return 0.0;
   }
   
   public String toString()
   {
      Queue<Note> temp = new LinkedList<>();
      String noteString = "";
      while(!song.isEmpty())
      {
         Note note = this.song.remove();
         temp.add(note);
         noteString = noteString + note.toString() + "\n";
      }
      
      while(!temp.isEmpty())
      {
         Note tempNote = temp.remove();
         this.song.add(tempNote);
      }
      return noteString;
   }
   
   public void changeTempo(double tempo)
   {
      if(tempo <= 0)
      {
         throw new IllegalArgumentException();
      }
      Queue<Note> temp = new LinkedList<>();
      
      while(!this.song.isEmpty())
      {
         Note note = this.song.remove();
         double newTempo = tempo/note.getDuration();
         note.setDuration(newTempo);
         temp.add(note);
      }
      
      while(!temp.isEmpty())
      {
         Note tempNote = temp.remove();
         this.song.add(tempNote);
      }
   }
   
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
   
   public void append(Melody other)
   {
      if(other == null)
      {
         throw new IllegalArgumentException();
      }
      
      Queue<Note> temp = new LinkedList<>();
      while(!other.song.isEmpty())
      {
         Note note = other.song.remove();
         temp.add(note);
         this.song.add(note);
      }
      
      while(!temp.isEmpty())
      {
         Note tempNote = temp.remove();
         other.song.add(tempNote);
      }
   }
   
   public void play()
   {
   }
}