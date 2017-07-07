package logica;

import java.util.ArrayList;
import java.util.Random;

public class BolsaLaboral {
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Solicitante> misSolicitantes;
	private ArrayList<Solicitud> misSolicitudes;
	private static BolsaLaboral bolsa;

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

	public BolsaLaboral getInstance() {
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
public boolean validarGeneral(Solicitante persona, Solicitud solicitud){
	boolean valido = true;
		if(persona.isVehiculoPropio()==solicitud.isVehiculoPropio()){
			if(persona.isMudarse() == solicitud.isMudarse()){
				if(persona.getCategoriaLicencia()==solicitud.getCategoriaLicencia()){
					if(persona.getAnnosExperiencia()==solicitud.getAnnosExperiencia()){
						if((persona.setEdadSoli()>=solicitud.getEdadMin())&&(persona.setEdadSoli()<=solicitud.getEdadMax())){
							if(persona.getIdiomas().size()==solicitud.getIdiomas().size()){
								if(validarIdiomas(persona, solicitud)){
								
																	  }
																						  }
					}
						
					
					
				                                                                                                            }
				
			                                                                         }
			}
		}
	
	return valido;
}

public boolean validarIdiomas(Solicitante persona,Solicitud soli){
	boolean aux = false;
	for (int i = 0; i < persona.getIdiomas().size(); i++) {
		if(persona.getIdiomas().get(i).equalsIgnoreCase(soli.getIdiomas().get(i)))
		{
		aux= true;
		}
	}
	return aux;
}

	public ArrayList<Solicitante> matcheo(Solicitud soli) {
		ArrayList<Solicitante> misSolicitantes = new ArrayList<>();
		boolean  validar= false;
		
		if (soli instanceof SolicitudObrero) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Obrero) {
					validar = validarGeneral(solicitante , soli);
					if(validar){
						
					}
				}
			}

		}
		if (soli instanceof SolicitudTecnico) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Tecnico) {
					validar = validarGeneral(solicitante , soli);
					if(validar){
						
					}
				}
			}
		}
		if (soli instanceof SolicitudUniversitario) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Universitario) {
					validar = validarGeneral(solicitante , soli);
					if(validar){
						
					}
				}
			}
		}

		return misSolicitantes;
	}
}
