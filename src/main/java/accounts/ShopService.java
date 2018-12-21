package accounts;

import DB.DataBase;

public class ShopService {
    private final DataBase db;

    public ShopService(DataBase db) {
        this.db = db;
    }

    public void createNewThing(DataBaseShop dataBaseShop) throws Exception {
        if(db.select("Name", dataBaseShop.getNameThing()).length == 0){

            String data[] = {dataBaseShop.getNameThing(), dataBaseShop.getCost(), dataBaseShop.getCount(), dataBaseShop.getPhoto()};
            db.insert(data);
        }
        else{
            throw new Exception("name already exist");
        }
    }

    public UserProfile getUserByLogin(String login){
        String[] datab = db.select("Login", login)[0].split(" ");
        return new UserProfile(datab[0], Integer.valueOf(datab[1]), Boolean.valueOf(datab[2]));
    }
}
