import java.util.*;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) {
        List<Animal> myPets = inputUser();
        printPets(myPets);
    }

    public static void printPets(List<Animal> pets){
        String result = pets.stream()
                .sorted(Comparator.comparing(a -> a instanceof Omnivore))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(result);
    }

    public static List<Animal> inputUser() {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();
        int targetSize = readInt(scanner);
        while (pets.size() < targetSize) {
            String type = scanner.nextLine().toLowerCase();
            if (List.of("dog", "cat", "hamster", "guinea").contains(type)){
                String name = scanner.nextLine();
                int age = readInt(scanner);
                if (age <= 0) {
                    System.out.println("Incorrect input. Age <= 0");
                    targetSize--;
                    continue;
                }
                pets.add(switch (type){
                    case "dog" -> new Dog(name, age);
                    case "hamster" -> new Hamster(name, age);
                    case "guinea" -> new GuineaPig(name, age);
                    default -> new Cat(name, age);
                });
            } else {
                System.out.println("Incorrect input. Unsupported pet type");
                targetSize--;
            }
        }
        return pets;
    }

    public static int readInt(Scanner scanner){
        while (true) {
            try {
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch(Exception e){
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    interface Harbinger {String chill();}
    interface Omnivore {String hunt();}

    public abstract static class Animal {
        private final String name;
        private final int age;

        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    public static class Dog extends Animal implements Omnivore {
        public Dog(String name, int age) { super(name, age); }
        public String hunt() { return "I can hunt for robbers"; }
        @Override
        public String toString() { return "Dog name = " + getName() + ", age = " + getAge() + ". " + hunt(); }
    }

    public static class Cat extends Animal implements Omnivore {
        public Cat(String name, int age) { super(name, age); }
        public String hunt() { return "I can hunt for mice"; }
        @Override
        public String toString() { return "Cat name = " + getName() + ", age = " + getAge() + ". " + hunt(); }
    }

    public static class Hamster extends Animal implements Harbinger {
        public Hamster(String name, int age) { super(name, age); }
        public String chill() { return "I can chill for 8 hours"; }
        @Override
        public String toString() { return "Hamster name = " + getName() + ", age = " + getAge() + ". " + chill(); }
    }

    public static class GuineaPig extends Animal implements Harbinger {
        public GuineaPig(String name, int age) { super(name, age); }
        public String chill(){ return "I can chill for 12 hours"; }
        @Override
        public String toString(){ return "GuineaPig name = " + getName() + ", age = " + getAge() + ". " + chill(); }
    }
}
