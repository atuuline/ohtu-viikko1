
package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class VarastoTest {
 
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
 
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
 
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
 
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
 
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
 
        double saatuMaara = varasto.otaVarastosta(2);
 
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
 
        varasto.otaVarastosta(2);
 
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiLisaaSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(11);

        // saldon pitäisi olla sama kuin tilavuus
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorissaEiNegatiivistaTilavuutta() {
        Varasto huonoVarasto = new Varasto(-10);

        // tilavuuden pitäisi olla nolla
        assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitetussaKonstruktorissaEiNegatiivistaTilavuutta() {
        Varasto huonoVarasto = new Varasto(-10, 0);

        // tilavuuden pitäisi olla nolla
        assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorinLuomallaVarastollaOikeaTilavuus() {
        varasto = new Varasto(20, 0);
        assertEquals(20, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegatiivistaSaldoa() {
        varasto = new Varasto(20, -10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void NegatiivinenLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi pysyä ennallaan
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenAiheuttaaPikapoistumisen() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liikaOttaminenOttaaKaikenMitaVoi() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitys() {
        String tilanne = varasto.toString();
        assertEquals("saldo = 0.0, viela tilaa 10.0", tilanne);
    }
}