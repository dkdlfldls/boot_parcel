package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.util.Page;

public interface MachineRepository {
	
	public int createMachine(Machine machine);
	
	public Machine findMachineByIdx(Machine machine);
	public List<Machine> findMachineListByPage(Page page);
	
	public int updateMachine(Machine machine);
	
	public int completelyDeleteMachine(Machine machine);
	public int showingDeleteMachine(Machine machine);
	
	public int countAllMachine();

	public List<Machine> findMachineList();
}
