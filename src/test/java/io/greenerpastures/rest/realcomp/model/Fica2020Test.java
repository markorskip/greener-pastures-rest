package io.greenerpastures.rest.realcomp.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class Fica2020Test {

    private static final BigDecimal oneMillion = new BigDecimal("1000000");

    @Test
    public void employerPortion() {
        Fica2020.SOCIAL_SECURITY_EMPLOYER_PORTION.calculate(oneMillion);


    }

}
