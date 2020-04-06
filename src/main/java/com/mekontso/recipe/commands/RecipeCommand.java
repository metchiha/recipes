package com.mekontso.recipe.commands;

import com.mekontso.recipe.domain.Difficulty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    private Difficulty difficulty;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @NotBlank
    private String directions;

    private String serving;
    private String source;

    @URL
    private String url;
    private Byte[] image;
    private NoteCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
}
