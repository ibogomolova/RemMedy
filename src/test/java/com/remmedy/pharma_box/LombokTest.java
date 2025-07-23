package com.remmedy.pharma_box;

import com.remmedy.pharma_box.entity.Medicine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LombokTest {
    @Test
    void testLombok() {
        Medicine m = new Medicine();
        m.setName("Test");
        assertEquals("Test", m.getName());
    }
}
