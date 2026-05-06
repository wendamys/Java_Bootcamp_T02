import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) {
        List<Animal> myPets = inputUser();
        Print(myPets);
    }

    public static void Print(List<Animal> pets){

        String result = pets.stream()
                .map(Animal::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(result);
    }

    public static List<Animal> inputUser() {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();
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
            if (userInputAnimal.equalsIgnoreCase("dog") || userInputAnimal.equalsIgnoreCase("cat")) {
                try {
                    String userInputName = scanner.nextLine();
                    int userInputAge = scanner.nextInt();
                    scanner.nextLine();
                    if (userInputAge <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        num -= 1;
                    } else {
                        if (userInputAnimal.equalsIgnoreCase("dog")){
                            Dog dog = new Dog(userInputName, userInputAge);
                            pets.add(dog);
                        } else {
                            Cat cat = new Cat(userInputName, userInputAge);
                            pets.add(cat);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            } else {
                System.out.println("Incorrect input. Unsupported pet type");
                num -= 1;
            }

        }
        return pets;
    }

    interface Harbilove {
        default String chill(String message){
            return message;
        }
    }

    interface Omnivore {
        default String hunt(String message){
           return message;

        }
    }


    public abstract static class Animal {
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


    public static class Dog extends Animal implements Omnivore {
        public Dog(String name, int age) {
            super(name, age);
        }

        public String hunt(){
            return "I can hunt for robbers";
        }

        public String toString(){
            return "Dog name = " + getName() + ", age = " + getAge() + " " + hunt();
        }
    }

    public static class Cat extends Animal implements Omnivore {
        public Cat(String name, int age){
            super(name, age);
        }

        public String hunt(){
            return "I can hunt for mice";
        }

        public String toString(){
            return "Cat name = " + getName() + ", age = " + getAge() + " " + hunt();
        }
    }

    public static class Hamster {
        private String name;
        private int age;

        public Hamster(String name, int age){
            this.name = name;
            this.age = age;
        }

    }
}



