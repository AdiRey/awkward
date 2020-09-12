package pl.awkward.needed_tests;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;
import pl.awkward.gender.Gender;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationTest {

    @Test
    void serialization_onGender_test() {
        Gender man = new Gender();
        man.setId(1L);
        man.setGender("Męska");
        man.setActive(true);
        man.setAddDate(LocalDateTime.now());

        Gender resultGender = (Gender) SerializationUtils.deserialize(SerializationUtils.serialize(man));

        assertEquals(man.getId(), resultGender.getId());
        assertTrue(man.getGender().equals(resultGender.getGender()));
        assertEquals(man.getActive(), resultGender.getActive());
        assertEquals(man.getAddDate(), resultGender.getAddDate());
        assertEquals(man.getDeleteDate(), resultGender.getDeleteDate());
    }

}
