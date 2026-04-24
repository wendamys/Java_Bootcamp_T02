import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) {


    }

    public static void inputUser() {
        Scanner scanner = new Scanner(System.in);
        List<String> pets = new ArrayList<>();
        int num;
        while (true) {
            try {
                String userInput = scanner.nextLine();
                num = Integer.parseInt(userInput);
                break;
            } catch(Exception e){
                System.out.println("Could not parse a number. Please, try again");
            }
        }

        while (pets.size() < num) {
            String userInputAnimal = scanner.nextLine();
            if (userInputAnimal.equals("dog") || userInputAnimal.equals("cat")) {
                try {
                    int userInputAge = scanner.nextInt();
                    if (userInputAge <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                    } else {
                        if (userInputAnimal.equals("dog")){
                            super.
                        }
                        pets.add()
                    }

                } catch (Exception e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            } else {
                System.out.println("Incorrect input. Unsupported pet type");
            }

        }

    }


    public abstract class Animal {
        private String name;
        private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}


    public class Dog extends Animal{
        public Dog(String name, int age) {
            super(name, age);
    }

    public String toString(){
        return "Dog name = " + getName() + ", age = " + getAge();
        }
    }

    public class Cat extends Animal{
        public Cat(String name, int age){
            super(name, age);
        }

    public String toString(){
        return "Cat name = " + getName() + ", age = " + getAge();
        }
    }
}


