package co.edu.unicesi.sama.programas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.unicesi.sama.bo.ProgramaBO;
import co.edu.unicesi.sama.dbutil.DBUtil;
import co.edu.unicesi.sama.entidades.Programa;
import co.edu.unicesi.sama.exception.SamaException;

/**
 * Session Bean implementation class ManejoProgramaSession
 */
@Stateless
public class ManejoProgramaSession implements ManejoProgramaRemote, ManejoProgramaLocal  {

	@PersistenceContext(unitName = DBUtil.PU_DT)
	protected EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ManejoProgramaSession() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String crearPrograma(ProgramaBO programa) throws SamaException {
		
		try{
			System.out.println("Entra a crearPrograma");
			Programa prog = new Programa();
			prog.setNombre(programa.getNombre());
			prog.setIdPrograma(programa.getIdPrograma());
			prog.setDescripcion(programa.getDescripcion());
			prog.setFacultade(null);
			
			System.out.println("M�todo crearPrograma del Bean - Nombre: " + 
			programa.getNombre() + ", ID: " + programa.getIdPrograma() + ", Descripci�n: " + 
			programa.getDescripcion());
			if(!entityManager.contains(prog)){
				
				entityManager.persist(prog);
				System.out.println("Pasa el merge");
				entityManager.flush();
				return programa.getNombre();
			}
			
			return "Error";
			
		}catch(Exception e){
			
			throw new SamaException("Error creando el programa");
			
		}
	}

}
