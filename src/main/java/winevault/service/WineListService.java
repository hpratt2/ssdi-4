package winevault.service;

import java.util.List;

import winevault.dao.IWineDAO;
import winevault.dao.WineDAO;
import winevault.model.IWine;
import winevault.util.*;

public class WineListService implements IWineListService{
	private IWineDAO dao = new WineDAO(new ConnectionDataTestLarge());
	
	public List<IWine> getWineList(){
		return dao.getWineList();
	}
}
