package wham.operation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import wham.model.User;
import wham.model.Userpreference;
import wham.model.UserpreferencePK;

public class PreferenceOperation {


    private EntityManager em;

    public PreferenceOperation(EntityManager em) {
        this.em = em;
    }

    /**
     * Add preferences for user
     * @param email
     * @param subcategories
     */
    public void createPreference(String email, List<Integer> subcategories) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.emailId = :email", User.class);
        User user = query.setParameter("email", email).getSingleResult();
        em.getTransaction().begin();
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
     * Update user preferences
     * @param email
     * @param subcategories
     */
    public void updatePreference(String email, List<Integer> subcategories) {
        deletePreference(email);
        createPreference(email, subcategories);
    }

    /**
     * Delete all user preferences
     * @param email
     * @return
     */
    public boolean deletePreference(String email) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Userpreference p WHERE p.user.emailId = :email");
        int deletedCount = query.setParameter("email", email).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return deletedCount > 0;
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
