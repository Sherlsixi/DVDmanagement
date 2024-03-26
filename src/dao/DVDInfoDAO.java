package dao;

import java.sql.SQLException;
import java.util.List;
import model.DVDInfoModel;
import utils.State;

public interface DVDInfoDAO {
	public void add(String name) throws SQLException;
	public void delete(int id) throws SQLException;
	public List<DVDInfoModel> list() throws SQLException;
	public State getDVDState(int id) throws Exception;
}
