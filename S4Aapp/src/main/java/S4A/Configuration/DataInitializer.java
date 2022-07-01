package S4A.Configuration;

import S4A.Baggage.Service.BaggageService;
import S4A.Parsing.Entity.CCargo.CCargo;
import S4A.Cargo.Service.CargoService;
import S4A.Flight.Entity.Flight;
import S4A.Flight.Service.FlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Component
public class DataInitializer {

    private final FlightService flightService;
    private final CargoService cargoService;
    private final BaggageService baggageService;

    @Autowired
    public DataInitializer(CargoService cargoService, FlightService flightService, BaggageService baggageService) {
        this.cargoService = cargoService;
        this.flightService = flightService;
        this.baggageService = baggageService;
    }

    @PostConstruct
    public synchronized void init() {
        try (FileReader reader = new FileReader("flight.json")) {
            Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
            Type collectionType = new TypeToken<List<Flight>>(){}.getType();
            List<Flight> lcs = gson
                    .fromJson( reader , collectionType);
            lcs.forEach(flight -> flightService.create(flight));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader("cargo.json")) {

            Gson gson = new GsonBuilder().create();
            Type collectionType = new TypeToken<List<CCargo>>(){}.getType();
            List<CCargo> lcs = gson
                    .fromJson( reader , collectionType);
            for (CCargo c:
                 lcs) {
                c.getCargo(flightService).forEach((cargo -> cargoService.create(cargo)));
                c.getBaggage(flightService).forEach((baggage -> baggageService.create(baggage)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
