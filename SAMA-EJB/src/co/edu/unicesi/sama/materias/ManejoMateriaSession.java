package co.edu.unicesi.sama.materias;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.unicesi.sama.bo.MateriaBO;
import co.edu.unicesi.sama.bo.ProgramaBO;
import co.edu.unicesi.sama.dbutil.DBUtil;
import co.edu.unicesi.sama.entidades.Materia;
import co.edu.unicesi.sama.entidades.Programa;
import co.edu.unicesi.sama.exception.SamaException;

/**
 * Session Bean implementation class ManejoProgramaSession
 */
@Stateless
public class ManejoMateriaSession implements ManejoMateriaRemote,
		ManejoMateriaLocal {

	@PersistenceContext(unitName = DBUtil.PU_DT)
	protected EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManejoMateriaSession() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String crearMateria(MateriaBO materia) throws SamaException {
		try {
			System.out.println("Entra a crearMateria");
			Materia mat = new Materia();
			mat.setNombre(materia.getNombre());
			mat.setIdMateria(materia.getIdMateria());
			mat.setDescripcion(materia.getDescripcion());
			mat.setFacultade(null);

			System.out.println("M�todo crearMateria del Bean - Nombre: "
					+ materia.getNombre() + ", ID: " + materia.getIdMateria()
					+ ", Descripci�n: " + materia.getDescripcion());
			if(!entityManager.contains(mat)){
				
				entityManager.persist(mat);
				System.out.println("Pasa el merge");
				entityManager.flush();
				return materia.getNombre();		
			}
			
			return "Error";
			
		} catch (Exception e) {

			throw new SamaException("Error creando la materia");

		}
	}

}
