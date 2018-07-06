package DAOs;


import Entidades.Produto;
import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOProduto extends DAOGenerico<Produto> {

    public DAOProduto() {
        super(Produto.class);
    }

    public int autoIdProduto() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idProduto) FROM Produto e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Produto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.nomeDoProduto LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Produto> listById(int id) {
        return em.createQuery("SELECT e FROM Produto e WHERE e.idProduto = :id").setParameter("id", id).getResultList();
    }

    public List<Produto> listInOrderNome() {
        return em.createQuery("SELECT e FROM Produto e ORDER BY e.nomeDoProduto").getResultList();
    }

    public List<Produto> listInOrderId() {
        return em.createQuery("SELECT e FROM Produto e ORDER BY e.idProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Produto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdProduto() + "-" + lf.get(i).getNomeDoProduto());
        }
        return ls;
    }
}
