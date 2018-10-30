package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPaqueteDeProductosPromo {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;

	public SQLPaqueteDeProductosPromo(PersistenciaSuperAndes persistenciaSuperAndes) {
		
		this.pp = persistenciaSuperAndes;
	}

	public long adicionarPaquete(PersistenceManager pm, String codigoProducto, int precioConjunto) {
		
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaPaqueteDeProductosPromo()+" (CODIGOPROMO, PRECIOPROMO) VALUES (?,?)");
		q.setParameters(codigoProducto, precioConjunto);
		return (long) q.executeUnique();
		
	}
	
	

}
