package controller;

import model.PersonReposity;
import java.util.ArrayList;
import model.Person;
import view.Menu;

public class Manager extends Menu<String>{

    ArrayList<Person> listPerson = new ArrayList<>();
    PersonReposity repo = new PersonReposity();
    static String[] choices = {"Find person info", "Copy text to new file"};

    public Manager() {
        super("======================= Management Person App ====================",choices,"Exit");
    }

    @Override
    public void execute(int n) {
        switch(n) { 
            case 1: { 
                repo.readFile("person.txt", listPerson);
                repo.findPerson(listPerson);
                break;
            }
            case 2: { 
                repo.writeFile();
                break;
            }
        }
    }

    
}
