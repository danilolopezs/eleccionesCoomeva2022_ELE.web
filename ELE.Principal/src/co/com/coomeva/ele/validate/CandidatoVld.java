package co.com.coomeva.ele.validate;

import java.sql.Connection;

public class CandidatoVld {

	private boolean flag=false;
	private static CandidatoVld instance;

	//Constructor de la clase
	private CandidatoVld() {
	}

	//Patròn Singular
	public static CandidatoVld getInstance() {
		if (instance == null) {
			instance = new CandidatoVld();
		}
		return instance;
	}
	
	/*public boolean validateAsesorMP(Connection p_conn,String p_index,String p_planchaEspecial) throws Exception
	{
		objAsesorMP = new AsesorMP();
		boolean vb_return=true;
				
		vb_return=objAsesorMP.exists(p_conn,p_index);
		
		if ((vb_return) && p_planchaEspecial.equals("SI"))
		{
			flag=true;	
			vb_return=false;
		}
		
		return vb_return;
	}
	
	public boolean validateAsesorFin(Connection p_conn,String p_index,String p_planchaEspecial) throws Exception
	{
		objAsesorFin = new AsesorFin();
		AsesorLicoVo vo =null;
			vo=objAsesorFin.getAsesorLico(p_conn,p_index);
			boolean vb_return=true;
			if (vb_return)
				flag=true;
			int tiempoRetiro =vo.getAnosRetiro();
			
			if (vo.getCDGO().equals(""))
				return false;
			
			if (!(vo.getEPR_CDGO().equals("A")) && (tiempoRetiro <3)&& !p_planchaEspecial.equals("SI"))
			{
				vb_return= true;
			}
			else 
			{
				if (p_planchaEspecial.equals("SI"))
				{
					if (flag)
						vb_return=false;
					else
						vb_return=true;
				}
				else
					vb_return=true;
				
			}
				
			return vb_return;
			
	}
	
	public boolean validateAsesor(Connection p_conn,String p_index,String p_planchaEspecial) throws Exception
	{
		objAsesor = new Asesor();
		AsesorVo vo =null;
			vo=objAsesor.getAsesor(p_conn,p_index);
			boolean vb_return=true;
			if (vb_return)
				flag=true;

			if (vo.getNro_identificacion().equals(""))
				return false;
			
			if ((vo.getRetirado()).equals("S"))
			{
				vb_return= true;
			}
			else 
			{
				if (p_planchaEspecial.equals("SI") && !(vo.getRetirado()).equals("S"))
					if (flag)
						vb_return=false;
					else
						vb_return=true;

				else
					vb_return=true;
			}
				
			return vb_return;
			
	}
	
	public boolean validateEmpleado(Connection p_conn,String p_index,String p_planchaEspecial) throws Exception
	{
		objEmpleado = new Empleado();
		EmpleadoVo vo = null;
		vo=objEmpleado.datosEmpleado(p_conn,p_index);
		boolean vb_return=true;
		if (vb_return && !vo.getEstado().equals("R"))
			flag=true;

		int tiempoRetiro =vo.getTiempoRetiro();
		
		if (vo.getCedula().equals(""))
			return false;
		
		if ((vo.getEstado().equals("R"))&& !p_planchaEspecial.equals("SI"))
		{
			if ( tiempoRetiro <3 && !flag )
				vb_return= true;
			else
				vb_return= false;
		}
		else 
		{
			if (p_planchaEspecial.equals("SI")&& (!(vo.getEstado().equals("R")))&& flag)

					vb_return=false;

			else
				if (p_planchaEspecial.equals("SI") && flag)
					vb_return=false;
				else
					vb_return=true;
			
			
		}
		
		return vb_return;
			
	}
	
	public boolean validatePlancha(Connection p_conn,String p_index) throws Exception
	{
		objPlancha = new Plancha();
		return objPlancha.exists((PooledConnection)p_conn,p_index);
	}
	
	public boolean validateSubcomision(Connection p_conn,String p_index) throws Exception
	{
		objRepresentante = new Representante();
		return objRepresentante.exists(p_conn,p_index);
	}
	
	public boolean validateCandidato(Connection p_conn,String p_index) throws Exception
	{
		objPlancha = new Plancha();
		boolean b_returnPpal=false;
		boolean b_returnSplente=false;
		boolean b_return=false;
		b_returnPpal= objPlancha.AnotherPrincipal( (PooledConnection)p_conn ,p_index);
		b_returnSplente=objPlancha.AnotherSuplente((PooledConnection)p_conn,p_index);
		if (b_returnPpal || b_returnSplente)
		{
			b_return=true;
		}
		return b_return;
	}*/

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}
