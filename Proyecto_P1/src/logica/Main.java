package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		/*LocalDate fecha= LocalDate.now();
		ArrayList<String>idiomas = new ArrayList<>();
		idiomas.add("Ingles");
		idiomas.add("Frances");
		ArrayList<String>miIdiomas = new ArrayList<>();
		miIdiomas.add("Frances");
		miIdiomas.add("Ingles");
	Empresa e1 = new Empresa("789", "La empresa", "859-896-8585", "EL email.com", "Telematica", "Un lugar");
	Solicitante s1 = new Universitario("1", "Yo mismo", "apen", "859-965-9521",fecha , "Domincano", "masculino", "soltero", "santiago", "santiago", "mi email", true, 4, 2, idiomas,true, false, "telematica");
	Solicitante s2 = new Universitario("1", "Yo mismo", "apen", "859-965-9521",fecha , "Domincano", "masculino", "soltero", "santiago", "santiago", "mi email", true, 4, 2, idiomas,true, false, "Matematica");
	BolsaLaboral.getInstance().insertSolicitante(s1);
	BolsaLaboral.getInstance().insertSolicitante(s2);
	System.out.println(BolsaLaboral.getInstance().getMisPersonas().get(0).getIdiomas().get(0));
	BolsaLaboral.getInstance().insertEmpresa(e1);
	System.out.println(BolsaLaboral.getInstance().getMisPersonas().get(0).getNombres());
	System.out.println(BolsaLaboral.getInstance().getMisEmpresas().get(0).getEmail());
	Solicitud soli = new SolicitudUniversitario(1, 1, 26, 0, "largo plazo", true, "Santiago", e1, true, miIdiomas, 2, false, "telematica");
	BolsaLaboral.getInstance().insertSolicitud(soli);
	System.out.println(BolsaLaboral.getInstance().getMisSolicitudes().get(0).getCantVacantes());
	ArrayList<Solicitante>contratado = new ArrayList<>();
	
	System.out.println("");
	System.out.println(BolsaLaboral.getInstance().matcheo(soli));
	/*System.out.println(contratado.get(0).getNombres());
	contratado= BolsaLaboral.getInstance().matcheo(soli);
	System.out.println(contratado.size());
	System.out.println(BolsaLaboral.getInstance().getMisEmpresas().get(0).getMisContratados().get(0).getNombres());*/

	}

}
