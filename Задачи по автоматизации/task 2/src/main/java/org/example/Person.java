package org.example;


import java.util.*;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private int age;
    private String position;
    private int salary;
    private String ccy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public Person(){}
    public Person(Map<String, String> map) {
        this.name = map.get("Имя");
        this.age = Integer.parseInt(map.get("Возраст"));
        this.position = map.get("Должность");
        this.salary = Integer.parseInt(map.get("Зарплата").split("\\D+")[0]);
        this.ccy = map.get("Зарплата").split("[\\d\\s]+")[1];
    }

    public List<Map<String,String>> data() {
        List<Map<String, String>> data = List.of(
                Map.of(
                        "Имя", "Кирилл", "Возраст", "26",
                        "Должность", "Middle java dev", "Зарплата", "150000 руб"
                ),
                Map.of(
                        "Имя", "Виталий", "Возраст", "28",
                        "Должность", "Senior java automation QA", "Зарплата", "2000$"
                ),
                Map.of(
                        "Имя", "Александр", "Возраст", "31",
                        "Должность", "Junior functional tester", "Зарплата", "50000 руб"
                ),
                Map.of(
                        "Имя", "Дементий", "Возраст", "35",
                        "Должность", "Dev-ops", "Зарплата", "1500$"
                )
        );
        List<Person> persons = data.stream()
                .map(Person::new)
                .collect(Collectors.toList());  // или toList() для Java


        System.out.println("Зарплата в рублях: ");
        // вывести имена всех сотрудников, получающих зарплату в рублях.
        persons.stream()
                .filter(p -> "руб".equals(p.getCcy()))
                .map(Person::getName)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Имя чей возраст <30: ");
        //вывести имена всех сотрудников, младше 30.
        persons.stream()
                .filter(p -> p.getAge() < 30)
                .map(Person::getName)
                .forEach(System.out::println);
        System.out.println();

        // вывести средний возраст всех сотрудников.
        double avgAge = persons.stream()
                .mapToInt(Person::getAge)  // IntStream
                .average()                 // OptionalDouble
                .getAsDouble();            // double
        System.out.println("Средний возраст: \n" + avgAge);

        return data;
    }
}