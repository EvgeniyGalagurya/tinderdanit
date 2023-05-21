public interface DAO<A extends Identifable> {

  void save(A a) throws Exception;

  A load(int id) throws Exception;

  int quantity() throws Exception;
}
