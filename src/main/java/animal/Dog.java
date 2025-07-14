package animal;

public class Dog extends Animal {

    public Dog(String atributo) {
        super(atributo);
        System.out.println("Um cachorro foi criado");
    }

    public void animalSound() {
        super.animalSound();
        System.out.println("The dog says: bow wow: " + super.atributo);
    }

}