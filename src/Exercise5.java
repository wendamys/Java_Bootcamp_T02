import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Exercise5 {
        public static void main(String[] args) {
            Instant startProgram = Instant.now();
            List<Animal> myPets = inputUser();
            List<Thread> threads = new ArrayList<>();
            for (Animal s: myPets){
                Thread t = new Thread(() -> {
                    Instant walkStart = Instant.now();
                    double startTime = Duration.between(startProgram, walkStart).toMillis() / 1000.0;
                    s.goToWalk();
                    Instant walkEnd = Instant.now();
                    double endTime = Duration.between(startProgram, walkEnd).toMillis() / 1000.0;
                    System.out.println(s.getClass().getSimpleName()
                            + " name = "
                            + s.getName()
                            + ", age = " + s.getAge() + ", start time = "
                            + String.format(Locale.US, "%.2f", startTime) + ", end time = "
                            + String.format(Locale.US,"%.2f", endTime));
                });
                threads.add(t);
                t.start();
            }
            try {
                for (Thread t : threads) {
                    t.join();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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

        public abstract static class Animal {
            private final String name;
            private final int age;

            public Animal(String name, int age){
                this.name = name;
                this.age = age;
            }

            public String getName() { return name; }
            public int getAge() { return age; }
            public double goToWalk() { return 1; }
        }

        public static class Dog extends Animal {
            public Dog(String name, int age) { super(name, age); }
            @Override
            public double goToWalk() {
                double time = (getAge() * 0.5);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (time * 1000));
                } catch (Exception exception) {
                    Thread.currentThread().interrupt();
                }
                return time;
            }
        }

        public static class Cat extends Animal {
            public Cat(String name, int age) {
                super(name, age);
            }
            @Override
            public double goToWalk() {
                double time = (getAge() * 0.25);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (time * 1000));
                } catch (Exception exception) {
                    Thread.currentThread().interrupt();
                }
                return time;
            }
        }
    }
