package Classwork;

//An interface defines a contract between you and the complier
//If you implement an interface, you are obligated to implement the methods of the interface
interface ISpeaking{
	void speak();
}

interface ILicensable{
	License getLicense();
}

/*
 * An abstract class cannot be instantiated. Any concrete derived class must implement
 * any and all abstract methods of the base class or themselves be
 * declared abstract. Abstract classes provide a useful method to factor out common code.
 */
abstract class Pet implements ISpeaking{
	protected String name;
	
	protected Pet(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

class Bird implements ISpeaking{
	@Override
	public void speak() {
		System.out.println("tweet");
	}
	
	public void fly() {
		System.out.println("flap flap");
	}
}

class Person implements ISpeaking{
	@Override
	public void speak() {
		System.out.println("Who took the cookie from the cookie jar?");
	}
}

class License{
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}
		
		return true;
	}
}

class Dog extends Pet implements ILicensable{
	private License license;
	
	public Dog(String name, License license) {
		super(name);
		this.license = license;
	}
	
	@Override
	public void speak() {
		System.out.println("Bark bark");
	}
	
	@Override
	public License getLicense() {
		return license;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}
		
		Dog other = (Dog) obj;
		return name.equals(other.name) && license.equals(other.license);
	}
	
}

class Retriever extends Dog{
	public Retriever(String name, License license) {
		super(name, license);
	}
	
	@Override
	public void speak() {
		System.out.println("woof");
	}
	
	public Bird retrieve() {
		return new Bird();
	}
}

class Cat extends Pet{
	
	public Cat(String name) {
		super(name);
	}
	
	@Override
	public void speak() {
		System.out.println("meow");
	}
	
}

public class ISpeakingDriver{
	public static void main(String args[]) {
		//ISpeaking b = new ISpeaking(); Can't instantiate an interface
		
		ISpeaking b = new Bird();
		b.speak();
		
		b = new Person();
		b.speak();
		
		Dog d = new Dog("Ubu", new License());
		System.out.print(d.getName() + " says ");
		d.speak();
		
		b = d;
		b.speak();
		
		Retriever r = new Retriever("Fido", new License());
		r.speak();
		
		r.retrieve().speak();
		
		d = r;
		d.speak();
		
		ISpeaking s = r;
		s.speak();
		
		d = (Dog) s;
		d.speak();
		
		/*
		Bird c = (Bird) s;
		c.fly();
		*/
		
		Pet p;
		p = new Cat("Rex");
		p.speak();
		System.out.println(p.getName());
		
		System.out.println(p);
		System.out.println(p.getClass());
		System.out.println(p.getClass().getName());
		
		
		System.out.println(d.equals(d));
		System.out.println(d.equals(p));
	}
}
