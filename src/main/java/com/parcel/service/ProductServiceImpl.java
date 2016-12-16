package com.parcel.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.parcel.entity.Machine;
import com.parcel.entity.Message;
import com.parcel.entity.Product;
import com.parcel.entity.User;
import com.parcel.repository.MessageRepository;
import com.parcel.repository.ProductRepository;
import com.parcel.repository.UserRepository;
import com.parcel.util.TextMaker;
import com.parcel.util.LogProperties;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private LogService logService;
	@Autowired
	private LogProperties prop;
	@Autowired
	private TextMaker logMaker;
	
	
	public static final boolean LOCK = false;
	public static  final boolean OPEN = true;
	
	@Override
	public List<Machine> getMachineList() {
		System.out.println("=====getMachineList process in ProductServiceImpl=====");
		return productRepository.findAllMachineList();
	}

	@Override
	public String registerProductByUser(Product product) {
		//1. 등록이 되어있는지 부터 확인한다.
		//2. 등록이 되어있다면 등록된 제품이라고 알려주고 등록이 안되어 있다면 update를 시작한다.
		//3. 업데이트에 실패하면 입력 확인하라고 알려준다.
		//4. 성공하면 성공했다고 알려준다.
		System.out.println("=====addProduct process in ProductServiceImpl=====");
		Product temp = productRepository.findProductByMachineAndMachine_code(product);
		if (temp == null) {
			return "입력 정보를 확인하여 주세요";
		} else {
			System.out.println("@@@@" + temp.toString());
			if (temp.getRegistrant() > 0) {
				return "이미 등록된 제품입니다.";
			} else {
				//등록 시작
				int result = productRepository.updateProduct(product);
				if (result > 0 ) {
					logService.addLog(logMaker.registerProduct(product.getRegistrant(), temp.getIdx()), temp, prop.getInt("addProduct"));
					return "등록 완료";
				} else {
					return "입력 정보를 확인하여 주세요";
				}
			}
		}
		
	}

	@Override
	public Product getProductInfo(int pidx) {
		System.out.println("=====getProductInfo process in ProductServiceImpl=====");
		return productRepository.findProductByPidx(pidx);
	}

	@Override
	@Transactional
	public boolean lock(User user) {
		// TODO Auto-generated method stub
		try {
			//1. 누가 잠그려는지 알아야한다.
			User lock_user = userRepository.findUserByIdx(user.getIdx());
			//2. 잠근다.
			productRepository.updateProductByIdxForLock(user.getProductIdx(), LOCK);
			logService.addLog(logMaker.lockProduct(user.getIdx(), user.getProductIdx()), user, prop.getInt("productLock"));
			//변경한 시간
			Message message = new Message();
			String msg = (new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분").format(new Timestamp(System.currentTimeMillis()))) 
					+ " " + lock_user.getName() + "이 " + user.getProductName() + "을 잠궜습니다.";
			message.setMessage(msg);
			//3. 그룹이 있는지 확인한다. 
			if (user.isHasGroup()) {
				//그룹이 있으면 그룹원 전체에게 메시지를 보내야겠고
				messageRepository.insertMessageForGroupByProductIdx(message, user.getProductIdx());
			} else {
				//그룹원이 없으면 자기 자신에게만 메시지를 보내면 되겠지
				messageRepository.insertMessageByUserIdx(message, user.getIdx());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //try catch 안에서 트랜잭션 롤백 시키는 방법
			return false;
		}
		//3. 잠근 사람과 잠근 시간등등을 해당 그룹에 속해있는 사람들에게 메시지로 보낸다.
		//3. 그룹이 없는경우 자기 자신에게만 메시지를 보낸다.
	}

	@Override
	@Transactional
	public boolean open(User user) {
		// TODO Auto-generated method stub
		try {
			//누가 여는가?
			User open_user = userRepository.findUserByIdx(user.getIdx());
			//열기
			productRepository.updateProductByIdxForLock(user.getProductIdx(), OPEN);
			logService.addLog(logMaker.unlockProduct(user.getIdx(), user.getProductIdx()), user, prop.getInt("productUnlock"));
			//변경한 시간
			Message message = new Message();
			String msg = (new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분").format(new Timestamp(System.currentTimeMillis()))) 
					+ " " + open_user.getName() + "이 " + user.getProductName() + "을 열었습니다.";
			message.setMessage(msg);
			//그룹이 있나없나에 따른 다른 처리
			if (user.isHasGroup()) {
				//productIdx를 가지고 user_group에서 user_group의 idx를 찾고 그 idx로 group_member에서 해당 group인 사람들에게 전부 메시지를 넣는다.
				messageRepository.insertMessageForGroupByProductIdx(message, user.getProductIdx());
			} else {
				messageRepository.insertMessageByUserIdx(message, user.getIdx());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //try catch 안에서 트랜잭션 롤백 시키는 방법
			return false;
		}
		
	}

}
