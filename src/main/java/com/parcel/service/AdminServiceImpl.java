package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.parcel.entity.Log;
import com.parcel.entity.Machine;
import com.parcel.repository.LogRepository;
import com.parcel.repository.MachineRepository;
import com.parcel.util.Page;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private MachineRepository machineRepository;
	
	@Override
	public List<Log> getLogList(Page page) {
		// TODO Auto-generated method stub
		if (page.getCategory() == 0) {
			page.setPageInfo(logRepository.findCountAllLog());
			return logRepository.findLogByPage(page);
		} else {
			page.setPageInfo(logRepository.findCountLog(page));
			return logRepository.findLogByPageAndCategory(page);
		}
		
	}

	@Override
	public List<Log> getSearchResultOfLogList(Page page) {
		// TODO Auto-generated method stub
		if (page.getCategory() == 0 ) {
			page.setPageInfo(logRepository.findCountLogBySearch(page));		
			return logRepository.findLogByPageAndSearch(page);
		} else {
			page.setPageInfo(logRepository.findCountLogBySearchAndCategory(page));
			return logRepository.findLogByPageAndSearchAndCategory(page);
		}
		
		
	}

	@Override
	public List<Machine> getMachineListByPage(Page page) {
		// TODO Auto-generated method stub
		page.setPageInfo(machineRepository.countAllMachine());
		return machineRepository.findMachineListByPage(page);
	}

	@Override
	public List<Machine> getMachineList() {
		// TODO Auto-generated method stub
		return machineRepository.findMachineList();
	}

	@Override
	public boolean modifyMachineName(Machine machine) {
		Machine temp = machineRepository.findMachineByIdx(machine);
		if (temp != null) {
			temp.setMachine_name(machine.getMachine_name());
			if (machineRepository.updateMachine(temp) > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addMachine(Machine machine) {
		if (machineRepository.createMachine(machine) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteMachine(Machine machine) {
		if (machineRepository.showingDeleteMachine(machine) > 0) {
			return true;
		} else {
			return false;
		}
	}

}
