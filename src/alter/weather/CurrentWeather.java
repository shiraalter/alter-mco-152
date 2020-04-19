package alter.weather;

public class CurrentWeather {
    //name of variable must match name in JSON

    String base;
    int visibility;
    Coord coord;
    String name;
    Main main;
    Weather weather[];

    class Coord{
        double lon;
        double lat;
    }
    class Main{
        double temp;
        double feels_like;

        //CHECK!!/////////////////////////
/*        @Override
        public String toString(){
            return temp + " degrees";*/

    }
    class Weather{
        int id;
        String main;
        String description;
        String icon;

        // CHECK!!//////////////////////////////
     /*   @Override
        public String toString() {
            return main + ": " + description;*/

    }
}
