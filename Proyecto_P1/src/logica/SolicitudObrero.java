package logica;

import java.util.ArrayList;

public class SolicitudObrero extends Solicitud {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> habilidades = new ArrayList<>();

	public SolicitudObrero(float cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse, ArrayList<String> idiomas,
			int categoriaLicencia, ArrayList<String> habilidades) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato, vehiculoPropio, localidad, empresa,
				mudarse, idiomas, categoriaLicencia);
		for (String habili : habilidades) {
			this.habilidades.add(habili);
			
		}
	}

	public ArrayList<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<String> habilidades) {
		this.habilidades = habilidades;
	}

}
