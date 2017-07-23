package logica;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
	private FileWriter writer_1;
	private String archivo = "olsaLaboral.dat";

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

	public void modificaEmpresa(Empresa empresa) {

		for (Empresa miempresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(miempresa.getRNC())) {
				miempresa.setArea(empresa.getArea());
				miempresa.setNombre(empresa.getNombre());
				miempresa.setDireccion(empresa.getDireccion());
				miempresa.setEmail(empresa.getEmail());
				miempresa.setTelefono(empresa.getRNC());
			}
		}
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
		if (persona.contratado == false) {
			if (persona.isVehiculoPropio() == solicitud.isVehiculoPropio()) {
				if (persona.isMudarse() == solicitud.isMudarse()) {
					if (persona.getCategoriaLicencia() >= solicitud
							.getCategoriaLicencia()) {
						if (persona.getAnnosExperiencia() >= solicitud
								.getAnnosExperiencia()) {
							if ((persona.setEdadSolicitante() >= solicitud
									.getEdadMin())
									&& (persona.setEdadSolicitante() <= solicitud
											.getEdadMax())) {
								if (validarIdiomas(persona, solicitud)) {
									valido = true;

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
		for (String habilidadObrero : ((SolicitudObrero) solicitud)
				.getHabilidades()) {
			if (((Obrero) solicitante).getHabilidades().contains(
					habilidadObrero)) {
				validar = true;
			} else {
				validar = false;
				break;
			}
		}

		return validar;
	}

	// Validacion Tecnico con solicitud
	private boolean ValidarTecnico(Solicitante solicitante, Solicitud soli) {
		boolean validar = false;
		if (((Tecnico) solicitante).getArea().equalsIgnoreCase(
				((SolicitudTecnico) soli).getArea())) {
			validar = true;
		}
		return validar;
	}

	// Validacion Universitario con solicitud
	private boolean validarUniversitario(Solicitante solicitante, Solicitud soli) {
		boolean validar = false;
		if (((Universitario) solicitante).getCarrera().equalsIgnoreCase(
				((SolicitudUniversitario) soli).getCarrera())) {
			validar = true;
			if (!((Universitario) solicitante).isPostGrado()
					&& ((SolicitudUniversitario) soli).isPostGrado()) {
				validar = false;
			}

		}
		return validar;
	}

	// Validar Idiomas
	public boolean validarIdiomas(Solicitante persona, Solicitud soli) {
		boolean aux = false;
		if (soli.idiomas.size() == 0) {
			aux = true;
		}
		for (String idiomas : soli.getIdiomas()) {
			if (persona.getIdiomas().contains(idiomas)) {
				aux = true;
			} else {
				aux = false;
				break;

			}
		}

		return aux;
	}

	public ArrayList<Solicitante> matcheo(Solicitud soli) {

		ArrayList<Solicitante> candito = new ArrayList<>();
		if (soli instanceof SolicitudObrero) {
			for (Solicitante solicitante : misSolicitantes) {
				if (solicitante instanceof Obrero) {
					if (validarGeneral(solicitante, soli)) {
						if (ValidarObrero(solicitante, soli)) {
							candito.add(solicitante);
							/*
							 * soli.getEmpresa().insertContratado(solicitante);
							 * Crear una funcion qune inserte los contradado y
							 * sea llamada desde el boton: Contratar Solicitante
							 */

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
							candito.add(solicitante);
							/*
							 * soli.getEmpresa().insertContratado(solicitante);
							 * Crear una funcion qune inserte los contradado y
							 * sea llamada desde el boton: Contratar Solicitante
							 */
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
							candito.add(solicitante);
							/*
							 * soli.getEmpresa().insertContratado(solicitante);
							 * Crear una funcion qune inserte los contradado y
							 * sea llamada desde el boton: Contratar Solicitante
							 */
						}

					}
				}
			}
		}

		return candito;
	}

	// Escritura & Lectura Binarios
	public void esribirBolsa() {
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

	public void leerBolsa() {
		FileInputStream bolsaFile = null;
		ObjectInputStream bolsaIn = null;
		try {
			bolsaFile = new FileInputStream("BolsaLaboral.dat");
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

	// validacion de Email
	public boolean validarEmail(String email) {
		Pattern patt = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher match = patt.matcher(email);
		if (!match.find()) {
			return true;
		} else {
			return false;
		}
	}

	// Retornar una empresa dado un RNC
	public Empresa RetornarEmpresa(String RNC) {
		Empresa miEmpresa = null;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(RNC)) {
				miEmpresa = empresa;
			}

		}
		return miEmpresa;
	}

	// Retorna cantidad de solicitudes de obreros hecha por una empresa
	public int cantSoliO(String rnc) {
		int cantO = 0;
		Empresa miEm = RetornarEmpresa(rnc);
		for (Solicitud soli : misSolicitudes) {
			if (soli.getEmpresa().getRNC().equalsIgnoreCase(rnc)) {

				if (soli instanceof SolicitudObrero) {
					cantO++;
				}
			}
		}
		return cantO;
	}

	// Retorna cantidad de solicitudes de Universitarios hecha por una empresa
	public int cantSoliU(String rnc) {
		int cantU = 0;
		for (Solicitud soli : misSolicitudes) {
			if (soli.getEmpresa().getRNC().equalsIgnoreCase(rnc)) {

				if (soli instanceof SolicitudUniversitario) {
					cantU++;
				}
			}
		}
		return cantU;
	}

	// Retorna cantidad de solicitudes de TECNICOS hecha por una empresa
	public int cantSoliT(String rnc) {
		int cantT = 0;

		for (Solicitud soli : misSolicitudes) {
			if (soli.getEmpresa().getRNC().equalsIgnoreCase(rnc)) {

				if (soli instanceof SolicitudTecnico) {
					cantT++;
				}
			}
		}
		return cantT;
	}

	// retorna la cantidad de Obreros que contrato una empresa determinada
	public int CantOCon(String rnc) {
		int CantconO = 0;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(rnc)) {
				for (Solicitante soli : empresa.getMisContratados()) {
					if (soli instanceof Obrero) {
						CantconO++;
					}
				}
			}
		}
		return CantconO;
	}

	// retorna la cantidad de Universitaarios que contrato una empresa
	// determinada
	public int CantUCon(String rnc) {
		int CantconU = 0;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(rnc)) {
				for (Solicitante soli : empresa.getMisContratados()) {
					if (soli instanceof Universitario) {
						CantconU++;
					}
				}
			}
		}
		return CantconU;
	}

	// retorna la cantidad de Tecnicos que contrato una empresa determinada
	public int CantTCon(String rnc) {
		int CantconT = 0;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(rnc)) {
				for (Solicitante soli : empresa.getMisContratados()) {
					if (soli instanceof Tecnico) {
						CantconT++;
					}
				}
			}
		}
		return CantconT;
	}

	// Retornar Solicotud dado su codigo
	public Solicitud RetornarSolocitudCod(String codigo) {
		Solicitud miSolicitud = null;
		for (Solicitud solicitud : misSolicitudes) {
			if (solicitud.getCodigo().equalsIgnoreCase(codigo)) {
				miSolicitud = solicitud;
			}

		}
		return miSolicitud;
	}

	// Retornar Solicitante dando una cedula
	public Solicitante retornarSolicitante(String cedula) {
		Solicitante solicitante = null;
		for (Solicitante soli : misSolicitantes) {
			if (soli.getCedula().equalsIgnoreCase(cedula))
				solicitante = soli;
		}

		return solicitante;
	}

	// Retorna todas las solicitudes de una empresa

	public ArrayList<Solicitud> RetornaSolicitudEmp(Empresa emp) {
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
		for (Solicitud solicitud : misSolicitudes) {
			if (solicitud.getEmpresa().getRNC().equalsIgnoreCase(emp.getRNC())) {
				solicitudes.add(solicitud);
			}

		}
		return solicitudes;
	}

	// Retorna solicid

	public boolean EliminarSolicitud(String codigo) {
		boolean eliminar = false;
		Solicitud SolicitudEliminar = null;
		for (Solicitud soli : misSolicitudes) {
			if (soli.getCodigo().equalsIgnoreCase(codigo)) {
				SolicitudEliminar = soli;
				eliminar = true;
			}

		}
		misSolicitudes.remove(SolicitudEliminar);
		return eliminar;
	}

	// funcion eleminnar empresa
	public void eliminarEmpresa(String cod) {
		Empresa empresaeliminar = null;
		for (Empresa empresa : misEmpresas) {
			if (empresa.getRNC().equalsIgnoreCase(cod)) {
				empresaeliminar = empresa;
			}
		}
		misEmpresas.remove(empresaeliminar);
	}

	// Actualizar una solicitud cuando se modifica
	public void ActualizarSolicitud(Solicitud modi, Solicitud modificarSoli) {
		int index = 0;
		if (misSolicitudes.contains(modi)) {
			index = misSolicitudes.indexOf(modi);
		}
		misSolicitudes.set(index, modificarSoli);

	}

	// Retornar cant. Universitarios desempleado
	public int desempleadoU() {
		int cant = 0;
		for (Solicitante soli : misSolicitantes) {
			if (!soli.isContratado()) {
				if (soli instanceof Universitario) {
					cant++;
				}
			}
		}
		return cant;
	}

	// Retornar cant. Tecnicos desempleado
	public int desempleadoT() {
		int cant = 0;
		for (Solicitante soli : misSolicitantes) {
			if (!soli.isContratado()) {
				if (soli instanceof Tecnico) {
					cant++;
				}
			}
		}
		return cant;
	}

	// Retornar cant. Obreros desempleado
	public int desempleadoO() {
		int cant = 0;
		for (Solicitante soli : misSolicitantes) {
			if (!soli.isContratado()) {
				if (soli instanceof Obrero) {
					cant++;
				}
			}
		}
		return cant;
	}

	// Retorna % de Obreros Contratados
	public float porcientoO() {
		float cant = 0;
		float porciento = 0;
		float total = totalContratado();
		for (Solicitante soli : misSolicitantes) {
			if (soli instanceof Obrero) {
				if (soli.isContratado()) {
					cant++;
				}
			}
		}
		if (total != 0) {
			porciento = (cant / total) * 100;
		}
		return porciento;
	}

	// Retorna % de Universitarios Contratados
	public float porcientoU() {
		float cant = 0;
		float porciento = 0;
		float total = totalContratado();
		for (Solicitante soli : misSolicitantes) {
			if (soli instanceof Universitario) {
				if (soli.isContratado()) {
					cant++;
				}
			}
		}
		if (total != 0) {
			porciento = (cant / total) * 100;
		}
		return porciento;
	}

	// Retorna % de Tecnicos Contratados
	public float porcientoT() {
		float cant = 0;
		float porciento = 0;
		float total = totalContratado();
		for (Solicitante soli : misSolicitantes) {
			if (soli instanceof Tecnico) {
				if (soli.isContratado()) {
					cant++;
				}
			}
		}
		if (total != 0) {
			porciento = (cant / total) * 100;
		}
		return porciento;
	}

	// Retorna total de contratados
	public int totalContratado() {
		int cant = 0;
		for (Solicitante soli : misSolicitantes) {
			if (soli.isContratado()) {
				cant++;
			}
		}
		return cant;
	}

	// Modificar solicitante
	public void updateSolicitante(Solicitante soli) {
		for (Solicitante solis : misSolicitantes) {
			if (solis.getCedula().equalsIgnoreCase(soli.getCedula())) {
				solis.setEstadoCivil(soli.getEstadoCivil());
				solis.setCategoriaLicencia(soli.getCategoriaLicencia());
				solis.setProvincia(soli.getProvincia());
				solis.setTelefono(soli.getTelefono());
				solis.setEmail(soli.getEmail());
				solis.setIdiomas(soli.getIdiomas());
				solis.setVehiculoPropio(soli.isVehiculoPropio());
				solis.setCalle(soli.getCalle());
				solis.setCiudad(soli.getCiudad());
				solis.setSector(soli.getSector());
				solis.setReferencia(soli.getReferencia());
				solis.setNumeroCasa(soli.getNumeroCasa());
			}
		}
	}

	// Retornar indice de Solicitate
	public int indexSolicitante(String code) {
		int index = 0;
		for (int i = 0; i < misSolicitantes.size(); i++) {
			if (misSolicitantes.get(i).getCodigo().equalsIgnoreCase(code)) {
				index = i;
			}
		}
		return index;
	}

	// Elimianr solicitante por codigo
	public void eliminarSolicitante(Solicitante persona) {
		int index = indexSolicitante(persona.getCodigo());
		misSolicitantes.remove(index);
	}

	// Contratar Candidatos
	public void contratarCandidatos(Solicitud solicitud,
			ArrayList<Solicitante> empleados) {
		Empresa empresa = solicitud.getEmpresa();
		for (Solicitante solicitante : empleados) {
			solicitud.IncreasedCantReal();
			empresa.insertContratado(solicitante);
		}

	}
	
	public float porcientoSolicitud(Solicitud soli){
		float por=0;
		if(soli.getCantReal()!=soli.getCantVacantes()){
		por= (soli.getCantVacantes()/soli.getCantReal())*100;
	}else{
		por= 0.0f;
		}
		return por;
	}
	// retorna una solicitud dado el rnc de una empresa
	public Solicitud RetornaEmpresaSoli (String rnc){
		Solicitud soli=null;
		for (Solicitud solicitud : misSolicitudes) {
			if(solicitud.getEmpresa().getRNC().equalsIgnoreCase(rnc)){
				soli=solicitud;
			}
			
		}
		return soli;
		
	}
	public boolean EmpresaExiste(String rnc){
		boolean existe=false;
		for (Empresa empresa : misEmpresas) {
			if(empresa.getRNC().equalsIgnoreCase(rnc)){
				existe = true;
			}
		}
		return existe;
		
	}

	
	// escribe fichero texto de una empresa
	public void writeEmpresaTXT(String rnc) throws IOException{
		writer_1 = new FileWriter(new File("cliente.txt"));
		Solicitud soli =RetornaEmpresaSoli(rnc);
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.write("*                               Bolsa Laboral                                     *"+"\n");
		writer_1.write("*                                   Empresa                                       *"+"\n");
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.write("Empresa: "+soli.getEmpresa().getNombre()+"\n");
		writer_1.write("Cantidad Vacantes inicial: "+soli.getCantReal()+"\n");
		writer_1.write("Cantidad Vacantes real: "+soli.getCantVacantes()+"\n");
		writer_1.write("Cantidad Empleados: "+soli.getCantReal()+"\n");
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.close();
		 
	
}
//buscar solicitante por cedula
	
	public Solicitante BuscarSoliCedula(String Cedula){
		Solicitante solicitante =null;
		for (Solicitante soli : misSolicitantes) {
			if(soli.getCedula().equalsIgnoreCase(Cedula)){
				solicitante=soli;
			}
			
		}
		return solicitante;
	}
	
	
	
	
		
		// retorna si existe un solicitante
		public boolean SolicitanteExiste(String cedula){
			boolean existe = false;
			for (Solicitante solicitante : misSolicitantes) {

				if(solicitante.getCedula().equalsIgnoreCase(cedula)){
					existe = true;
				}
			}
			return existe;
		}
		
		// retorna el tipo de solicitante
		public String tipoSolicitante(Solicitante soli){
		String solici=null;

			if(soli instanceof Obrero){
				solici="Obrero";
				
			}
			if(soli instanceof Universitario){
				solici="Universitario";
				
			}
			if(soli instanceof Tecnico){
				solici="Tecnico";
				
			}
				
		return solici;
		
		}
		
		// retorna si esta contratado o no
		
		public String contradato(Solicitante soli){
			String estado=null;
			if(soli.isContratado()){
				estado="Contratado";
				
			}else{
				estado="Desempleado";
			}
			return estado;
		}
		
		// retorna cantidad de solicitudes del solicitane
		public int cantidadSolicitante(Solicitante soli){
			int cant=0;
			for (Solicitante misoli : misSolicitantes) {
				if(soli.getCedula().equalsIgnoreCase(misoli.getCedula())){
					cant++;
				}
				
			}
			return cant;
			
		}
	public void writeSolicitanteTXT(String cedula) throws IOException{
		writer_1 = new FileWriter(new File("cliente.txt"));
		Solicitante soli =BuscarSoliCedula(cedula);
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.write("*                               Bolsa Laboral                                     *"+"\n");
		writer_1.write("*                                 Empleado                                        *"+"\n");
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.write("Nombres: "+soli.getNombres()+"\n");
		writer_1.write("Apellidos: "+soli.getApellidos()+"\n");
		writer_1.write("Tipo Solicitante: "+tipoSolicitante(soli)+"\n");
		writer_1.write("Estado: "+contradato(soli)+"\n");
		writer_1.write("Cantidad de Solicitudes: "+cantidadSolicitante(soli)+"\n");
		writer_1.write("***********************************************************************************"+"\n");
		writer_1.close();
		 
	}

}
