package TrafficSim;

public class Motorbike extends Car {
    //constructor
    public Motorbike(int id){
        super(id);
        this.id = "Motorbike" + id;
        this.length = Car.CAR_LENGTH / 2;
    }

    public Motorbike(int id, int length) {
        super(id, length);
        this.id = "Motorbike" + id;
        this.length *= 0.5;
    }

    //to string
    @Override
    public String toString() {
        String result = super.toString();
        return result.replace("Car", "Motorbike");
    }
}
