package testPages;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlocareProiecte;
import utilities.BaseSteps;

public class AlocareProiecteTeste extends BaseSteps {
    @Test
    public void testAlocareEditareStergereProiect() throws InterruptedException {
        AlocareProiecte alocareProiecte=homePage.clickAlocareProiecte();
        Thread.sleep(2000);
        //Alocare proiect
        alocareProiecte.selectPrimulProiect();
        Thread.sleep(1000);
        alocareProiecte.adaugaDescriere();
        alocareProiecte.selectPrimulAngajat();
        alocareProiecte.adaugaDataCurenta();
        alocareProiecte.selectPrimulStatus();
        Thread.sleep(1000);
        alocareProiecte.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(alocareProiecte.esteSuccesMsgVizibil(), "Salvarea nu s-a efectuat!");
        Thread.sleep(1000);

        //Salveaza fara date
        alocareProiecte.adaugaDescriere();
        alocareProiecte.clickSalveaza();
        Thread.sleep(1000);
        Assert.assertTrue(alocareProiecte.esteMsgAvertizareVizibil(),"Mesajele de avertizare nu sunt prezente");

        //schimbare pagina
        Thread.sleep(1000);
        alocareProiecte.clickLista();
        Thread.sleep(1000);
        Assert.assertTrue(alocareProiecte.esteBtnAdaugaAlocareVizibil(), "Nu s-a comutat pe pagina");

        //modificare alocare
        Thread.sleep(2000);
        alocareProiecte.deruleazaLaUltimulRand();
        String descriereInit=alocareProiecte.obtineUltimaDescriereProiect();
        alocareProiecte.clickUltimulBtnModifica();
        Thread.sleep(1000);
        alocareProiecte.adaugaDescriere();
        alocareProiecte.clickSalveaza();
        Thread.sleep(1000);
        String descriereEditata= alocareProiecte.obtineUltimaDescriereProiect();
        Thread.sleep(1000);
        Assert.assertNotEquals(descriereInit,descriereEditata, "Modificarea nu s-a salvat!");

        //stergere alocare
        Thread.sleep(1000);
        alocareProiecte.deruleazaLaUltimulRand();
        int nrRanduriInit=alocareProiecte.numarRanduriTabel();
        alocareProiecte.clickUltimulBtnSterge();
        Thread.sleep(1000);
        alocareProiecte.acceptaStergere();
        Thread.sleep(1000);
        alocareProiecte.deruleazaLaUltimulRand();
        int nrRanduriDupa= alocareProiecte.numarRanduriTabel();
        Thread.sleep(1000);
        Assert.assertTrue(nrRanduriInit>nrRanduriDupa, "Stergerea nu s-a efectuat cu succes!");

        //cauta dupa angajat
        Thread.sleep(1000);
        int nrInit= alocareProiecte.numarRanduriTabel();
        Thread.sleep(1000);
        alocareProiecte.cautaAngajat();
        Thread.sleep(1000);
        int nrAfter=alocareProiecte.numarRanduriTabel();
        Thread.sleep(1000);
        Assert.assertTrue(nrInit>nrAfter, "Cautarea dupa angajat nu  a functionat");
        Thread.sleep(1000);

        //cauta dupa proiect
        alocareProiecte.clickBtnCauta();
        Thread.sleep(1000);
        int reset= alocareProiecte.numarRanduriTabel();
        alocareProiecte.cautaProiect();
        Thread.sleep(1000);
        int after=alocareProiecte.numarRanduriTabel();
        Thread.sleep(1000);
        Assert.assertTrue(reset>after,"Cautarea dupa proiect nu a functionat");
        Thread.sleep(1000);
        alocareProiecte.clickLinkDescarca();
        Thread.sleep(5000);
        boolean file=alocareProiecte.isFileDownloaded();
        Assert.assertTrue(file,"Fisierul nu a fost gasit");
        Thread.sleep(1000);

    }
}
