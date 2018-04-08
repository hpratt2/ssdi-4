package winevault.service;

import java.util.List;

import winevault.dao.IWineDAO;
import winevault.dao.WineDAO;
import winevault.model.IWine;
import winevault.util.ConnectionData;

public class WineListService {
	private IWineDAO dao;
	
	public List<IWine> getWineList(){
		dao = new WineDAO(new ConnectionData());
		return dao.getWineList();
	}
}
