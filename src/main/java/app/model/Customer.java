package app.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Customer {
  private String id;
  @NotNull @Pattern(regexp = "^\\w+$")
  private String name;
  private String description;
}
