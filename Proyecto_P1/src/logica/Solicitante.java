package logica;

import java.time.LocalDate;

public abstract class Solicitante {
	protected String codigo;
	protected String cedula;
	protected String nombres;
	protected String apellidos;
	protected String telefono;
	protected LocalDate FechaNacimiento;
	protected String nacionalidad;
	protected String sexo;
	protected String estadoCivil;
	protected String direccion;
	protected String provincia;
	protected String email;
	protected boolean vehiculoPropio;
	protected boolean licencia;
	protected int categoriaLicencia;
	protected int annosExperiencia;
	protected String idiomas;
	protected boolean contratado;
	protected int edad;
	protected boolean mudarse;

	public Solicitante(String cedula, String nombres, String apellidos, String telefono, LocalDate fechaNacimiento,
			String nacionalidad, String sexo, String estadoCivil, String direccion, String provincia, String email,
			boolean vehiculoPropio, boolean licencia, int categoriaLicencia, int annosExperiencia, String idiomas,
			boolean mudarse) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		FechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.direccion = direccion;
		this.provincia = provincia;
		this.email = email;
		this.vehiculoPropio = vehiculoPropio;
		this.licencia = licencia;
		this.categoriaLicencia = categoriaLicencia;
		this.annosExperiencia = annosExperiencia;
		this.idiomas = idiomas;
		this.mudarse = mudarse;
	}

	public boolean isMudarse() {
		return mudarse;
	}

	public void setMudarse(boolean mudarse) {
		this.mudarse = mudarse;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVehiculoPropio() {
		return vehiculoPropio;
	}

	public void setVehiculoPropio(boolean vehiculoPropio) {
		this.vehiculoPropio = vehiculoPropio;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public int getCategoriaLicencia() {
		return categoriaLicencia;
	}

	public void setCategoriaLicencia(int categoriaLicencia) {
		this.categoriaLicencia = categoriaLicencia;
	}

	public int getAnnosExperiencia() {
		return annosExperiencia;
	}

	public void setAnnosExperiencia(int annosExperiencia) {
		this.annosExperiencia = annosExperiencia;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public boolean isContratado() {
		return contratado;
	}

	public void setContratado(boolean contratado) {
		this.contratado = contratado;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
