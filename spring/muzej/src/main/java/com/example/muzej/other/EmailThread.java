package com.example.muzej.other;

import com.example.muzej.model.KartaEntity;
import com.example.muzej.service.KartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmailThread extends Thread{

    @Autowired
    KartaService kartaService;

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public void run() {
        while(true){

            if(kartaService!=null) {

                List<KartaEntity> lista = kartaService.getDanasnjeKArte();
                if(lista!=null) {

                    for (KartaEntity k : lista) {
                        Time vrijeme = k.getPosjetaByPosjetaId().getVrijeme();
                        LocalTime time = vrijeme.toLocalTime();
                        Double d = k.getPosjetaByPosjetaId().getTrajanje() * 60;
                        int i = d.intValue();
                        LocalTime time1 = time.plusMinutes(i);
                        LocalTime sada = LocalTime.now();
                        java.util.Date utilDatum = new java.util.Date();
                        Date datum = new Date(utilDatum.getTime());
                        Date datum1=new Date(k.getPosjetaByPosjetaId().getDatum().getTime());
                        double razlika = sada.until(time, ChronoUnit.MINUTES);
                        Date sutra=new Date(utilDatum.getTime());
                        sutra.setDate(sutra.getDate()+1);
                        //System.out.println("Sutra datum: "+sutra);
                        //System.out.println((razlika == 60.0));
                        //System.out.println((datum.toString().equals(datum1.toString())) +" id karte:"+k.getId());
                        if ((datum.toString().equals(datum1.toString()) && (razlika == 60.0)) || (sutra.toString().equals(datum1.toString()) && (razlika == 60.0))) {
                            //System.out.println("prije pocetka");
                            emailService.sendMailPodsjetnik(k.getRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId().getKorisnik().getMail(),
                                    "Viruelna posjeta počinje za sat vremena! Muzej koji ste gledate: "+ k.getPosjetaByPosjetaId().getPrezentacijaByPrezentacijaId().getMuzejByMuzejId().getNaziv()+". "
                                            , k.getBrojKarte());
                        }
                        double razlika1 = sada.until(time1, ChronoUnit.MINUTES);
                        if (datum.toString().equals(datum1.toString()) && (razlika1 == 5.0)) {
                            //System.out.println("prije kraja");
                            emailService.sendMailPodsjetnik(k.getRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId().getKorisnik().getMail(),
                                    "Viruelna posjeta završava za pet minuta! Muzej koji ste gledali: " +
                                            k.getPosjetaByPosjetaId().getPrezentacijaByPrezentacijaId().getMuzejByMuzejId().getNaziv()+". ", k.getBrojKarte());
                        }
                    }
                }
            }

            try{
                sleep(50000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
