package model;

import java.time.LocalDate;

public class VoloPartenza extends Volo{
    private int gateAssegnato;

    public VoloPartenza(int codiceVolo, String compagniaAerea,
                        String aeroportoOrigine, String aeroportoDestinazione,
                        LocalDate dataOraPartenza, LocalDate dataOraArrivo, StatoVolo statoVolo,
                        int ritardo, int gateAssegnato){
        super(codiceVolo, compagniaAerea, "NAPOLI", aeroportoDestinazione, dataOraPartenza, dataOraArrivo,
                statoVolo, ritardo);
        this.gateAssegnato = gateAssegnato;
    }
}
