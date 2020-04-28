package alter.weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;


public class CurrentWeatherFrame extends JFrame {
    private JPanel zipPanel;
    private JPanel infoPanel;
    private JPanel buttonPanel;
    private JLabel zipLabel;
    private JTextField zipField;
    private JTextArea weatherData;

    private JButton enterButton;
    private JButton clearButton;
   private Retrofit retrofit;

    public CurrentWeatherFrame()  {
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
                e.printStackTrace();
            }

        });
        buttonPanel.add(enterButton);
        clearButton = new JButton("Clear Data");
        clearButton.addActionListener(actionEvent -> clearData());
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);


         retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void clearData() {
        weatherData.setText("");
    }

    private void getWeatherInfo() throws IOException {

        WeatherService service = retrofit.create(WeatherService.class);

        service.getZip(zipField.getText()).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                if(response.body() != null) {
                    weatherData.setText("Location: " + currentWeather.name + "\nCurrent Temperature: " + currentWeather.main.temp + " degrees "
                            + "\nCurrent Weather: " + currentWeather.weather[0].main + "\nDescription: " + currentWeather.weather[0].description);
                }
                else {
                    weatherData.setText("Error. Please try again");
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                clearData();
                t.printStackTrace();
            }
        });

    }


    public static void main(String[] args) throws IOException {
        new alter.weather.CurrentWeatherFrame().setVisible(true);
    }
}
