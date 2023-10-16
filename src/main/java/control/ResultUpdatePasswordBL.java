package control;

public class ResultUpdatePasswordBL {
	
	public String getUserId(String ploginId) {
		
		String result = "";
		
		LoginDAO loginDAO = new LoginDAO();
		result = loginDAO.findLoginIdtoId(ploginId);
		
		return result;
	}
	
	public boolean checkLoginIdLength(String pLoginId) {
		boolean result = false;
		
		if(pLoginId.length() > 4) {
			result = true;
		}
		
		return result;
	}
	
	public void updateUserPassword(String pUserId,String pLoginname,String pPassword){
		
		//新規ログインIDとパスワードの登録
		LoginDAOInsertUpdate insDAO = new LoginDAOInsertUpdate();
		insDAO.updateUserPassword(pUserId, pLoginname, pPassword);
	}
	
	public void updatePassword(String pUserId,String pPassword){
		
		//パスワードの更新
		LoginDAOInsertUpdate upDAO = new LoginDAOInsertUpdate();
		upDAO.updatePassword(pUserId,pPassword);
	}
	
}
