package dao;

import java.sql.SQLException;

public interface DVDLendInfoDAO {
	public void lend(int dvdId) throws SQLException;
	public void returnBack(int id) throws SQLException;
}
