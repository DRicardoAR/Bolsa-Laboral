package logica;

import java.util.ArrayList;

public class BolsaLaboral {
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Solicitante> misPersonas;
	private ArrayList<Solicitud> misSolicitudes;
	private static BolsaLaboral bolsa;

	public BolsaLaboral() {
		super();
		this.bolsa = null;
		this.misEmpresas = new ArrayList<>();
		this.misPersonas = new ArrayList<>();
		this.misSolicitudes = new ArrayList<>();
	}

	public ArrayList<Empresa> getMisEmpresas() {
		return misEmpresas;
	}

	public ArrayList<Solicitante> getMisPersonas() {
		return misPersonas;
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

}
