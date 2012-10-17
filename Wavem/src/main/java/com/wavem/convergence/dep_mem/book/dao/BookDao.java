package com.wavem.convergence.dep_mem.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("bookDao")
public class BookDao extends SqlMapClientDaoSupport {

	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient); //설정정보 넘김
	}
	
	public String saveBook(Map<String, String> paraMap) {
		String ret = getSqlMapClientTemplate().update("Book.saveBook", paraMap) + "";
		return ret;
	}
	
	public List<Map> searchBook(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Book.searchBook", paraMap);
		
		return ret;
	}
	
	public String updateBook(Map<String, String> paraMap){
		
		String ret = "";
		String ret1 = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClientTemplate().getSqlMapClient().startTransaction();
			super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClientTemplate().getSqlMapClient().startBatch();

			ret = getSqlMapClientTemplate().getSqlMapClient().update("Book.updateBok_tab", paraMap) + "";
			ret1 = getSqlMapClientTemplate().getSqlMapClient().update("Book.updateMem_bok", paraMap) + "";

			super.getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
			super.getSqlMapClientTemplate().getSqlMapClient().executeBatch();
			super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().commit();
			ret = "1";
		} catch (Exception e) {
			try {
				super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
}




















