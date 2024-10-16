package treno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrenoDTO {
    
    private Long idTreno;
    private String sigla;
    private String immagine;
    private boolean inVendita;
    private double prezzoVendita;
    private double pesoMin;
    private double pesoMax;
    private double costo;
    private double lunghezzaMax;;
    private double lunghezzaMin;
    private double postiTotali;
    private long idOwner; // Preso dall'utente proprietario
    private double mediaValutazioni; // Media delle valutazioni dei treni
	public TrenoDTO(Long idTreno, String sigla, String immagine, boolean inVendita, double prezzoVendita,
			double pesoMin, double pesoMax, double costo, double lunghezzaMax, double lunghezzaMin, double postiTotali,
			long idOwner, double mediaValutazioni) {
		super();
		this.idTreno = idTreno;
		this.sigla = sigla;
		this.immagine = immagine;
		this.inVendita = inVendita;
		this.prezzoVendita = prezzoVendita;
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.costo = costo;
		this.lunghezzaMax = lunghezzaMax;
		this.lunghezzaMin = lunghezzaMin;
		this.postiTotali = postiTotali;
		this.idOwner = idOwner;
		this.mediaValutazioni = mediaValutazioni;
	}
	public TrenoDTO(Long idTreno2, boolean inVendita2, String immagine2) {
		this.idTreno=idTreno2;
		this.inVendita= inVendita2;
		this.immagine=immagine2; 
	}
    
    
    
    
    
    
}
