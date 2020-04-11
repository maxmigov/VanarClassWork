package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArchitectureTest {
    @Test
    void checkBothCLIAndGUI() {
        try {
            Class.forName("main.CLIApplication");
            Class.forName("main.GUIApplication");
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have both CLI and GUI");
        }
    }

    @Test
    void checkAsteroidAndNASADataProvider() {
        try {
            Class.forName("space.Asteroid");
            Class.forName("space.NASADataProvider");
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have both Asteroid and NASADataProvider");
        }
    }

    @Test
    void checkMethodMainForCLIAndGUI() {
        try {
            Class.forName("main.CLIApplication").getMethod("main", String[].class);
            Class.forName("main.GUIApplication").getMethod("main", String[].class);
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have both CLI and GUI");
        } catch (NoSuchMethodException e) {
            fail("The App doesn't have \"main\" method");
        }
    }
    @Test
    void checkMethodToStringAsteroid() {
        try {
            Class.forName("space.Asteroid").getMethod("toString");
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have Asteroid");
        } catch (NoSuchMethodException e) {
            fail("The App doesn't have \"toString()\" method");
        }
    }

    @Test
    void checkMethodGetNeoAsteroidsNASADataProvider() {
        try {
            Class.forName("space.NASADataProvider").getMethod("getNeoAsteroids", String.class, String.class);
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have NASADataProvider");
        } catch (NoSuchMethodException e) {
            fail("The App doesn't have \"getNeoAsteroids()\" method");
        }
    }

    @Test
    void checkAsteroidConstructor() {
        try {
            Class.forName("space.Asteroid").getConstructor(java.time.LocalDate.class, Float.class, Float.class, Boolean.class);
        } catch (ClassNotFoundException e) {
            fail("The App doesn't have Asteroid");
        } catch (NoSuchMethodException e) {
            fail("The App doesn't have constructor");
        }
    }
}