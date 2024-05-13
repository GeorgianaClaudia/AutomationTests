package testPages;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StareAlocare;
import utilities.BaseSteps;

public class StareAlocareTeste extends BaseSteps {
    @Test
    public void testAdaugaEditeazaStergeStare() throws InterruptedException {
        StareAlocare stareAlocare = homePage.clickStareAlocare();
        Thread.sleep(2000);
        //adauga Stare alocare
        stareAlocare.adaugaNumeStare();
        stareAlocare.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(stareAlocare.esteSuccesMsgVizibil(),"Salvarea nu s-a efectuat cu succes");

        //salveaza fara date
        Thread.sleep(1000);
        stareAlocare.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(stareAlocare.esteMsgAvertizareVizibil(),"Mesajul de avertizare nu  e vizibil");

        //schimba pagina
        Thread.sleep(1000);
        stareAlocare.clickLista();
        Thread.sleep(1000);
        Assert.assertTrue(stareAlocare.esteBtnAdaugaAngajatVizibil(), "Nu s-a comutat pe pagina");

        //Editeaza stare alocare
        Thread.sleep(1000);
        String numeInitial= stareAlocare.obtineUltimulNume();
        Thread.sleep(1000);
        stareAlocare.clickUltimulBtnModifica();
        stareAlocare.adaugaNumeStare();
        stareAlocare.clickSalveaza();
        Thread.sleep(1000);
        String numeEditat = stareAlocare.obtineUltimulNume();
        Thread.sleep(1000);
        Assert.assertNotEquals(numeInitial, numeEditat, "Numele starii nu a fost editat");

        //Sterge Angajat
        Thread.sleep(1000);
        int numarInitialStari= stareAlocare.numarRanduriTabel();
        stareAlocare.clickUltimulBtnSterge();
        Thread.sleep(1000);
        stareAlocare.acceptaStergere();
        Thread.sleep(1000);
        int numarStariDupaSterg=stareAlocare.numarRanduriTabel();
        Thread.sleep(1000);
        Assert.assertTrue(numarInitialStari>numarStariDupaSterg, "Starea nu a fost stearsa.");
    }
}
