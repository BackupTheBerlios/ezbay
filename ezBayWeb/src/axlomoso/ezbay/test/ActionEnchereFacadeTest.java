package axlomoso.ezbay.test;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import junit.framework.TestCase;

public class ActionEnchereFacadeTest extends TestCase {

	public ActionEnchereFacadeTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static boolean equalsDTO(ActionEnchereDTO enchereDTO1, ActionEnchereDTO enchereDTO2,boolean testerId){
		boolean tRes = true;
		if (testerId){
			tRes = tRes && ( (enchereDTO1.getId()).equals(enchereDTO1.getId()) );
		}

		tRes = tRes && ( 
				( enchereDTO1.getDate() == null) && ( enchereDTO2.getDate() == null)
				|| ( enchereDTO1.getDate().equals(enchereDTO2.getDate())  )
				);
		/*tRes = tRes && ( 
				( enchereDTO1.getDateLimite() == null) && ( enchereDTO2.getDateLimite() == null)
				|| ( enchereDTO1.getDateLimite().equals(enchereDTO2.getDateLimite())  )
				);*/
		tRes = tRes && ( 
				( enchereDTO1.getMontant() == null) && ( enchereDTO2.getMontant() == null)
				|| ( enchereDTO1.getMontant().equals(enchereDTO2.getMontant())  )
				);
		
		return tRes;
	}
	
}
