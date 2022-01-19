package design.connect4.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private final Stone stone;
    private final String name;
}
