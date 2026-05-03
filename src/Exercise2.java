import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise2 {

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
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
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
                    if (userInputAge <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        num -= 1;
                        continue;
                    }
                    double userInputWt = scanner.nextDouble();
                    scanner.nextLine();
                    if (userInputWt <= 0) {
                        System.out.println("Incorrect input. Mass <= 0");
                    } else {
                        if (userInputAnimal.equalsIgnoreCase("dog")){
                            Dog dog = new Dog(userInputName, userInputAge, userInputWt);
                            pets.add(dog);
                        } else {
                            Cat cat = new Cat(userInputName, userInputAge, userInputWt);
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


    public abstract static class Animal {
        private String name;
        private int age;
        private double wt;

        public Animal(String name, int age, double wt
        ) {
            this.name = name;
            this.age = age;
            this.wt = wt;
        }

        public String getName(){
            return name;
        }

        public int getAge(){
            return age;
        }

        public double getWt() {return wt;}

        public double getFeedInfoKg() {
            return wt;
        }

    }


    public static class Dog extends Animal{
        public Dog(String name, int age, double wt) {
            super(name, age, wt);
        }

        public String toString(){
            return "Dog name = " + getName() + ", age = " + getAge() + ", mass = " + getWt() + ", feed = " + getFeedInfoKg();
        }

        public double getFeedInfoKg() {
            double value = this.getWt() * 0.3;
            return Math.round(value * 100.0) / 100.0;
        }
    }

    public static class Cat extends Animal{
        public Cat(String name, int age, double wt){
            super(name, age, wt);
        }

        public String toString(){
            return "Cat name = " + getName() + ", age = " + getAge() + ", mass = " + getWt() + ", feed = " + getFeedInfoKg();
        }

        public double getFeedInfoKg() {
            double value = this.getWt() * 0.1;
            return Math.round(value * 100.0) / 100.0;
        }
    }
}


