package wham.operation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import wham.config.AppEntityManager;
import wham.model.User;
import wham.model.Userpreference;
import wham.model.UserpreferencePK;

public class PreferenceOperation {


    private EntityManager em;

    public PreferenceOperation() {
        this.em = AppEntityManager.createEntityManager();
    }

    /**
     * Add preferences for user
     * @param email
     * @param subcategories
     */
    public void createPreference(String email, List<Integer> subcategories) {
        em.getTransaction().begin();
        Query deleteQuery = em.createQuery("DELETE FROM Userpreference p WHERE p.user.emailId = :email");
        int deletedCount = deleteQuery.setParameter("email", email).executeUpdate();
        if (deletedCount > 0)
            System.out.println("Deleted outdated preferences");
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.emailId = :email", User.class);
        User user = query.setParameter("email", email).getSingleResult();
        for(int sub : subcategories) {
            Userpreference pref = new Userpreference();
            UserpreferencePK upk = new UserpreferencePK();
            upk.setUId(user.getUId());
            upk.setSubCategoryId(sub);
            pref.setId(upk);
            pref.setUser(user);
            em.persist(pref);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * List user preferences
     * @param email
     * @return
     */
    public List<Integer> getSubCategory(String email) {
        TypedQuery<Integer> query = em.createQuery(
                "SELECT p.id.subCategoryId FROM Userpreference p WHERE p.user.emailId = :email",
                Integer.class);
        List<Integer> results = query.setParameter("email", email).getResultList();
        em.close();
        return results;
    }

}
