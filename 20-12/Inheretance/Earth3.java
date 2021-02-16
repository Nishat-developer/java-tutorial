/* Concept of overriding */

public class Earth3 extends Planet {
    public static void hide() {
        System.out.println("The hide method in Earth.");
    }
    public void override() {
        System.out.println("The override method in Earth.");
    }

    public static void main(String[] args) {
        Earth3 myEarth = new Earth3();
        Planet myPlanet = (Planet)myEarth;
        myPlanet.hide();                   
        myPlanet.override();
    }
}
