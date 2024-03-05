package com.ynov.jdlvbackend.dto.service;


import com.ynov.jdlvbackend.dto.model.ReglesCustom;
import com.ynov.jdlvbackend.dto.model.ReglesCustomDto;
import com.ynov.jdlvbackend.dto.repository.IReglesCustomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("La classe ReglesCustomServiceTests teste:")
@ExtendWith(MockitoExtension.class)
public class ReglesCustomServiceTests {

    private ReglesCustom reglesCustom = new ReglesCustom();
    @Mock
    private IReglesCustomRepo iReglesCustomRepo;
    @Mock
    private UserService userService;
    private ReglesCustomService reglesCustomService;
    @BeforeEach
    public void setup() {
        reglesCustomService = new ReglesCustomService(iReglesCustomRepo, userService);
    }
    @DisplayName("Que les regles à sauver ne sont pas nuls.")
    @Test
    public void testerReglesCustomServiceIsNotNull() {
        assertNotNull(reglesCustomService);
    }

    @DisplayName("Que les valeur des règles ne sont pas supérieurs à 100 et pas inférieur ou égales à 0")
    @Test
    public void testerValeurDesRegles() {
        reglesCustom.setReproduction(5);
        reglesCustom.setTailleGrille(100);
        reglesCustom.setSurPopulation(3);
        reglesCustom.setSousPopulation(2);
        assertTrue((reglesCustom.getReproduction() > 0 && reglesCustom.getReproduction() <= 100)
                && (reglesCustom.getSousPopulation() > 0 && reglesCustom.getSousPopulation() <= 100)
                && (reglesCustom.getSurPopulation() > 0 && reglesCustom.getSurPopulation() <= 100)
                && (reglesCustom.getTailleGrille() > 0 && reglesCustom.getTailleGrille() <= 100));
    }

    @DisplayName("Que la méthode recupererRegles renvoie null pour un login de type String vide")
    @Test
    public void testerRetourMethodeRecupererReglesStringVide() {
        assertTrue(this.reglesCustomService.recupererRegles("") == null);
    }
    @DisplayName("Que la méthode recupererRegles renvoie un null pointer exception lorsque l'on vérifie que le " +
            "résultat est empty après avoir mis in login null.")
    @Test
    public void testerRetourMethodeRecupererReglesParametreNull() {
        assertThrows(NullPointerException.class, () -> this.reglesCustomService.recupererRegles(null).isEmpty());
    }

    @DisplayName("Que la méthode sauverRegles renvoie un objet null si le login affilié au règles n'existe pas en bdd")
    @Test
    public void testerLaSauvegardeDesRegles() {
        assertTrue(this.reglesCustomService.sauverRegles(new ReglesCustomDto(), "login") == null);
    }
}
