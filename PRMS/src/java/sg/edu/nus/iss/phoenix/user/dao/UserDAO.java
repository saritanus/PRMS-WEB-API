package sg.edu.nus.iss.phoenix.user.dao;

import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

/**
 * @author vimal raj
 * @version 1.0
 * @created 18-Sep-2017 10:56:45 PM
 */
public interface UserDAO {

	public void deleteUser();

	public void loadAll();

}