import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public record Users(int id, String name, String link) implements IdentifableSerializable {
    static Users Ivan = new Users(1, "Ivan", "https://img.freepik.com/free-photo/handsome-young-man-in-white-t-shirt-cross-arms-chest-and-smiling-pleased_176420-21607.jpg?w=900&t=st=1684319499~exp=1684320099~hmac=0c50531fc8cd5a81f80113e42a75505ff6d56dff0a71b1b5985691f7d522bf38");
    static Users Angelica = new Users(2, "Angelica", "https://img.freepik.com/free-photo/pretty-smiling-joyfully-female-with-fair-hair-dressed-casually-looking-with-satisfaction_176420-15187.jpg?w=900&t=st=1684319522~exp=1684320122~hmac=02ddf077e39c475368289a83def125b5db745d62d9ec160d37dbdcb506c385d2");
    static Users Alex = new Users(3, "Alex", "https://img.freepik.com/free-photo/handsome-stylish-male-entrepreneur-in-glasses-with-laptop_176420-17912.jpg?w=900&t=st=1684319544~exp=1684320144~hmac=da1792ac636470e240d82107c5a25ebec5f00ff21290efce08a28d787bb586f4");
    static Users Helen = new Users(4, "Helen", "https://img.freepik.com/free-photo/studio-portrait-of-successful-young-businesswoman_1262-5844.jpg?w=900&t=st=1684319565~exp=1684320165~hmac=bce479935718db513bb5775f13859a71cdded6e685a1785a6998165d22f90fbe");
    static Users Kristina = new Users(5, "Kristina", "https://img.freepik.com/free-photo/young-and-beautiful-woman-in-pink-warm-sweater-natural-look-smiling-portrait-on-isolated-long-hair_285396-896.jpg?size=626&ext=jpg&ga=GA1.1.1831214472.1684135673&semt=sph");
    static Users Evgeniy = new Users(6, "Evgeniy", "https://img.freepik.com/free-photo/selective-focus-shot-of-a-serious-man-posing-seriously_181624-46455.jpg?size=626&ext=jpg&ga=GA1.2.1831214472.1684135673&semt=sph");

    @Override
    public String toString() {
        return id + ". [" + name + "] - [" + link + "] ";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    static DAO<Users> mapUsersLiked = new DaoHashMap<>();
    static DAO<Users> mapUsers = new DaoHashMap<>();

    public static void usersList1() throws Exception {
        mapUsers.save(Ivan);
        mapUsers.save(Angelica);
        mapUsers.save(Alex);
        mapUsers.save(Helen);
        mapUsers.save(Kristina);
        mapUsers.save(Evgeniy);
    }

    public static int usersListSize() throws Exception {
        Connection conn = DataBaseConn.make();
        String sql = "select count(*) from users";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        int dataBaseSize = 0;
        while (rs.next()) {
            dataBaseSize = rs.getInt(1);
//            System.out.println("Total number of books in the table : " + dataBaseSize);

        }
        return dataBaseSize;
    }
    public static void usersList() throws Exception {

        for (int i = 0; i < Users.usersListSize(); i++) {
            Connection conn = DataBaseConn.make();
            String sql = "select id, name, link from users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String link = rs.getString("link");
                mapUsers.save(new Users(id, name, link));

            }
        }

    }
}
