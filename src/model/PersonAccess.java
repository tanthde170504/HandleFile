package model;

import Controller.Validation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Person;

public class PersonAccess {

    ArrayList<Person> list = new ArrayList<>();
    private static PersonAccess instance = null;
    Validation vl = new Validation();
    Scanner sc = new Scanner(System.in);

    public static PersonAccess Intance() {
        if (instance == null) {
            synchronized (Person.class) {
                if (instance == null) {
                    instance = new PersonAccess();
                }
            }
        }
        return instance;
    }

    public Person createPerson(String name, String add, double salary) {
        String vName = vl.getString(name);
        String vAdd = vl.getString(add);
        double vSalary = vl.getDouble(salary + "");
        Person p = new Person(vName, vAdd, vSalary);
        return p;
    }

    public void readFile(String path, ArrayList<Person> listPerson) {
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String arr[] = line.split(";");
                int count = 0;
                if (arr.length == 3) {
                    Person p = createPerson(arr[0], arr[1], Double.parseDouble(arr[2]));
                    for (Person person : listPerson) {
                        if (p.equals(person)) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        listPerson.add(p);
                    }
                }
                line = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean saveFile() {
        System.out.print("Please enter new location:");
        String path = sc.nextLine();
        try {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for (Person p : list) {
                String line = p.getName() + ";" + p.getAddress() + ";" + p.getSalary();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            osw.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void displayAll(ArrayList<Person> listPerson) {
        System.out.printf("%-30s|%-35s|%-30s\n", "Name", "Address", "Salary");
        for (Person p : listPerson) {
            System.out.printf("%-30s%-35s%-15f\n", p.getName(), p.getAddress(), p.getSalary());
        }
        System.out.println("==========================================================");
        System.out.println("");
    }

    public void findPerson(ArrayList<Person> listPerson) {
        double salary = vl.checkDouble("Input the salary of person you want to find:");
        ArrayList<Person> newList = new ArrayList<>();
        Person pMax = listPerson.get(0);
        Person pMin = listPerson.get(0);

        for (Person p : listPerson) {

            if (p.getSalary() > salary) {
                newList.add(p);
            }
            if (pMax.getSalary() < p.getSalary()) {
                pMax = p;
            }
            if (pMin.getSalary() > p.getSalary()) {
                pMin = p;
            }
        }
        System.out.println("------------- Result ----------");
        displayAll(newList);
        System.out.println("");
        System.out.println("");
        System.out.println("Max :" + pMax.getName());
        System.out.println("Min :" + pMin.getName());
        list.clear();
        list.addAll(newList);

    }
}
