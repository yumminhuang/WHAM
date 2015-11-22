package wham.operation;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PreferenceOperation {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("WHAM");

    public boolean createPreference(String email, List<Double> subcatrogries) {
        return false;
    }

    public boolean updatePreference(String email, List<Double> subcatrogries) {
        return false;
    }

    public boolean deletePreference(String email) {
        return false;
    }

    public List<Double> getSubCategory(String email) {
        return null;
    }

}
