package projeto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import projeto.model.*;
import projeto.service.ClinicaService;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class ClinicaServiceTest {

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

    @Test
    void adicionarProprietario() {
        ClinicaService service = new ClinicaService();

        Proprietario p = new Proprietario(1, "Maria");
        service.adicionarProprietario(p);

        assertEquals(1, service.proprietarios.size());
    }

    @Test
    void adicionarConsulta() {
        ClinicaService service = new ClinicaService();

        Proprietario p = new Proprietario(1, "João");
        Veterinario v = new Veterinario(1, "Ana", "Geral", "CED999");
        Cao cao = new Cao(1, "Rex", "Labrador", 2, p);

        service.adicionarAnimal(cao);
        service.adicionarVeterinario(v);

        Consulta consulta = new Consulta(cao, v, java.time.LocalDate.now(), "Check-up");
        service.adicionarConsulta(consulta);

        assertEquals(1, service.consultas.size());
    }


    @Test
    void calcularFaturacaoTotal() {
        ClinicaService service = new ClinicaService();

        Proprietario p = new Proprietario(1, "João");
        Veterinario v = new Veterinario(1, "Ana", "Geral", "CED999");
        Cao cao = new Cao(1, "Rex", "Labrador", 2, p);

        // Consulta 1
        Consulta c1 = new Consulta(
                cao,
                v,
                java.time.LocalDate.now(),
                "Check-up"
        ) {
            @Override
            public double calcularTotal() {
                return 30.00;
            }
        };

        // Consulta 2
        Consulta c2 = new Consulta(
                cao,
                v,
                java.time.LocalDate.now(),
                "Vacinação"
        ) {
            @Override
            public double calcularTotal() {
                return 20.00;
            }
        };

        // NÃO usar adicionarConsulta() porque chama o repositório MySQL
        service.consultas.add(c1);
        service.consultas.add(c2);

        double total = service.calcularFaturacaoTotal();

        assertEquals(50.00, total);
    }

}