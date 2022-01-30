package io.greenerpastures.rest.realcomp;

import io.greenerpastures.rest.realcomp.model.FringeBenefits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FringeBenefitsTest {

    public static FringeBenefits createTestData() {
        FringeBenefits fringeBenefits = new FringeBenefits();
        fringeBenefits.setEducationExpenses(5000);
        fringeBenefits.setOther(100);
        return fringeBenefits;
    }

    @Test
    public void totalValueOfFringeBenefits() {
        FringeBenefits fringeBenefits = createTestData();
        assertEquals(5100, fringeBenefits.calculateTotalFringeValue().longValue());
        }

}
