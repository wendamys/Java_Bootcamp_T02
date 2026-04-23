public class Exercise1 {
    public static void main(String[] args) {

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
        return "Dog name = " + getName() + ", age = " + getAge()
        return null;
    }
}
}


