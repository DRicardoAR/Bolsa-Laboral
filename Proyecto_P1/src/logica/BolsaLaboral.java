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
	public void insertEmpresa(Empresa empresa){
		misEmpresas.add(empresa);
	}
	public void insertSolicitante(Solicitante pSolicitante){
		String code = getCodeSolicitante();
		pSolicitante.setCodigo(code);
		misSolicitantes.add(pSolicitante);
	}
	public void insertSolicitud(Solicitud pSolicitud){
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
		codigo ="S"+code;
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
		codigo ="SO"+code;
		return codigo;
	}

}
