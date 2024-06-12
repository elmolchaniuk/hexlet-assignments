package exercise.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private Long categoryId;
    @NotNull
    private String categoryName;
    private String title;
    private int price;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
