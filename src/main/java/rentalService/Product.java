package rentalService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Data
@Entity
@Table(name="Product_table")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    @ColumnDefault("10") //default 10
    private int qty ;

    @PostPersist
    public void onPostPersist(){
        ProductSaved productSaved = new ProductSaved();
        BeanUtils.copyProperties(this, productSaved);
        productSaved.publishAfterCommit();
        
        // 무중단 배포 테스트     

    }

    /*
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    */
}
