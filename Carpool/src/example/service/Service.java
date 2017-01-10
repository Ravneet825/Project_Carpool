package example.service;

import java.util.ArrayList;

import example.dao.CarpoolDao;
import example.dao.UserDao;
import example.domain.Carpool;
import example.domain.Register;

public class Service {

	UserDao userDao = new UserDao();
	CarpoolDao carpoolDao = new CarpoolDao();
	public boolean validateLogin(String username , String password){
		
		return false;
	}

	public void registerNewUser(Register registerUser) {
		
		userDao.registerNewUser(registerUser);
	}
	public void addCarpool(Carpool carpool) {
		
		carpoolDao.addCarpool(carpool);
	}
	public ArrayList<Carpool> searchCarpool(Carpool carpool) {
		
		return carpoolDao.searchCarpool(carpool);
	}
}
