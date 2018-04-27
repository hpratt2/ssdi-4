package winevault.service;

import java.util.List;

import winevault.dao.IWineDAO;
import winevault.dao.WineDAO;
import winevault.model.IWine;
import winevault.util.*;

public class WineService implements IWineService{
	private IWineDAO dao = new WineDAO(new ConnectionDataTestLarge());
	
	public List<IWine> getWineList(){
		return dao.getWineList();
	}
	
	public IWine getWineByID(int id) {
		return dao.getWineByID(id);
	}
}
