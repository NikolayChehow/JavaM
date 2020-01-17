package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {


    private UserService() {
    }

    public static class SingletonHolder {
        public static final UserService HOLDER_INSTANCE = new UserService();
    }

    public static UserService instance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());


    public List<User> getAllUsers() {
        return new ArrayList<>(dataBase.values());
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        if (isExistsThisUser(user)) {
            return false;
        }
        Long id = maxId.getAndIncrement();
        user.setId(id);
        dataBase.put(id,user);

            return true;
        }


        public void deleteAllUser () {
            dataBase.clear();
//        maxId.set(0);

        }

        public boolean isExistsThisUser (User user){
        Iterator<Map.Entry<Long, User>> iterator = dataBase.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, User> pair =  iterator.next();
            User userInBase =  pair.getValue();
            if (userInBase.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
        }

        public List<User> getAllAuth () {
            return new ArrayList<>(authMap.values());
        }

        public boolean authUser (User user){

            Iterator<Map.Entry<Long, User>> iterator = dataBase.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, User> pair =  iterator.next();
                User userInBase =  pair.getValue();
                if (userInBase.getEmail().equals(user.getEmail())&&userInBase.getPassword().equals(user.getPassword())) {

//                    Long id = pair.getValue().getId();
//                    user.setId(id);
//                    authMap.put(id,user);
                    authMap.put(userInBase.getId(),userInBase);
                    return true;
                }
            }
            return false;
        }


        public void logoutAllUsers () {
            authMap.clear();

        }

        public boolean isUserAuthById (Long id){
            return authMap.containsKey(id);
        }

    }

