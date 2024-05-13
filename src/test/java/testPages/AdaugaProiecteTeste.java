package testPages;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdaugaProiecte;
import utilities.BaseSteps;

public class AdaugaProiecteTeste extends BaseSteps {

    @Test
    public void testAdaugaEditeazaStergeProiect() throws InterruptedException {
        AdaugaProiecte proiecte= homePage.clickAlocariProiecte();
        Thread.sleep(2000);
        proiecte.adaugaNumeProiect();
        proiecte.adaugaDescriereProiect();
        proiecte.clickSalveaza();
        Assert.assertTrue(proiecte.esteSuccesMsgVizibil(),"Proiectul nu a fost adaugat");

        //Adauga date incomplete
        Thread.sleep(2000);
        proiecte.adaugaNumeProiect();
        proiecte.clickSalveaza();
        Assert.assertTrue(proiecte.esteMsgAvertizareVizibil(),"Mesajul de avertizare nu e vizibil pe pagina");

        //schimba pagina
        Thread.sleep(2000);
        proiecte.clickLista();
        Thread.sleep(2000);
        Assert.assertTrue(proiecte.esteBtnAdaugaProiecteVizibil(),"Butonul nu e vizibil");

        //editeaza proiect
        proiecte.deruleazaLaUltimulRand();
        String numeInitial=proiecte.obtineUltimulNumeProiect();
        String descriereInitial=proiecte.obtineUltimaDescriereProiect();
        Thread.sleep(2000);
        proiecte.clickUltimulBtnModifica();
        Thread.sleep(1000);
        proiecte.adaugaNumeProiect();
        proiecte.adaugaDescriereProiect();
        proiecte.clickSalveaza();
        Thread.sleep(2000);
        proiecte.deruleazaLaUltimulRand();
        String numeModificat= proiecte.obtineUltimulNumeProiect();
        Thread.sleep(1000);
        String descriereModif=proiecte.obtineUltimaDescriereProiect();
        Thread.sleep(1000);
        Assert.assertNotEquals(numeModificat,numeInitial,"Modificarea numelui nu s-a efectuat");
        Assert.assertNotEquals(descriereModif,descriereInitial, "modificarea descrierii nu s-a efectuat");

        //stergere proiect
        Thread.sleep(1000);
        proiecte.deruleazaLaUltimulRand();
        int nrRanduriInit= proiecte.numarRanduriTabel();
        proiecte.clickUltimulBtnSterge();
        Thread.sleep(1000);
        proiecte.acceptaStergere();
        Thread.sleep(2000);
        proiecte.deruleazaLaUltimulRand();
        int nrRanduriDupaStergere= proiecte.numarRanduriTabel();
        Assert.assertTrue(nrRanduriInit>nrRanduriDupaStergere,"Ultimul rand nu s-a sters");
    }

}
