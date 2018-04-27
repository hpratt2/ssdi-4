package winevault.service;

import java.util.List;
import winevault.model.IWine;

public interface IWineService {
	public List<IWine> getWineList();

	public IWine getWineByID(int id);
}