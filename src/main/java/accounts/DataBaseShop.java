package accounts;

public class DataBaseShop {
    private String nameThing;
    private String cost;
    private String  count;
    private String photo;

    public DataBaseShop(String nameThing, String cost, String count, String photo) {
        this.nameThing = nameThing;
        this.cost = cost;
        this.count = count;
        this.photo = photo;
    }

    public String getNameThing(){
        return nameThing;
    }

    public String getCount() {
        return count;
    }

    public String getCost() {
        return cost;
    }

    public String getPhoto(){
        return photo;
    }
}
