package com.wavem.convergence.dep_mem.equipment.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("equipmentDao")
public class EquipmentDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);	//설정정보를 넘김
	}
	
	public String saveEquipment(Map<String, String> paraMap){
		
		String ret = getSqlMapClientTemplate().update("Equipment.saveEquipment2", paraMap) + "";
		
		return ret;
	}
	
	public List<Map> searchEquipment(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Equipment.searchEquipment", paraMap);
		
		return ret;	
	}
	
	public String updateEquipment(Map<String, String> paraMap){
		
		String ret = "";
		String ret1 = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();

			ret = getSqlMapClient().update("Equipment.updateEqu_tab", paraMap) + "";
			ret1 = getSqlMapClient().update("Equipment.updateMem_equ", paraMap) + "";

			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			
			ret = "1";
			
		} catch (Exception e) {
			try {
				super.getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				super.getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}	
}
