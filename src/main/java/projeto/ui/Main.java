package projeto.ui;

import projeto.repository.AnimalCsvRepository;
import projeto.service.ClinicaService;

public class Main {

    public static void main(String[] args) {

        ClinicaService service = new ClinicaService();

        // CARREGAR CSV
        AnimalCsvRepository repository =
                new AnimalCsvRepository();

        repository.carregar(service.animais);
        System.out.println(service.animais.size());

        // MENU
        Menu menu = new Menu(service);

        menu.iniciar();
    }
}