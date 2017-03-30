# edaf25-XL
Course code: EDAF25 - Objektorienterad Modellering och Design, Projekt XL

### What is MVC?
Model–View–Controller (MVC) is a software architectural pattern for implementing user interfaces on computers. It divides a given application into three interconnected parts in order to separate internal representations of information from the ways that information is presented to and accepted from the user.[1][2] The MVC design pattern decouples these major components allowing for efficient code reuse and parallel development.

### What is the Observer pattern?
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
    observers.forEach((ob) -> ob.update())
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
### Resources
Project description: http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/assignment2hbg.pdf

Reference manual: http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/manualhbg.pdf

Description (partial impl): http://fileadmin.cs.lth.se/cs/Education/EDAF25/project/overviewhbg.pdf

``` Java
public static void main(String args[]) {
  // placeholder for code
  return 0;
}
```
