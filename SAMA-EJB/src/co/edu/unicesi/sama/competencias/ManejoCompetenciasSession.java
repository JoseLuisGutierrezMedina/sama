package co.edu.unicesi.sama.competencias;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.unicesi.sama.bo.BloqueBO;
import co.edu.unicesi.sama.bo.BloquesMateriaPKBO;
import co.edu.unicesi.sama.bo.MateriaBO;
import co.edu.unicesi.sama.bo.ProgramaBO;
import co.edu.unicesi.sama.dbutil.DBUtil;
import co.edu.unicesi.sama.entidades.Bloque;

import co.edu.unicesi.sama.entidades.Lineadecompetencia;
import co.edu.unicesi.sama.entidades.Materia;
import co.edu.unicesi.sama.entidades.Programa;
import co.edu.unicesi.sama.exception.SamaException;

/**
 * Session Bean implementation class ManejoProgramaSession
 */
@Stateless
public class ManejoCompetenciasSession implements ManejoCompetenciasRemote, ManejoCompetenciasLocal  {

	@PersistenceContext(unitName = DBUtil.PU_DT)
	protected EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public ManejoCompetenciasSession() {
        // TODO Auto-generated constructor stub
    }

	



	@Override
	public String crearAsociacionLineaProfesional(String linea, String programa)
			throws SamaException {
		
		try{
		// TODO Auto-generated method stub
		TypedQuery<Lineadecompetencia> respProgramas = entityManager.createNamedQuery(
				"buscarLineaDeCompetenciaPorIdLinea", Lineadecompetencia.class);
		respProgramas.setParameter("id", "%"+Integer.parseInt(linea)+ "%");
		List<Lineadecompetencia> lineas = respProgramas.getResultList();
		
		
		TypedQuery<Programa> respProgramas2 = entityManager.createNamedQuery(
				"buscarProgramaporId", Programa.class);
		respProgramas2.setParameter("programa", "%"+ Integer.parseInt(programa) + "%");
		List<Programa> programas = respProgramas2.getResultList();
		
		
		Lineadecompetencia lineaObj=lineas.get(0);
		Programa progObj=programas.get(0);
		
		String retorno="";
		if(!lineaObj.getProgramas().contains(progObj)){
		lineaObj.getProgramas().add(progObj);
		
		entityManager.persist(lineaObj);
		
		
		entityManager.flush();
		
		 return "Asociacion creada con exito";
		
		}else{
		retorno="Error";
		}
		return retorno;
		
		
		
		
	}catch(Exception e){
		e.printStackTrace();
		throw new SamaException("Error");
		
	}
	}






	@Override
	public String eliminarAsociacionLineaProfesional(String linea,
			String programa) throws SamaException {
		// TODO Auto-generated method stub

		try{		
			TypedQuery<Lineadecompetencia> respProgramas = entityManager.createNamedQuery(
					"buscarLineaDeCompetenciaPorIdLinea", Lineadecompetencia.class);
			respProgramas.setParameter("id", "%"+Integer.parseInt(linea)+ "%");
			List<Lineadecompetencia> lineas = respProgramas.getResultList();
			
			
			TypedQuery<Programa> respProgramas2 = entityManager.createNamedQuery(
					"buscarProgramaporId", Programa.class);
			respProgramas2.setParameter("programa", "%"+ Integer.parseInt(programa) + "%");
			List<Programa> programas = respProgramas2.getResultList();
			
			
			Lineadecompetencia lineaObj=lineas.get(0);
			Programa progObj=programas.get(0);
			
			String retorno="";
			
				
				
			lineaObj.getProgramas().remove(progObj);
			
			
			
			
			entityManager.persist(lineaObj);
			
			entityManager.flush();
			
			 return "Asociacion eliminada con exito";
			
		
			
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new SamaException("Error");
			
		}
	}




}
