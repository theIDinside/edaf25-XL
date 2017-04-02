# Questions for first design meeting

1.  Vilka klasser bör finnas för att representera ett kalkylark?
XLSheet
XLCell (??)
2.  En ruta i kalkylarket skall kunna innehalla en text eller ett uttryck. Hur modellerar man detta?

3.  Hur skall man hantera uppdragsgivarens krav pa minnesresurser?

4.  Vilka klasser skall vara observatörer och vilka skall observeras?

5.  Vilket paket och vilken klass skall halla reda pa vad som är ”Current slot”?

6.  Vilken funktionalitet är redan färdig och hur fungerar den? Titta p ̊a klasserna i view -paketet och testkör.

7.  Det kan inträffa ett antal olika fel när man försöker ändra innehållet i ett kalkylark. Då skall undantag kastas. Var skall dessa undantag fangas och hanteras? 
    
8.  Vilken klass används för att representera en adress i ett uttryck?<br>
Variable används som address, lite synd att det inte finns (tillåts) en getter av Strängvärdet som innehåller addressen.
Om det hade funnits det, hade man kunnat utnyttja det i en HashMap, som så att ```HashMap<Variable, XLCell> ```

9.  När  ett  uttryck  som  bestar  av  en  adress  skall  beräknas  används  gränssnittet Environment. 
    Vilken  klass  skall  implementera  gränssnittet?  Varför  använder  man  inte  klassnamnet  istället  för gränssnittet?
    
10. Om  ett  uttryck  i  kalkylarket  refererar  till  sig  själv,  direkt  eller  indirekt,  så kommer  det  att  bli
    bekymmer vid beräkningen av uttryckets värde. Föresl något sätt att upptäcka sadana cirkulära beroenden! 
    Det  finns  en  elegant  lösning  med  hjälp  av  strategimönstret  som  du far  chansen  att upptäcka. 
    Om du inte hittar den sa kommer handledaren att avslöja den.
    
    "Another oft made specialization is the removal of the observerState member variable from the ConcreteObserver class. <br>
    In some cases, a concrete observer does not need to store the new state of the Subject, but rather, simply view the <br>
    state of the subject at the time the state is updated. For example, if an observer prints member variables of the<br>
    updated state to standard output, the observerState member variable can be removed. The removal of this member<br>
    variable removes the association between the ConcreteObserver and the State class.<br>"
