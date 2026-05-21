
package projeto;

import org.junit.jupiter.api.Test;
import projeto.model.*;
import projeto.service.ClinicaService;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicaServiceTest {

    @Test
    void adicionarAnimal() {
        ClinicaService service = new ClinicaService();

        Proprietario p = new Proprietario(1, "João");

        Cao cao = new Cao(1, "Rex", "Labrador", 2, p);

        service.adicionarAnimal(cao);

        assertEquals(1, service.animais.size());
    }

    @Test
    void adicionarVeterinario() {
        ClinicaService service = new ClinicaService();

        Veterinario v = new Veterinario(
                1,
                "Ana",
                "Cirurgia",
                "CED123"
        );
        service.adicionarVeterinario(v);

        assertEquals(1, service.veterinarios.size());
    }
}
