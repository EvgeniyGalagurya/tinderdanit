import java.util.HashMap;
import java.util.Map;

public class DaoHashMap<A extends Identifable> implements DAO<A> {

  private final Map<Integer, A> mapUsers = new HashMap<>();

  @Override
  public void save(A a) {
    mapUsers.put(a.id(), a);
  }

  @Override
  public A load(int id) {
    return mapUsers.get(id);
  }

  @Override
  public int quantity() throws Exception {
    return mapUsers.size();
  }




}
