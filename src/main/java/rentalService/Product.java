package rentalService;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Product_table")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    @PostPersist
    public void onPostPersist(){
        ProductSaved productSaved = new ProductSaved();
        BeanUtils.copyProperties(this, productSaved);
        productSaved.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
