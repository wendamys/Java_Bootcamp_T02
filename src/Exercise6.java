import java.util.*;

public class Exercise6 {

    public static void main(String[] args) {
        List<Animal> myPets = inputUser();
        AnimalIterator iterator = new AnimalIterator(myPets);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static List<Animal> inputUser() {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();
        int targetSize = readInt(scanner);
        while (pets.size() < targetSize) {
            String type = scanner.nextLine().toLowerCase();
            if (List.of("dog", "cat").contains(type)){
                String name = scanner.nextLine();
                int age = readInt(scanner);
                if (age <= 0) {
                    System.out.println("Incorrect input. Age <= 0");
                    targetSize--;
                    continue;
                }
                pets.add(switch (type){
                    case "dog" -> new Dog(name, age);
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


    interface BaseIterator<T> {
        public T next();
        public boolean hasNext();
        public void reset();
    }

    static class AnimalIterator implements BaseIterator<Animal> {
        private final List<Animal> animals;
        private int currentIndex = 0;

        public AnimalIterator(List<Animal> animals){
            this.animals = animals;
        }

        @Override
        public Animal next() {
            Animal result = animals.get(currentIndex);
            currentIndex++;
            return result;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < animals.size();
        }

        @Override
        public void reset() {
            currentIndex = 0;
        }
    }

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

    public static class Dog extends Animal {
        public Dog(String name, int age) { super(name, age); }
        @Override
        public String toString() { return "Dog name = " + getName() + ", age = " + getAge(); }
    }

    public static class Cat extends Animal {
        public Cat(String name, int age) { super(name, age); }
        @Override
        public String toString() { return "Cat name = " + getName() + ", age = " + getAge(); }
    }
}
