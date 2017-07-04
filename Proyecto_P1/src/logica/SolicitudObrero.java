package logica;

import java.util.ArrayList;



public class SolicitudObrero extends Solicitud {
	ArrayList<String> Habilidades;

	public SolicitudObrero(int cantVacantes, int annosExperiencia, int edadMax,
			int edadMin, String tipoContrato, boolean vehiculoPropio,
			String localidad, Empresa empresa, boolean mudarse,ArrayList<String> Habilidades) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato,
				vehiculoPropio, localidad, empresa, mudarse);
		this.Habilidades= new ArrayList<String>();
	
	}

	public ArrayList<String> getHabilidades() {
		return Habilidades;
	}

	public void setHabilidades(ArrayList<String> habilidades) {
		Habilidades = habilidades;
	}

}
