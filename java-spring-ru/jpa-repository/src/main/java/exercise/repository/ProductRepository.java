package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll(Sort sort);
    List<Product> findByPriceBetween(int min, int max, Sort sort);
    List<Product> findByPriceGreaterThanEqual(int min, Sort sort);
    List<Product> findByPriceLessThanEqual(int max, Sort sort);

    List<Product> findByPriceLessThan(int max, Sort sort);
    List<Product> findByPriceGreaterThan(int max, Sort sort);
}
