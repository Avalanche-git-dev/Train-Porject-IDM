package com.treno.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.treno.application.model.Valutazione;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrenoDTO {
	public TrenoDTO(Long idTreno, boolean inVendita, String immagine) {
		this.id=idTreno;
		this.inVendita= inVendita;
		this.immagine = immagine;
		
	}
	private Long id;
    private String nome;
    private String descrizione;
    private Double mediaValutazioni;
    private String immagine;
    private boolean inVendita;
    private List<Valutazione> valutazioni = new ArrayList<Valutazione>();
	@Override
	public String toString() {
		return "TrenoDTO [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", mediaValutazioni="
				+ mediaValutazioni + ", immagine=" + immagine + ", inVendita=" + inVendita + ", valutazioni="
				+ valutazioni + "]";
	}
	
    

}
