package logica;

import java.util.ArrayList;

public class SolicitudObrero extends Solicitud {
	private ArrayList<String> habilidades;

	public SolicitudObrero(int cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse, ArrayList<String> idiomas,
			int categoriaLicencia, ArrayList<String> habilidades) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato, vehiculoPropio, localidad, empresa,
				mudarse, idiomas, categoriaLicencia);
		this.habilidades = new ArrayList<>();
	}

	public ArrayList<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<String> habilidades) {
		this.habilidades = habilidades;
	}

}
