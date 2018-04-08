package winevault.dao;

import java.util.List;

import winevault.model.IWine;

public interface IWineDAO {
	public void addWine(IWine wine);
	public List<IWine> getWineList();
}
