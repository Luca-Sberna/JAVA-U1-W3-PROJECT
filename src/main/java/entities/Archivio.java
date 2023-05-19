//package entities;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import javax.persistence.CascadeType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//public class Archivio {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private UUID id;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<ElementoCatalogo> elementiCatalogo;
//
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Prestito> prestiti;
//
//	public Archivio() {
//		this.elementiCatalogo = new ArrayList<>();
//		this.prestiti = new ArrayList<>();
//	}
//
//	public void aggiungiElemento(ElementoCatalogo elemento) {
//		elementiCatalogo.add(elemento);
//	}
//
//	public ElementoCatalogo rimuoviElemento(String codiceIsbn) {
//		ElementoCatalogo elementoRimosso = ricercaPerIsbn(codiceIsbn);
//		if (elementoRimosso != null) {
//			elementiCatalogo.remove(elementoRimosso);
//		}
//		return elementoRimosso;
//	}
//
//	public ElementoCatalogo ricercaPerIsbn(String codiceIsbn) {
//		return elementiCatalogo.stream().filter(e -> codiceIsbn != null && codiceIsbn.equals(e.getCodiceIsbn()))
//				.findFirst().orElse(null);
//	}
//
//	public List<ElementoCatalogo> getElementiCatalogo() {
//		return elementiCatalogo;
//	}
//
//	public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
//		return elementiCatalogo.stream().filter(e -> e.getAnnoPubblicazione() == annoPubblicazione)
//				.collect(Collectors.toList());
//	}
//
//	public List<Libro> ricercaPerAutore(String autore) {
//		return elementiCatalogo.stream().filter(e -> e instanceof Libro).map(e -> (Libro) e)
//				.filter(l -> autore.equals(l.getAutore())).collect(Collectors.toList());
//	}
//
//	public void salvaSuDisco(String nomeFile) {
//		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
//			out.writeObject(elementiCatalogo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public void caricaDaDisco(String nomeFile) {
//		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
//			elementiCatalogo = (List<ElementoCatalogo>) in.readObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<Prestito> ricercaPrestitiPerUtente(String numeroTessera) {
//		return prestiti.stream().filter(p -> p.getUtente().getTessera().equals(numeroTessera))
//				.collect(Collectors.toList());
//	}
//
//	public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
//		LocalDate now = LocalDate.now();
//		return prestiti.stream().filter(p -> p.getDataEffettiva() == null)
//				.filter(p -> p.getDataPrevista().isBefore(now)).collect(Collectors.toList());
//	}
//
//}
