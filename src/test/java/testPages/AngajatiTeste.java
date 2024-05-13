package testPages;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Angajati;
import utilities.BaseSteps;

public class AngajatiTeste extends BaseSteps {

    @Test
    public void testAdaugaEditeazaStergeAngajat() throws InterruptedException {
        Angajati angajati=homePage.clickAngajati();
        Thread.sleep(2000);
        //adauga Angajat
        angajati.adaugaNumeAngajat();
        angajati.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(angajati.esteSuccesMsgVizibil());

        //salveaza fara date
        Thread.sleep(1000);
        angajati.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(angajati.esteMsgAvertizareVizibil(),"Mesajul de avertizare nu  e vizibil");

        //schimba pagina
        Thread.sleep(1000);
        angajati.clickLista();
        Thread.sleep(1000);
        Assert.assertTrue(angajati.esteBtnAdaugaAngajatVizibil(), "Nu s-a comutat pe pagina");

        //Editeaza Angajat
        Thread.sleep(1000);
        String numeInitial= angajati.obtineUltimulNume();
        Thread.sleep(1000);
        angajati.clickUltimulBtnModifica();
        angajati.adaugaNumeAngajat();
        angajati.clickSalveaza();
        Thread.sleep(1000);
        String numeEditat = angajati.obtineUltimulNume();
        Thread.sleep(1000);
        Assert.assertNotEquals(numeInitial, numeEditat, "Numele angajatului nu a fost editat");

        //Sterge Angajat
        Thread.sleep(1000);
        int numarInitialAng= angajati.numarRanduriTabel();
        angajati.clickUltimulBtnSterge();
        Thread.sleep(1000);
        angajati.acceptaStergere();
        Thread.sleep(1000);
        int numarAngDupaSterg=angajati.numarRanduriTabel();
        Thread.sleep(1000);
        Assert.assertTrue(numarInitialAng>numarAngDupaSterg, "Angajatul nu a fost sters.");
    }
}
