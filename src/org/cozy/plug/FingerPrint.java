package org.cozy.plug;



public class FingerPrint
{
	/* COMMENT BECAUSE OF JDBC */
	 
	int fId;
	Plug p;
	
	public FingerPrint(Plug test) {
		// TODO Auto-generated constructor stub
		p = test;
		fId = -1;
	}
	public void activate_fp()
	{
		try {
			p.Fingerprint_Activate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void desactivate_fp()
	{
		try {
			p.Fingerprint_Deactivate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unauthenticate_fp()
	{
		try {
			p.Fingerprint_Deauthenticate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int auth_fp()
	{
		try {
			System.out.println( "Please authenticate with your finger" );
			
			while ( (fId = p.Fingerprint_GetAuthenticatedId()) == -1 )
			{
				Thread.sleep( 1000 );
			}
			System.out.println( String.format( "Fingerprint ID:%d authenticated", fId ) );
			Thread.sleep( 2500 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fId;
	}
	
	
}
