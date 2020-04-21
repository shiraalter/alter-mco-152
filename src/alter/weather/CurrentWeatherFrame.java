package alter.weather;

import com.google.gson.Gson;
import javafx.beans.property.adapter.JavaBeanLongPropertyBuilder;

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
    private JLabel zipLabel;
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
        zipPanel.setLayout(new BoxLayout(zipPanel, BoxLayout.X_AXIS));
        zipField = new JTextField();
        zipLabel = new JLabel("Zip Code");
        zipPanel.add(zipLabel);

        zipPanel.add(zipField);
        zipPanel.setPreferredSize(new Dimension(50, 20));
        add(zipPanel, BorderLayout.NORTH);

        infoPanel = new JPanel();
       // infoPanel.setBorder(BorderFactory.createTitledBorder("Weather Data"));
        weatherData = new JTextArea();
        infoPanel.add(weatherData);
        add(infoPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        enterButton = new JButton("Enter");
        enterButton.addActionListener(actionEvent -> {
            try {
                getWeatherInfo();
            } catch (IOException e) {
                weatherData.setText("Error. Please try again");
            }
        });
        buttonPanel.add(enterButton);
        clearButton = new JButton("Clear Data");
        clearButton.addActionListener(actionEvent -> clearData());
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);



    }

    private void clearData() {
        weatherData.setText("");
    }

    private void getWeatherInfo() throws IOException {
            GetCurrentWeather weatherInfo = new GetCurrentWeather();
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?zip="+ zipField.getText() + ",US&appid=f0294b71a48b8c8a985f8f63afeaba95&units=imperial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Gson gson = new Gson();
            CurrentWeather currentWeather = gson.fromJson(reader, CurrentWeather.class);

            weatherData.setText("Location: " + currentWeather.name + "\nCurrent Temperature: " + currentWeather.main.temp +" degrees "
                                    + "\nCurrent Weather: " + currentWeather.weather[0].main + "\nDescription: " + currentWeather.weather[0].description);

    }


    public static void main(String[] args) throws IOException {
        new alter.weather.CurrentWeatherFrame().setVisible(true);
    }
}
