package com.example.muzej.other;

import com.example.muzej.model.KartaEntity;
import com.lowagie.text.DocumentException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class UserPDFExporter {

    static final String fileName="src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"karta.pdf";

    public void export(KartaEntity karta) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        File file=new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(file);
        PdfWriter pw=PdfWriter.getInstance(document, fos);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.black);

        Paragraph p = new Paragraph("Karta", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
        font1.setSize(14);
        font1.setColor(Color.black);
        Paragraph p0=new Paragraph(" ", font1);
        Paragraph p1=new Paragraph("Broj karte: "+karta.getBrojKarte(), font1);
        p1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        Paragraph p2=new Paragraph("Muzej: "+karta.getPosjetaByPosjetaId().getPrezentacijaByPrezentacijaId().getMuzejByMuzejId().getNaziv(), font1);
        p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        Format f=new SimpleDateFormat("dd-MM-YYYY");
        Paragraph p4=new Paragraph("Datum posjete: "+f.format(karta.getPosjetaByPosjetaId().getDatum()), font1);
        p4.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        Paragraph p3=new Paragraph("Početak posjete: "+ karta.getPosjetaByPosjetaId().getVrijeme(), font1);
        p3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        Paragraph p5=new Paragraph("Trajanje: "+karta.getPosjetaByPosjetaId().getTrajanje() +" h", font1);
        p5.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        Paragraph p6=new Paragraph("Cijena: "+karta.getPosjetaByPosjetaId().getCijena()+" KM", font1);
        p6.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        Paragraph p7=new Paragraph("Ime i prezime kupca: "+karta.getRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId().getKorisnik().getIme()+" "+karta.getRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId().getKorisnik().getPrezime(), font1);
        p7.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(p0);
        document.add(p1);
        document.add(p2);
        document.add(p4);
        document.add(p3);
        document.add(p5);
        document.add(p6);
        document.add(p7);
        document.close();

    }
}
