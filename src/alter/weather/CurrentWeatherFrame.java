package alter.weather;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrentWeatherFrame extends JFrame {
    private JPanel zipPanel;
    private JPanel infoPanel;
    private JPanel buttonPanel;

    private JTextField zipField;
    private JTextArea weatherData;

    private JButton enterButton;
    private JButton clearButton;

    public CurrentWeatherFrame() throws IOException {
        setLayout(new BorderLayout());
        setSize(500, 300);
        setTitle("Local Weather Data");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        zipPanel = new JPanel();
        zipField = new JTextField();
        zipPanel.add(zipField);
        add(zipPanel, BorderLayout.NORTH);

        infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("Weather Data"));
        weatherData = new JTextArea();
        infoPanel.add(weatherData);
        add(infoPanel, BorderLayout.CENTER);


        enterButton = new JButton("Enter");
        enterButton.addActionListener(actionEvent -> {
            try {
                getWeatherInfo();
            } catch (IOException e) {
                weatherData.setText("Error. Please try again");
            }
        });
        enterButton.add(buttonPanel);

        clearButton = new JButton("Clear Data");
        clearButton.addActionListener(actionEvent -> clearData());
        clearButton.add(buttonPanel);
        add(buttonPanel, BorderLayout.SOUTH);



    }

    private void clearData() {
        weatherData.setText("");
    }

    private void getWeatherInfo() throws IOException {
            GetCurrentWeather weatherInfo = new GetCurrentWeather();
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?zip="+ zipField.getText() + ",US&appid=f0294b71a48b8c8a985f8f63afeaba95");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Gson gson = new Gson();
            CurrentWeather currentWeather = gson.fromJson(reader, CurrentWeather.class);

            weatherData.setText("Location: " + currentWeather.name + "\nCurrent Temperature: " + currentWeather.main.temp +" degrees "
                                    + "\nCurrent Weather: " + currentWeather.weather[0].main + "\nDescription: " + currentWeather.weather[0].description);

    }


    public static void main(String[] args) throws IOException {
        new CurrentWeatherFrame().setVisible(true);
    }
}
