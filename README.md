# edaf25-XL
Course code: EDAF25 - Objektorienterad Modellering och Design, Projekt XL

### What is MVC?
Model–View–Controller (MVC) is a software architectural pattern for implementing user interfaces on computers. It divides a given application into three interconnected parts in order to separate internal representations of information from the ways that information is presented to and accepted from the user.[1][2] The MVC design pattern decouples these major components allowing for efficient code reuse and parallel development.

### What is the Observer pattern?
According to the classic definition coined by the GoF, the intent of the Observer Pattern is to

    "Define a one-to-many dependency between objects so that when one object changes state, all
    its dependents are notified and updated automatically." 

![alt text](https://dzone.com/storage/temp/887275-classic-observer-pattern-class-diagram.jpg "Classic observer pattern")

(example impl.) 
```Java
// Subject (Observee)
public class Subject {
  
  private List<Observer> observers;
  private String observedValue;
  Subject() {
    observers = new ArrayList<>();
  }
  
  public void changeValue(String nv) {
    observedValue = nv;
    notifyObservers();
  }
  
  public void notifyObservers() {
    observers.forEach(update());
  }
  
  public void register(Observer obs) {
    observers.add(obs);  
  }
  
  public String getState() {
    return observedValue;
  }

}

// abstract class to be extended and used as the Observer
public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}

public class StringObserver {
  Subject s;
  StringObserver() {
    this.s.register(this);
  }
  
  public void update() {
    System.out.println("String has been set to a new value: " + s.getState();
  }
}
``` 

Basically, the observer type, registers a type of type Subject, which will notify by calling update(), from within notifyObservers(). This is more or less the essence of the Observer pattern. All Java GUI components follow this design pattern with the addActionEventListeners (or maybe it's called addActionListeners() who knows), or the GUI component 
gets registered with an ActionEventListener. This is usually now a days done with just calling a lambdafunction, like 
``` Java
addActionEventListener((actevt) -> {
  // perform some task here
  // update some field in the GUI here
  // return void or some value here
});
```
One or more views registers with the model, as observers, through addObserver(...), according to the **Open-Closed-Principle**. 

### Resources
Project description: http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/assignment2hbg.pdf
Reference manual: http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/manualhbg.pdf
Description (partial impl): http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/overviewhbg.pdf

- Lectures on MVC: http://fileadmin.cs.lth.se/cs/Education/EDAF25/lectures/F6.pdf
- Lecture on MVC (begins at 1:03:35): https://www.youtube.com/watch?v=MIZrQ2smM30
  @(1:06:15) _"The view is basically glorified HTML with placeholders"_  
  What he is saying here, in this context, is that the view-part of the MVC, is constructed with HTML, with tags in them, and in that way create what is called templates. This is done differently depending on what language you are programming in, JSP-HTML has <% %> in them for example, 
  (there are different versions of the tags used in JSP), where you can place java-code for example, get variables from the model etc.
- The Observer Pattern & Publish-Subscribe architecture: https://dzone.com/articles/the-observer-pattern-using-modern-java


This is a very useful lecture. Watch it.
``` Java
public static void main(String args[]) {
  // placeholder for code
  return 0;
}
```

- Observer design pattern
- Open Closed Principle
- Single Responsibility Principle

"Another oft made specialization is the removal of the observerState member variable from the ConcreteObserver class. <br>
In some cases, a concrete observer does not need to store the new state of the Subject, but rather, simply view the <br>
state of the subject at the time the state is updated. For example, if an observer prints member variables of the updated <br> 
state to standard output, the observerState member variable can be removed. The removal of this member variable removes <br>
the association between the ConcreteObserver and the State class." <br>

In this case, the ConcreteObserver would be the classes in the view - so for example, a SlotLabel, would not need
to have a reference to the state, (i.e the data/calculation). Perhaps.

