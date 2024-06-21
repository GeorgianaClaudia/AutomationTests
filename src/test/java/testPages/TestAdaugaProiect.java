package testPages;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdaugaProiecte;
import utilities.BaseSteps;

public class TestAdaugaProiect extends BaseSteps {
    @Test
    public void testAdaugaEditeazaStergeProiect() throws InterruptedException {
        AdaugaProiecte adaugaProiecte=homePage.clickAlocariProiecte();
        Thread.sleep(2000);
        adaugaProiecte.adaugaNumeProiect();
        adaugaProiecte.adaugaDescriereProiect();
        adaugaProiecte.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(adaugaProiecte.esteSuccesMsgVizibil(),"Salvarea nu s-a efectuat");
        Thread.sleep(1000);
        //scenariu date incomplete
        adaugaProiecte.adaugaNumeProiect();
        adaugaProiecte.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(adaugaProiecte.esteMsgAvertizareVizibil(),"Mesajul de avertizare nu e vizibil");
        Thread.sleep(1000);
        adaugaProiecte.clickLista();
        Thread.sleep(1000);
        Assert.assertTrue(adaugaProiecte.esteBtnAdaugaProiecteVizibil(),"Comutare pe noua pagina esuata");
        Thread.sleep(1000);
        //scenariu editare/modificare date
        adaugaProiecte.deruleazaLaUltimulRand();
        String initNume= adaugaProiecte.obtineUltimulNumeProiect();
        String initDescriere= adaugaProiecte.obtineUltimaDescriereProiect();
        adaugaProiecte.clickUltimulBtnModifica();
        Thread.sleep(1000);
        adaugaProiecte.adaugaNumeProiect();
        adaugaProiecte.adaugaDescriereProiect();
        Thread.sleep(1000);
        adaugaProiecte.clickSalveaza();
        Thread.sleep(1000);
        adaugaProiecte.deruleazaLaUltimulRand();
        String dupaNume=adaugaProiecte.obtineUltimulNumeProiect();
        String dupaDescriere=adaugaProiecte.obtineUltimaDescriereProiect();
        Assert.assertNotEquals(initNume,dupaNume,"modificarile nu s-au salvat!");
        Assert.assertNotEquals(initDescriere,dupaDescriere,"Modificarile privind descrierea nu s-a salvat");
        Thread.sleep(1000);
        //scenariu stergere proiect
        int initRanduri=adaugaProiecte.numarRanduriTabel();
        adaugaProiecte.clickUltimulBtnSterge();
        Thread.sleep(1000);
        adaugaProiecte.acceptaStergere();
        Thread.sleep(1000);
        adaugaProiecte.deruleazaLaUltimulRand();
        int dupaRanduri=adaugaProiecte.numarRanduriTabel();
        Assert.assertTrue(initRanduri>dupaRanduri,"Stergerea nu s-a efectuat");
    }
}
