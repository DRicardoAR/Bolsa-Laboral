package logica;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BolsaLaboral implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Solicitante> misSolicitantes;
	private ArrayList<Solicitud> misSolicitudes;
	private static BolsaLaboral bolsa;
	private String archivo = "BolsaLaboral.dat";

	public BolsaLaboral() {
		super();
		this.bolsa = null;
		this.misEmpresas = new ArrayList<>();
		this.misSolicitantes = new ArrayList<>();
		this.misSolicitudes = new ArrayList<>();
		
	}

	public ArrayList<Empresa> getMisEmpresas() {
		return misEmpresas;
	}

	public ArrayList<Solicitante> getMisPersonas() {
		return misSolicitantes;
	}

	public ArrayList<Solicitud> getMisSolicitudes() {
		return misSolicitudes;
	}

	public static BolsaLaboral getInstance() {
		if (bolsa == null) {
			bolsa = new BolsaLaboral();
		}
		return bolsa;

	}

	public void insertEmpresa(Empresa empresa) {
		misEmpresas.add(empresa);
	}

	public void insertSolicitante(Solicitante pSolicitante) {
		String code = getCodeSolicitante();
		pSolicitante.setCodigo(code);
		pSolicitante.setContratado(false);
		misSolicitantes.add(pSolicitante);
	}

	public void insertSolicitud(Solicitud pSolicitud) {
		String code = getCodeSolicitud();
		pSolicitud.setCodigo(code);
		misSolicitudes.add(pSolicitud);
	}

	// Generacion de Codigos....
	public String getCodeSolicitante() {
		String code = "";
		String codigo = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		for (int i = 0; i < 3;) {
			char c = (char) r.nextInt(225);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				code += c;
				i++;
			}

		}
		codigo = "S" + code;
		return codigo;
	}

	public String getCodeSolicitud() {
		String code = "";
		String codigo = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		for (int i = 0; i < 3;) {
			char c = (char) r.nextInt(225);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				code += c;
				i++;
			}

		}
		codigo = "SO" + code;
		return codigo;
	}

	// Funciones de Empresa
	public int cantSolicitudes(String RNC) {
		int cant = 0;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(RNC)) {
				cant++;
			}
		}
		return cant;
	}

	// Funciones de Solicitantes
	public boolean buscarSolicitud(Solicitud pSolicitud) {
		boolean find = false;
		if (misSolicitudes.contains(pSolicitud)) {
			find = true;
		}
		return find;
	}

	public boolean buscarSolicitante(Solicitante pSolicitante) {
		boolean find = false;
		if (misSolicitantes.contains(pSolicitante)) {
			find = true;
		}
		return find;
	}

	public int cantSolicitanteUniversitario() {
		int cant = 0;
		for (Solicitante solicitante : misSolicitantes) {
			if (solicitante instanceof Universitario) {
				cant++;
			}
		}

		return cant;
	}

	public int cantSolicitanteTecnico() {
		int cant = 0;
		for (Solicitante solicitante : misSolicitantes) {
			if (solicitante instanceof Tecnico) {
				cant++;
			}
		}

		return cant;
	}

	public int cantSolicitanteObrero() {
		int cant = 0;
		for (Solicitante solicitante : misSolicitantes) {
			if (solicitante instanceof Obrero) {
				cant++;
			}
		}

		return cant;
	}

	// Funciones de Matching
	public boolean validarGeneral(Solicitante persona, Solicitud solicitud) {
		boolean valido = false;
		if (persona.contratado==false) {
			if (persona.isVehiculoPropio() == solicitud.isVehiculoPropio()) {
				if (persona.isMudarse() == solicitud.isMudarse()) {
					if (persona.getCategoriaLicencia() >= solicitud.getCategoriaLicencia()) {
						if (persona.getAnnosExperiencia() >= solicitud.getAnnosExperiencia()) {
							if ((persona.setEdadSolicitante() >= solicitud.getEdadMin())
									&& (persona.setEdadSolicitante() <= solicitud.getEdadMax())) {
								if (persona.getIdiomas().size() == solicitud.getIdiomas().size()) {
									if (validarIdiomas(persona, solicitud)) {
										valido = true;

									}
								}
							}

						}

					}
				}
			}
		}

		return valido;
	}

	// Validacion Obrero con solicitud
	private boolean ValidarObrero(Solicitante solicitante, Solicitud solicitud) {
		boolean validar = false;
		
		ArrayList<String> listaSolicitud = ((SolicitudObrero) solicitud).getHabilidades();
		ArrayList<String> listaObrero = ((Obrero) solicitante).getHabilidades();
		
		if (listaSolicitud.size() == listaSolicitud.size()) {
			for (String habilidadObrero : listaSolicitud) {
				if(listaObrero.contains(habilidadObrero)){					
					validar = true;				
			}else{
				validar = false;
			}
			}
			
		}
		return validar;
	}

	// Validacion Tecnico con solicitud
	private boolean ValidarTecnico(Solicitante solicitante, Solicitud soli) {
		boolean validar = false;
		if (((Tecnico) solicitante).getArea().equalsIgnoreCase(((SolicitudTecnico) soli).getArea())) {
			validar = true;
		}
		return validar;
	}

	// Validacion Universitario con solicitud
	private boolean validarUniversitario(Solicitante solicitante, Solicitud soli) {
		boolean validar = false;
		if (((Universitario) solicitante).getCarrera().equalsIgnoreCase(((SolicitudUniversitario) soli).getCarrera())) {
			validar = true;
			if(((Universitario) solicitante).isPostGrado() && !((SolicitudUniversitario) soli).isPostGrado()){
				validar = true;
			}
		}
		return validar;
	}

	// Validar Idiomas
	public boolean validarIdiomas(Solicitante persona, Solicitud soli) {
		boolean aux = false;
		for (String idiomas : soli.getIdiomas()) {
		if(persona.getIdiomas().contains(idiomas)){
			aux = true;
		}else {
			aux = false;
		}	
		}
		

		return aux;
	}

	public ArrayList<Solicitante> matcheo(Solicitud soli) {
		ArrayList<Solicitante> misContratados = new ArrayList<>();
		if (soli instanceof SolicitudObrero) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Obrero) {
					if (validarGeneral(solicitante, soli)) {
						if (ValidarObrero(solicitante, soli)) {
							solicitante.setContratado(true);
							misContratados.add(solicitante);
							soli.getEmpresa().insertContratado(solicitante);
						}
					}
				}
			}

		}

		if (soli instanceof SolicitudTecnico) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Tecnico) {
					if (validarGeneral(solicitante, soli)) {
						if (ValidarTecnico(solicitante, soli)) {
							solicitante.setContratado(true);
							misContratados.add(solicitante);
							soli.getEmpresa().insertContratado(solicitante);
						}

					}
				}
			}
		}
		if (soli instanceof SolicitudUniversitario) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Universitario) {
					if (validarGeneral(solicitante, soli)) {
						if (validarUniversitario(solicitante, soli)) {
							solicitante.setContratado(true);
							misContratados.add(solicitante);
							soli.getEmpresa().insertContratado(solicitante);
						}

					}
				}
			}
		}

		return misContratados;
	}
// Escritura & Lectura Binarios
	public void esribirBolsa(){
		FileOutputStream bolsaFile = null;
		ObjectOutputStream bolsaOut = null;
		
		try {
			bolsaFile = new FileOutputStream(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bolsaOut = new ObjectOutputStream(bolsaFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bolsaOut.writeObject(bolsa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void leerBolsa(){
		FileInputStream bolsaFile = null;
		ObjectInputStream bolsaIn = null;
		try {
			bolsaFile = new FileInputStream("Biblioteca.dat");
			bolsaIn = new ObjectInputStream(bolsaFile);
			try {
				bolsa = (BolsaLaboral) bolsaIn.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//validacion de Email
		public boolean validarEmail(String email){
		       Pattern patt = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		       Matcher match = patt.matcher(email);
		       if(!match.find()){
		    	   return true;
		       }else {
		    	   return false;
		       }
		}
		//Retornar una empresa dado un RNC
		public Empresa RetornarEmpresa(String RNC){
			Empresa miEmpresa = null;
			for (Empresa empresa : misEmpresas) {
				if(empresa.getRNC().equalsIgnoreCase(RNC)){
					miEmpresa = empresa;
				}				
			}
			
			return miEmpresa;
		}
		
		//Retornar Solicotud dado su codigo
		public Solicitud RetornarSolocitud(String codigo){
			Solicitud miSolicitud = null;
			for (Solicitud solicitud : misSolicitudes) {
				if(solicitud.getCodigo().equalsIgnoreCase(codigo)){
					miSolicitud = solicitud;
				}
				
			}
			return miSolicitud;
		}
	
}
