package alter.earthquakes;

public class CurrentEarthquakes{
    Feature features[];

    class Properties {
        double mag;
        String place;
        long time;

        @Override
        public String toString() {
            return "Properties{" +
                    "mag=" + mag +
                    ", place='" + place + '\'' +
                    ", time=" + time +
                    '}';
        }
    }

    class Feature {
        Properties properties;
    }

}