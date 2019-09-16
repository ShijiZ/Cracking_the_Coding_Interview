import java.util.LinkedList;

public class Q6AnimalQueue {
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal a){
        a.setOrder(order);
        order++;

        if (a instanceof Dog) dogs.addLast((Dog) a);
        else if (a instanceof Cat) cats.addLast((Cat) a);
    }

    public Animal dequeueAny(){
        if (dogs.size() == 0)
            return dequeueCat();
        if (cats.size() == 0)
            return dequeueDog();

        Dog dog = dogs.peek();
        Cat cat = cats.peek();

        if (dog.isOlderThan(cat))
            return dequeueDog();
        else
            return dequeueCat();
    }

    public Dog dequeueDog(){
        return dogs.poll();
    }

    public Cat dequeueCat(){
        return cats.poll();
    }

    public int size(){
        return dogs.size() + cats.size();
    }

    public static void main(String[] args) {
        Q6AnimalQueue animals = new Q6AnimalQueue();
        animals.enqueue(new Cat("Callie"));
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Dog("Fido"));
        animals.enqueue(new Dog("Dora"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Dexter"));
        animals.enqueue(new Dog("Dobo"));
        animals.enqueue(new Cat("Copa"));

        System.out.println(animals.dequeueAny().name);
        System.out.println(animals.dequeueAny().name);
        System.out.println(animals.dequeueAny().name);

        animals.enqueue(new Dog("Dapa"));
        animals.enqueue(new Cat("Kilo"));

        while (animals.size() != 0) {
            System.out.println(animals.dequeueAny().name);
        }
    }
}

abstract class Animal{
    int order;
    protected String name;

    public Animal(String name){
        this.name = name;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    /* Compare orders of animals to return the older item. */
    public boolean isOlderThan(Animal a){
        return this.order < a.order;
    }
}

class Dog extends Animal{
    public Dog(String n){
        super(n);
    }
}

class Cat extends Animal{
    public Cat(String n){
        super(n);
    }
}

